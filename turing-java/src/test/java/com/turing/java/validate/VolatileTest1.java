package com.turing.java.validate;

import java.util.concurrent.CountDownLatch;

public class VolatileTest1 {
    public static volatile int race = 0;
    public static final CountDownLatch countDownLatch = new CountDownLatch(10);

    static void add() {
        race++;
    }

    public static class VO implements Runnable {

        @Override
        public void run() {
            for (int j = 0; j < 1000; j++) {
                add();
            }
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new VO());
            thread.start();
        }
        //保证前面的10条线程都执行完
        countDownLatch.await();
        System.out.println(race);
    }
}
