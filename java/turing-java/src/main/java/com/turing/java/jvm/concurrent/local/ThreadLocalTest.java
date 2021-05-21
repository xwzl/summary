package com.turing.java.jvm.concurrent.local;

public class ThreadLocalTest {
    /**
     * 全局ThreadLocal对象位于堆中，这是线程共享的，而方法栈，是每个线程私有的
     */
    static ThreadLocal<String> th = new ThreadLocal<>();

    public static void set() {
        //设置值，值为当前线程的名字
        th.set(Thread.currentThread().getName());
    }

    public static String get() {
        //获取值
        return th.get();
    }


    public static void main(String[] args) throws InterruptedException {
        System.out.println("主线程中尝试获取值:" + get());
        //主线程中设置值，值为线程名字
        set();
        //主线程中尝试获取值
        System.out.println("主线程中再次尝试获取值:" + get());
        //开启一条子线程
        Thread thread = new Thread(new Th1(), "child");
        thread.start();
        //主线程等待子线程执行完毕
        thread.join();
        System.out.println("等待子线程执行完毕，主线程中再次尝试获取值:" + get());
    }

    static class Th1 implements Runnable {
        @Override
        public void run() {
            System.out.println("子线程中尝试获取值:" + get());
            //子线程中设置值，值为线程名字
            set();
            System.out.println("子线程中再次尝试获取值:" + get());
        }
    }
}
