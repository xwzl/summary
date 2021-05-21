package com.turing.java.jvm.concurrent.atomic;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.LockSupport;

public class AtomicStampedReferenceRunner {

    private static AtomicStampedReference<Integer> atomicStampedRef =
            new AtomicStampedReference<>(1, 0);

    public static void main(String[] args) {
        Thread main = new Thread(() -> {
            int stamp = atomicStampedRef.getStamp(); //获取当前标识别
            System.out.println("操作线程" + Thread.currentThread() + "stamp=" + stamp + ",初始值 a = " + atomicStampedRef.getReference());
            try {
                Thread.sleep(3000); //等待1秒 ，以便让干扰线程执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean isCASSuccess = atomicStampedRef.compareAndSet(1, 2, stamp, stamp + 1);  //此时expectedReference未发生改变，但是stamp已经被修改了,所以CAS失败
            System.out.println("操作线程" + Thread.currentThread() + "stamp=" + stamp + ",CAS操作结果: " + isCASSuccess);
        }, "主操作线程");

        Thread other = new Thread(() -> {
            int stamp = atomicStampedRef.getStamp();
            atomicStampedRef.compareAndSet(1, 2, stamp, stamp + 1);
            System.out.println("操作线程" + Thread.currentThread() + "stamp=" + atomicStampedRef.getStamp() + ",【increment】 ,值 a= " + atomicStampedRef.getReference());
            stamp = atomicStampedRef.getStamp();
            atomicStampedRef.compareAndSet(2, 1, stamp, stamp + 1);
            System.out.println("操作线程" + Thread.currentThread() + "stamp=" + atomicStampedRef.getStamp() + ",【decrement】 ,值 a= " + atomicStampedRef.getReference());
        }, "干扰线程");

        main.start();
        LockSupport.parkNanos(1000000);
        other.start();
    }
}
