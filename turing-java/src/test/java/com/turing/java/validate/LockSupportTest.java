package com.turing.java.validate;

import org.junit.Test;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport Test
 *
 * @author xuweizhi
 * @since 2020/10/09 17:27
 */
public class LockSupportTest {

    /**
     * park/unpark测试
     */
    @Test
    public void test2() {
        System.out.println("begin park");
        //调用park方法
        LockSupport.park();
        //使当前线程获取到许可证,明显执行不到这一步来,因为在上一步就已经阻塞了
        LockSupport.unpark(Thread.currentThread());
        System.out.println("end park");
    }

    /**
     * park/unpark测试
     */
    @Test
    public void test3() {
        System.out.println("begin park");
        //使当前线程先获取到许可证
        LockSupport.unpark(Thread.currentThread());
        //再次调用park方法,先获得了许可,因此该方法不会阻塞
        LockSupport.park();
        System.out.println("end park");
    }

    /**
     * park/unpark测试
     */
    @Test
    public void test4() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                long currentTimeMillis = System.currentTimeMillis();
                System.out.println("begin park");
                LockSupport.park();
                System.out.println("end park");
                System.out.println(System.currentTimeMillis() - currentTimeMillis);
            }
        });
        thread.start();
        //开放或者注释该行代码,观察end park时间
        Thread.sleep(2000);
        //使当子线程获取到许可证
        LockSupport.unpark(thread);
    }

    /**
     * park线程状态测试
     */
    @Test
    public void test1() throws InterruptedException {
        //park不限时
        Thread thread = new Thread(LockSupport::park);
        //park限时
        Thread thread2 = new Thread(() -> LockSupport.parkNanos(3000000000L));
        thread.start();
        thread2.start();
        //主线睡眠一秒,让子线程充分运行
        Thread.sleep(1000);
        //获取处于park的子线程状态
        System.out.println(thread.getState());
        System.out.println(thread2.getState());
    }

    /**
     * park中断测试
     */
    @Test
    public void test5() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 由于 LockSupport.park(); 对中断状态的线程会立即返回，因此线程挂起会失败。
                for (;;){
                    //最开始中断标志位位false
                    System.out.println(Thread.currentThread().isInterrupted());
                    // 对于中断标志为 false 的程序应该立即返回
                    long currentTimeMillis = System.currentTimeMillis();
                    System.out.println("begin park");
                    LockSupport.park();
                    System.out.println("end park");
                    System.out.println(System.currentTimeMillis() - currentTimeMillis);
                    //调用interrupt方法之后,中断标志位为true
                    System.out.println(Thread.currentThread().isInterrupted());
                }
            }
        });
        thread.start();
        //开放或者注释该行代码,观察end park时间
        Thread.sleep(2000);
        //使用interrupt,也可以中断因为park造成的阻塞,但是该中断不会抛出异常
        thread.interrupt();
    }


}
