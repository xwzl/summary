package com.turing.java.jvm.concurrent;

import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {
    static ReentrantLock resource1 = new ReentrantLock();
    static ReentrantLock resource2 = new ReentrantLock();

    public static void main(String[] args) {
        deadLock();

    }

    /**
     * 构建两个线程,让他们发生死锁
     */
    private static void deadLock() {
        new Thread(() -> {
            while (true) {
                try {
                    resource1.lock();
                    System.out.println(Thread.currentThread().getName() + ": locked resource1");
                    try {
                        resource2.lock();
                        System.out.println(Thread.currentThread().getName() + ": locked resource2");
                    } finally {
                        resource2.unlock();
                    }
                } finally {
                    resource1.unlock();
                }
            }
        }, "deadLock-1").start();
        new Thread(() -> {
            while (true) {
                try {
                    resource2.lock();
                    System.out.println(Thread.currentThread().getName() + ": locked resource2");
                    try {
                        resource1.lock();
                        System.out.println(Thread.currentThread().getName() + ": locked resource1");
                    } finally {
                        resource1.unlock();
                    }
                } finally {
                    resource2.unlock();
                }
            }
        }, "deadLock-2").start();
    }

}
