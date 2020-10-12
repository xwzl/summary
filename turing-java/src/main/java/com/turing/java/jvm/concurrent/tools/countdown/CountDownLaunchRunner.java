package com.turing.java.jvm.concurrent.tools.countdown;

import java.util.concurrent.CountDownLatch;

public class CountDownLaunchRunner {
    static int sub = 0;
    static Object object = new Object();
    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        //positive(countDownLatch);
        reverse(now, countDownLatch);
    }
    /**
     *  await 和 countDown 顺序调用
     */
    private static void reverse(long now, CountDownLatch countDownLatch) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    // CountDownLatch await 和 countDown 调用顺序正着和反正放都可以用
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object) {
                    for (int j = 0; j < 1000; j++) {
                        sub++;
                    }
                }
            });
        }
        Thread.sleep(3000);
        //等待线程池中的2个任务执行完毕，否则一直等待,zk分布式锁
        countDownLatch.countDown();
        System.out.println("over，回家 cost:" + (System.currentTimeMillis() - now));
    }

    /**
     *  countDown 和 await 顺序调用
     */
    private static void positive(CountDownLatch countDownLatch) throws InterruptedException {
        new Thread(new SeeDoctorTask(countDownLatch)).start();
        new Thread(new QueueTask(countDownLatch)).start();
        countDownLatch.await();
        System.out.println("可以吃饭了");
    }

}
