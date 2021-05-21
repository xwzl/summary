package com.turing.java.jvm.concurrent.queue;

import org.junit.Test;

import java.security.SecureRandom;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.SynchronousQueue;

/**
 * @author xuweizhi
 * @since 2021/01/16 15:56
 */
public class SynchronousQueueTest {

    SecureRandom secureRandom = new SecureRandom();

    CountDownLatch countDownLatch = new CountDownLatch(100);

    @Test
    public void doSynchronousQueueTest() throws InterruptedException {
        SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>(false);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    while (true) {
                        Integer take = synchronousQueue.take();
                        System.out.println(Thread.currentThread().getName() + ";获取到数据：" + take);
                        // 超时数据返回为空
                        // Integer poll = synchronousQueue.poll(1, TimeUnit.SECONDS);
                        // if (poll==null) {
                        //     System.out.println("超时");
                        // }

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "消费者线程：" + i).start();
        }
        new Thread(() -> {
            try {
                while (true) {
                    countDownLatch.countDown();
                    int i = secureRandom.nextInt(10);
                    Thread.sleep(1001);
                    synchronousQueue.put(i);
                    System.out.println("产生数据：" + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        countDownLatch.await();

    }
}
