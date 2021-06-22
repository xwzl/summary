package leetcode.editor.cn.cow.redsend;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 红包池
 * 1、不断的往红包队列里面生成资金池
 * 2、红包发送端根据发送的速度，消费队列里面的红包
 */
@SuppressWarnings("all")
public class CashPool {

    /**
     * 生成的红包队列
     */
    private LinkedBlockingDeque<RedPacket> redPackets = new LinkedBlockingDeque<>();

    /**
     * 构造红包的线程池
     */
    private ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * 线程安全的随机生成因子
     */
    private final ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();


    /**
     * 剩余金额
     */
    private final AtomicInteger remainMoneyPool = new AtomicInteger(0);


    public CashPool() {
    }

    /**
     * @param remainMoney
     */
    public CashPool(int remainMoney) {
        // 初始化红包池里面的总资金
        this.remainMoneyPool.set(remainMoney);
    }

    /**
     * 执行这个用例，需要注释带参的构造方法
     */


    /**
     * 随机生成1元左右的红包
     *
     * @return
     */
    public boolean buildRedPacket(int avgAmount) throws InterruptedException {
        // 拟读取资金池剩余的金额的io操作
        Thread.sleep((long) (1000 + (Math.random() * 1000)));

        while (true) {
            // 获取当前红包池剩余金额
            int remainMoney = remainMoneyPool.get();
            if (remainMoney == 0) {
                return true;
            }

            // 生成红包金额
            int money = threadLocalRandom.nextInt(avgAmount - 15, avgAmount + 15);

            // 查看红包金额是否够用
            if (remainMoney >= money) {

                // 够用
                int newRemainMoney = remainMoney - money;
                // 设置回去
                if (remainMoneyPool.compareAndSet(remainMoney, newRemainMoney)) {

                    // 不用阻塞，直接进去好了
                    boolean add = false;
                    try {
                        add = this.redPackets.add(new RedPacket(money)); // 落盘
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println(Thread.currentThread().getName() + ":生成红包异常：" + add);

                    }
                    continue;
                }
            }

            if (remainMoney < money) {
                // 资金池不够用了，就用剩余的红包金额作为最后一个红包
                if (remainMoneyPool.compareAndSet(remainMoney, 0)) {
                    this.redPackets.add(new RedPacket(remainMoney));
                    return true;
                }
            }
        }

    }

    public LinkedBlockingDeque<RedPacket> getRedPackets() {
        return redPackets;
    }

    public void setRedPackets(LinkedBlockingDeque<RedPacket> redPackets) {
        this.redPackets = redPackets;
    }

    public ThreadPoolExecutor getExecutorService() {
        return executorService;
    }

    public void setExecutorService(ThreadPoolExecutor executorService) {
        this.executorService = executorService;
    }

    public ThreadLocalRandom getThreadLocalRandom() {
        return threadLocalRandom;
    }

    public AtomicInteger getRemainMoneyPool() {
        return remainMoneyPool;
    }
}
