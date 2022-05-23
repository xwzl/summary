package com.turing.java.jvm.concurrent.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xuweizhi
 * @since 2021/02/22 20:04
 */
public class ThreadLocalSample {

    private static final int THREAD_LOOP_SIZE = 2;

    private static ThreadLocal<String> threadLocal1 = new ThreadLocal<>();
    private static ThreadLocal<String> threadLocal2 = new ThreadLocal<>();
    private static ThreadLocal<String> threadLocal3 = new ThreadLocal<>();



    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_LOOP_SIZE);
        for (int i = 0; i < THREAD_LOOP_SIZE; i++) {
            executorService.execute(() -> {
                threadLocal1.set("123");
                threadLocal2.set("234");
                threadLocal3.set("345");
            });
        }

        String strOne = threadLocal1.get();
        System.out.println("");
        executorService.shutdown();
    }

}
