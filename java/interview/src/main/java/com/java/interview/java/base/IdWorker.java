package com.java.interview.java.base;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@SuppressWarnings("all")
public class IdWorker {

    //下面两个每个5位，加起来就是10位的工作机器id
    private long workerId;    //工作id

    private long datacenterId;   //数据id

    //12位的序列号
    private long sequence;

    // https://www.jianshu.com/p/e94f793d2b34
    public IdWorker(long workerId, long datacenterId, long sequence) {
        // sanity check for workerId
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        System.out.printf("worker starting. timestamp left shift %d, datacenter id bits %d, worker id bits %d, sequence bits %d, workerid %d", timestampLeftShift, datacenterIdBits, workerIdBits, sequenceBits, workerId);

        this.workerId = workerId;
        this.datacenterId = datacenterId;
        this.sequence = sequence;
    }

    //初始时间戳
    private long twepoch = 1288834974657L;

    //长度为5位
    private long workerIdBits = 5L;
    private long datacenterIdBits = 5L;
    //最大值
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    //序列号id长度
    private long sequenceBits = 12L;
    //序列号最大值
    private long sequenceMask = -1L ^ (-1L << sequenceBits);

    //工作id需要左移的位数，12位
    private long workerIdShift = sequenceBits;
    //数据id需要左移位数 12+5=17位
    private long datacenterIdShift = sequenceBits + workerIdBits;
    //时间戳需要左移位数 12+5+5=22位
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    //上次时间戳，初始值为负数
    private long lastTimestamp = -1L;

    public long getWorkerId() {
        return workerId;
    }

    public long getDatacenterId() {
        return datacenterId;
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * SnowFlake算法生成id的结果是一个64bit大小的整数，其中的41位时间戳部分依赖服务器的时间，当服务器发生时钟回拨时，在开源的实现中不可避免的会出现报错。
     * 首先，SnowFlake的末尾12位是序列号，用来记录同一毫秒内产生的不同id，同一毫秒总共可以产生4096个id，每一毫秒的序列号都是从0这个基础序列号开始递增。
     * 假设我们的业务系统在单机上的QPS为3w/s，那么其实平均每毫秒只需要产生30个id即可，远没有达到设计的4096，也就是说通常情况下序列号的使用都是处在一个低
     * 水位，当发生时钟回拨的时候，这些尚未被使用的序号就可以派上用场了。
     * <p>
     * 因此，可以对给定的基础序列号稍加修改，后面每发生一次时钟回拨就将基础序列号加上指定的步长，例如开始时是从0递增，发生一次时钟回拨后从1024开始递增，再发
     * 生一次时钟回拨则从2048递增，这样还能够满足3次的时钟回拨到同一时间点。
     *
     * @return
     */
    public synchronized long nextId() {
        long timestamp = timeGen();

        //获取当前时间戳如果小于上次时间戳，则表示时间戳获取出现异常
        if (timestamp < lastTimestamp) {
            System.err.printf("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp);
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        //获取当前时间戳如果等于上次时间戳（同一毫秒内），则在序列号加一；否则序列号赋值为0，从0开始。
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }

        //将上次时间戳值刷新
        lastTimestamp = timestamp;

        /**
         * 返回结果：
         * (timestamp - twepoch) << timestampLeftShift) 表示将时间戳减去初始时间戳，再左移相应位数
         * (datacenterId << datacenterIdShift) 表示将数据id左移相应位数
         * (workerId << workerIdShift) 表示将工作id左移相应位数
         * | 是按位或运算符，例如：x | y，只有当x，y都为0的时候结果才为0，其它情况结果都为1。
         * 因为个部分只有相应位上的值有意义，其它位上都是0，所以将各部分的值进行 | 运算就能得到最终拼接好的id
         */
        return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
    }

    // 修改处: workerId原则上上限为1024, 但是为了每台sequence服务预留一个workerId, 所以实际上workerId上限为512
    private long WORKER_ID_MAX_VALUE = 1L << workerIdBits >> 1;
    private long MAX_BACKWARD_MS = 1000L;

    /**
     * 保留workerId和lastTime, 以及备用workerId和其对应的lastTime
     */
    private static Map<Long, Long> workerIdLastTimeMap = new ConcurrentHashMap<>();

    /**
     * Generate key. 考虑时钟回拨, 与sharding-jdbc源码的区别就在这里</br>
     * 缺陷: 如果连续两次时钟回拨, 可能还是会有问题, 但是这种概率极低极低
     */
    public synchronized Number generateKey() {
        long currentMillis = System.currentTimeMillis();

        // 当发生时钟回拨时
        if (lastTimestamp > currentMillis) {
            // 如果时钟回拨在可接受范围内, 等待即可
            if (lastTimestamp - currentMillis < MAX_BACKWARD_MS) {
                try {
                    Thread.sleep(lastTimestamp - currentMillis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                // 如果时钟回拨太多, 那么换备用workerId尝试

                // 当前workerId和备用workerId的值的差值为512
                long interval = 512L;
                // 发生时钟回拨时, 计算备用workerId[如果当前workerId小于512,
                // 那么备用workerId=workerId+512; 否则备用workerId=workerId-512, 两个workerId轮换用]
                if (workerId >= interval) {
                    workerId = workerId - interval;
                } else {
                    workerId = workerId + interval;
                }

                // 取得备用workerId的lastTimestamp
                Long tempTime = workerIdLastTimeMap.get(workerId);
                lastTimestamp = tempTime == null ? 0L : tempTime;
                // 如果在备用workerId也处于过去的时钟, 那么抛出异常
                // [这里也可以增加时钟回拨是否超过MAX_BACKWARD_MS的判断]
                Preconditions.checkState(lastTimestamp <= currentMillis, "Clock is moving backwards, last time is %d milliseconds, current time is %d milliseconds", lastTimestamp, currentMillis);
                // 备用workerId上也处于时钟回拨范围内的逻辑还可以优化: 比如摘掉当前节点. 运维通过监控发现问题并修复时钟回拨
            }
        }

        // 如果和最后一次请求处于同一毫秒, 那么sequence+1
        if (lastTimestamp == currentMillis) {
            if (0L == (sequence = ++sequence & sequenceMask)) {
                // currentMillis = waitUntilNextTime(currentMillis);
                currentMillis = tilNextMillis(currentMillis);
            }
        } else {
            // 如果是一个更近的时间戳, 那么sequence归零
            sequence = 0;
        }

        lastTimestamp = currentMillis;
        // 更新map中保存的workerId对应的lastTimestamp
        workerIdLastTimeMap.put(workerId, lastTimestamp);

        if (log.isDebugEnabled()) {
            log.debug("{}-{}-{}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date(lastTimestamp)), workerId, sequence);
        }
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date(lastTimestamp))
                + " -- " + workerId + " -- " + sequence + " -- " + workerIdLastTimeMap);
       return ((currentMillis - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
    }

    //获取时间戳，并与上次时间戳比较
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    //获取系统时间戳
    private long timeGen() {
        return System.currentTimeMillis();
    }
}
