package com.redis;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

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


}