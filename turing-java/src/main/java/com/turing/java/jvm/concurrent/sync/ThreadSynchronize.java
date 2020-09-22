package com.turing.java.jvm.concurrent.sync;


public class ThreadSynchronize {

    private final static Object object = new Object();

    public static void reentrantLock() {
        String tName = Thread.currentThread().getName();
        synchronized (object) {
            System.out.println(String.format("{}:) hold {}->monitor lock", tName, object));
            synchronized (object) {
                System.out.println(String.format("{}:) re-hold {}->monitor lock", tName, object));
            }
        }
    }

    public static void main(String[] args) {
        ThreadSynchronize.reentrantLock();
    }
}
