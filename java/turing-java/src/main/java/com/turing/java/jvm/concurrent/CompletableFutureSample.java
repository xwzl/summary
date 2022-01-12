package com.turing.java.jvm.concurrent;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 匹诺曹
 *
 * @author xuweizhi
 * @since 2022/01/08 16:49
 */
public class CompletableFutureSample {

    public static void main(String[] args) throws InterruptedException {
        List<CompletableFuture<Void>> completableFutures = Lists.newArrayList();
        for (int i = 1; i < 10; i++) {
            int finalI = i;
            CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
                System.out.println("111");
                try {
                    Thread.sleep(finalI * 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("222");
            });
            completableFutures.add(completableFuture);
        }
        CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0])).join();
    }
}
