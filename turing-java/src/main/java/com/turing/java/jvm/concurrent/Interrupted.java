package com.turing.java.jvm.concurrent;

public class Interrupted {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {}
        });
        thread.start();
        thread.interrupt();
        //获取中断标志,获取子线程的中断标志
        System.out.println("isInterrupted:" + thread.isInterrupted());
        //获取中断标志并重置,虽然是thread.interrupted(),但实际上是获取主线程的中断标志,因为在主线程中调用的
        System.out.println("interrupted:" + thread.interrupted());
        //获取中断标志并重置,也是获取主线程的中断标志
        System.out.println("interrupted:" + Thread.interrupted());
        //获取中断标志,获取子线程的中断标志
        System.out.println("interrupted:" + thread.isInterrupted());
    }
}
