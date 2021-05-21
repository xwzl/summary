package com.turing.java.jvm.concurrent.consumer;

/**
 * 等待队列测试
 *
 * @author xuweizhi
 * @since 2020/11/30 14:45
 */
public class ThreadCondition {

    private volatile static int capacity = 10;

    public volatile static int count = 0;

    public static void main(String[] args) {
        ThreadCondition threadCondition = new ThreadCondition();

        for (int i = 0; i < 3; i++) {
            @SuppressWarnings("all")
            Thread thread = new Thread(() -> {
                while (true) {
                    synchronized (threadCondition) {
                        try {
                            count++;
                            //System.out.println(Thread.currentThread().getName() + "起始:" + count);
                            while (count >= threadCondition.capacity) {
                                //System.out.println(Thread.currentThread().getName() + "阻塞：" + count);
                                threadCondition.wait();
                                //System.out.println(Thread.currentThread().getName() + "唤醒：" + count);
                            }
                            System.out.println(Thread.currentThread().getName() + ":" + count);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, "" + i);
            thread.start();
        }
        @SuppressWarnings("all")
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (threadCondition) {
                    count = 0;
                    System.out.println("唤醒所有的线程");
                    threadCondition.notifyAll();
                }
            }

        });
        thread.start();
    }
}
