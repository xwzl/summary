package com.java.interview.java.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Google cache demo
 *
 * @author xuweizhi
 * @since 2021/09/06 10:04
 */
@Slf4j
public class GoogleCacheExample {

    private final static Integer MAX_COUNT = 2;

    @SuppressWarnings("all")
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        AtomicInteger atomic = new AtomicInteger();
        LoadingCache<String, String> userCache = CacheBuilder.newBuilder()
                .concurrencyLevel(8).expireAfterAccess(8, TimeUnit.SECONDS).refreshAfterWrite(3, TimeUnit.SECONDS)
                .initialCapacity(16).maximumSize(256).recordStats()
                .removalListener(notification -> log.info(notification.getKey() + " 被移除了，原因：" + notification.getCause()))
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        log.info("重新初始化value");
                        return key + ":" + atomic.getAndIncrement();
                    }
                });
        // originalExample(userCache);
        for (int i = 0; i < MAX_COUNT; i++) {
            String firstValue = userCache.get("" + i);
            new Thread(new Runnable() {
                @Override
                @SneakyThrows
                public void run() {
                    while (true) {
                        // Thread.sleep((long) (Math.random() * 10000));
                        Thread.sleep(200L);
                        String key = Thread.currentThread().getName();
                        String value = userCache.get(key);
                        log.info("第一次从缓存中获取值;{},第二次从缓存中获取值：key:{},value:{}", firstValue, key, value);
                    }
                }
            }, i + "").start();
        }
    }

    private static void originalExample(LoadingCache<String, String> userCache) throws ExecutionException, InterruptedException {
        List<String> cacheKey = Lists.newArrayList();
        for (int i = 0; i < MAX_COUNT; i++) {
            String key = UUID.randomUUID().toString().replaceAll("-", "");
            String value = userCache.get(key);
            cacheKey.add(key);
            log.info("第一次查询到的数据:{}", value);
        }
        for (String key : cacheKey) {
            Thread.sleep((long) (Math.random() * 1000));
            log.info("第二次命中缓存: key:{},value:{}", key, userCache.get(key));
        }
    }
}
