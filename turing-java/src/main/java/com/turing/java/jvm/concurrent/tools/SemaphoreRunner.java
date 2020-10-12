package com.turing.java.jvm.concurrent.tools;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * juc 工具包：相当于计数等待，让多个任务同时并发执行
 * <p>
 * 信号量
 */
public class SemaphoreRunner {

    private final static int PERMITS_COUNT = 2;

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            new Thread(new Task(semaphore, "Task+" + i)).start();
        }
    }

    static class Task extends Thread {
        Semaphore semaphore;

        public Task(Semaphore semaphore, String tName) {
            super(tName);
            this.semaphore = semaphore;
            //this.setName(tName);
        }

        public void run() {
            try {
                //basicCall();
                //stepCall();
                timeoutWaiting();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /**
         * 如果等待时间超过最长等待时间，则进入降级逻辑
         */
        private void timeoutWaiting() throws InterruptedException {
            if (semaphore.tryAcquire(500, TimeUnit.MILLISECONDS)) {
                System.out.println(Thread.currentThread().getName() + ":acquire() at time:" + System.currentTimeMillis());
                Thread.sleep(5000);
                semaphore.release();//释放公共资源
            } else {
                fallback();
            }

        }

        /**
         * 减去相应数值，释放时应注意释放相同的数值
         */
        private void stepCall() throws InterruptedException {
            // 当 STATE - PERMITS_COUNT > 0 才能拿到资源，相当于每次减去 PERMITS_COUNT 步数
            semaphore.acquire(PERMITS_COUNT);
            System.out.println(Thread.currentThread().getName() + ":acquire() at time:" + System.currentTimeMillis());
            Thread.sleep(3000);
            semaphore.release(PERMITS_COUNT);
        }

        /**
         * 最基本的调用，每次 acquire 被调用，state - 1; 至到 state - 1 <= 0 时进入的等待状态
         */
        private void basicCall() throws InterruptedException {
            //semaphore.acquireUninterruptibly(); // 不支持中断的特性
            semaphore.acquire();//获取公共资源,最多只有三个线程同时执行
            System.out.println(Thread.currentThread().getName() + ":acquire() at time:" + System.currentTimeMillis());
            Thread.sleep(3000);
            semaphore.release(PERMITS_COUNT);
        }

        public void fallback() {
            System.out.println("降级");
        }
    }
}
