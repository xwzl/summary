package com.turing.java.jvm.concurrent.aqs;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 对于 ReentrantLock 理解与 LockSupport 理解
 *
 * @author xuweizhi
 * @since 2020/09/24 13:21
 */
@Slf4j
public class AQSAndLockSupport {

    ReentrantLock lock = new ReentrantLock();

    @Test
    public void lockTest() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            lock.lock();
            for (; ; ) {
                if (Thread.interrupted()) {
                    if (Math.random() > 0.1) {
                        continue;
                    }
                    break;
                }
            }
            lock.unlock();
        }, "lock");
        Thread.sleep(1000);
    }

    @Test
    public void lockSupportPark() throws InterruptedException {
        park();
    }

    /**
     * 对于 {@link LockSupport#park()} 方法来讲，当调用该方法的线程被阻塞时，如果线程被中断，
     * park() 方法将失效，因此必须显示的处理线程中断状态。
     */
    private void park() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (; ; ) {
                //if (Thread.currentThread().isInterrupted()) {
                //    log.info("Thread.currentThread().isInterrupted() 不会清楚线程中中断标记状态：{}", Thread.currentThread().isInterrupted());
                //    log.info("Thread.interrupted() 方法会清楚中断标记,{}", Thread.interrupted());
                //    log.info("由于该方法清楚了中断标记，第二次标记为{}", Thread.interrupted());
                //}
                log.info("调用 LockSupport.spark() 阻塞线程");
                LockSupport.park(); // 刚方法的区别在 AQS 有特殊处理，线程中断也可以阻塞
                //LockSupport.park(lock);
                log.info("被其它线程解除了等待状态");
            }
        }, "Lock Support Thread");
        t1.start();
        Thread.sleep(2000);
        log.info("主线程解除 t1 线程的阻塞状态");
        LockSupport.unpark(t1);
        Thread.sleep(2000);
        t1.interrupt();
        t1.join();
    }

}
