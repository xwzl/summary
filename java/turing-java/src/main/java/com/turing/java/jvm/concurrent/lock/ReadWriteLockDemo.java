package com.turing.java.jvm.concurrent.lock;

import co.paralleluniverse.strands.concurrent.ReentrantLock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 首先，对于读写都使用同一个 ReentrantLock 锁，即独占锁，记录完成时间；然后切换到 ReentrantReadWriteLock，
 * 其中读任务使用读锁，写任务使用写锁，记录完成时间，可以发现，在读任务明显多于写任务的时候，读写所的性能将会优
 * 于独占锁！
 */
public class ReadWriteLockDemo {
    private static Lock lock = new ReentrantLock();

    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    private static Lock readLock = reentrantReadWriteLock.readLock();

    private static Lock writeLock = reentrantReadWriteLock.writeLock();

    private static CountDownLatch countDownLatch = new CountDownLatch(110);

    private static void read(Lock lock) throws InterruptedException {
        try {
            lock.lock();
            //模拟读操作
            Thread.sleep(500);
        } finally {
            lock.unlock();
        }
    }

    private static void write(Lock lock) throws InterruptedException {
        try {
            lock.lock();
            //模拟写操作
            Thread.sleep(1000);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String args[]) throws InterruptedException {
        //普通独占锁测试性能，读和写都是用一个锁
        //Write write = new Write(lock);
        //Read read = new Read(lock);
        //读写锁测试性能，读使读锁，写使用写锁
        Write write = new Write(writeLock);
        Read read = new Read(readLock);
        //计算分别采用普通重入锁和读写锁的耗时,可以发现
        long l = System.currentTimeMillis();
        //执行任务
        for (int i = 0; i < 100; i++) {
            new Thread(read).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(write).start();
        }
        //等待任务执行完毕
        countDownLatch.await();
        //计算任务耗时，读写锁耗时远低于普通重入锁
        System.out.println(System.currentTimeMillis() - l);
    }

    /**
     * 写任务
     */
    static class Write implements Runnable {
        Lock lock;

        Write(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            //分别使用两种锁来运行,性能差别很直观的就体现出来
            try {
                write(lock);
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读任务
     */
    static class Read implements Runnable {
        Lock lock;

        Read(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            //分别使用两种锁来运行,性能差别很直观的就体现出来
            try {
                read(lock);
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}