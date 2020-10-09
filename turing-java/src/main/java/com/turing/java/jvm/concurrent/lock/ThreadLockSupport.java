package com.turing.java.jvm.concurrent.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

@Slf4j
public class ThreadLockSupport {

    public static void main(String[] args) {
        baseLockSupport();
    }

    private static void baseLockSupport() {
        Thread t0 = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread current = Thread.currentThread();
                log.info("{},开始执行!", current.getName());
                for (; ; ) {//spin 自旋
                    log.info("准备park住当前线程：{}....", current.getName());
                    // LockSupport.park() 对于被中断的线程不会阻塞
                    if (Thread.interrupted()) {
                        log.info("线程由于未知原因被阻塞中断");
                        break;
                    }
                    LockSupport.park();
                    // park支持中断唤醒，但是不会抛出InterruptedException异常，可以从isInterrupted不会清除中断标记）、interrupted（会清除中断标记）
                    // 方法中获得中断标记。对于被中断的线程，应该显示的退出线程。
                    //Thread.interrupted(); // 清楚标记，达到 LockSupport.park();阻塞的目的
                    log.info("当前线程{}已经被唤醒....", current.getName());
                }
            }

        }, "t0");
        t0.start();
        try {
            Thread.sleep(1000);
            log.info("准备唤醒{}线程!", t0.getName());
            t0.interrupt();
            LockSupport.unpark(t0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
