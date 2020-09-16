package com.turing.java.jvm.concurrent;

public class Daemon {
//启动该类，将会构造两条线程，main线程和一条子线程。
    public static void main(String[] args) throws InterruptedException {
        //测试非守护线程
        //可以看到,输出"main线程结束"之后,子线程还在继续输出,程序没有结束
//        test1();
        //测试守护线程
        //可以看到,输出"main线程结束"之后,子线程没有继续输出,程序结束
        test2();
    }

    /**
     * 测试非守护线程
     *
     * @throws InterruptedException
     */
    public static void test1() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.currentThread().sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程非守护线程");
            }
        });
        thread.start();
        Thread.currentThread().sleep(1000);
        System.out.println("main线程结束");
    }

    //测试守护线程
    public static void test2() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.currentThread().sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程守护线程");
            }
        });
        thread.setDaemon(true);
        thread.start();
        Thread.currentThread().sleep(1000);
        System.out.println("main线程结束");
    }
}

