package com.redis;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Slf4j
@RestController
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private Redisson redisson;

    /**
     * 测试节点挂了哨兵重新选举新的master节点，客户端是否能动态感知到
     */
    @RequestMapping("/test_sentinel")
    public void testSentinel() throws InterruptedException {
        int i = 1;
        while (i < 500) {
            try {
                i++;
                stringRedisTemplate.opsForValue().set("zhuge" + i, i + ""); //jedis.set(key,value);
                stringRedisTemplate.opsForValue().setIfAbsent("", "", 1, TimeUnit.MINUTES);
                System.out.println("设置key：" + "zhuge" + i);
                Thread.sleep(1000);
            } catch (Exception e) {
                logger.error("错误：", e);
            }
        }
    }

    /**
     * 分布式锁测试
     */
    @RequestMapping("/distributedLock")
    public String distributedLock() {
        String lock = "product:lock";
        //String clientId = UUID.randomUUID().toString();
        RLock redissonLock = redisson.getLock(lock);
        try {
            // setnx 命令
            //Boolean lockResult = stringRedisTemplate.opsForValue().setIfAbsent(lock, clientId, 10, TimeUnit.HOURS);
            redissonLock.lock();
            //if (!lockResult) {
            //    return "1001";
            //}
            // todo 如果执行之间过长，可以另起一个线程续期
            int stock = Integer.parseInt(Objects.requireNonNull(stringRedisTemplate.opsForValue().get("stock")));
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", realStock + "");
                System.out.println("扣减成功，剩余库存：" + realStock);
            } else {
                System.out.println("扣减失败，剩余库存不足");
            }
        } finally {
            // 防止释放其它线程得锁,不是原子性操作，lua 脚本修改成原子操作
            //if (clientId.equals(stringRedisTemplate.opsForValue().get(lock))) {
            //    stringRedisTemplate.delete(lock);
            //}
            redissonLock.unlock();
        }
        return "end";
    }

    //public void redLockImpl() {
    //    Config config1 = new Config();
    //    config1.useSingleServer().setAddress("redis://172.29.1.180:5378")
    //            .setPassword("a123456").setDatabase(0);
    //    RedissonClient redissonClient1 = Redisson.create(config1);
    //    Config config2 = new Config();
    //    config2.useSingleServer().setAddress("redis://172.29.1.180:5379")
    //            .setPassword("a123456").setDatabase(0);
    //    RedissonClient redissonClient2 = Redisson.create(config2);
    //    Config config3 = new Config();
    //    config3.useSingleServer().setAddress("redis://172.29.1.180:5380")
    //            .setPassword("a123456").setDatabase(0);
    //    RedissonClient redissonClient3 = Redisson.create(config3);
    //    String resourceName = "REDLOCK";
    //    RLock lock1 = redissonClient1.getLock(resourceName);
    //    RLock lock2 = redissonClient2.getLock(resourceName);
    //    RLock lock3 = redissonClient3.getLock(resourceName);
    //    RedissonRedLock redLock = new RedissonRedLock(lock1, lock2, lock3);
    //    boolean isLock;
    //    try {
    //        isLock = redLock.tryLock(500, 30000, TimeUnit.MILLISECONDS);
    //        System.out.println("isLock = " + isLock);
    //        if (isLock) {
    //            //TODO if get lock success, do something;
    //            Thread.sleep(30000);
    //        }
    //    } catch (Exception e) {
    //    } finally {
    //        // 无论如何, 最后都要解锁
    //        System.out.println("");
    //        redLock.unlock();
    //    }
    //}

    /**
     * 分布式锁测试
     */
    @RequestMapping("/redLock")
    public void redLock() {
        // 配置多台互相独立的redis单机节点
        Config config1 = new Config();
        config1.useSingleServer().setAddress("redis://192.168.208.128:9000").setDatabase(0).setPassword("zhuge");
        Config config2 = new Config();
        config2.useSingleServer().setAddress("redis://192.168.208.128:9001").setDatabase(0).setPassword("zhuge");
        Config config3 = new Config();
        config3.useSingleServer().setAddress("redis://192.168.208.128:9002").setDatabase(0).setPassword("zhuge");

        // 构建RedissonClient
        RedissonClient redissonClient1 = Redisson.create(config1);
        RedissonClient redissonClient2 = Redisson.create(config2);
        RedissonClient redissonClient3 = Redisson.create(config3);

        // 5个线程并发去获取锁
        IntStream.range(0, 5).parallel().forEach(i -> tryRedlock(redissonClient1, redissonClient2, redissonClient3));
    }

    private void tryRedlock(RedissonClient... redissonClients) {
        // 构建Redlock对象
        RLock[] rLock = new RLock[redissonClients.length];
        // 与redisson的普通分布式锁相比，只是会有多个RedissonClient（每个对应1个redis节点）构建出多个RLock对象，再由这多个RLock对象构建成
        // RedissonRedLock对象。由这个对象去获取和释放锁的步骤与用redisson的普通分布式锁步骤一模一样。
        for (int i = 0; i < rLock.length; i++) {
            rLock[i] = redissonClients[i].getLock("1111111");
        }
        RLock redLock = new RedissonRedLock(rLock);

        // 基于Redlock对象去操作，与redisson实现普通的分布式锁一样
        try {
            // 获取锁最多等待500ms，10s后key过期自动释放锁
            boolean tryLock = redLock.tryLock(500, 10000, TimeUnit.MILLISECONDS);
            if (!tryLock) {
                log.info("当前线程:[{}]没有获得锁", Thread.currentThread().getName());
                return;
            }
            log.info("当前线程:[{}]获得锁", Thread.currentThread().getName());
            // 操作资源...
        } catch (InterruptedException e) {
            log.error("获取分布式锁失败", e);
        } finally {
            redLock.unlock();
        }
    }


}
