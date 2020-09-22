package com.turing.java.jvm.concurrent.sync.images;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 *  JVM 偏向锁和 hash code 值得存放地址
 */
public class ObjectSize {

    public static void main(String[] args) throws InterruptedException {
        // jvm 偏向锁延迟5秒钟启动，有可能时间会更短
        TimeUnit.SECONDS.sleep(5);
        Object o = new Object();
        // 由于 windows 和 linux  采用小端模式，高位存地位地址。因此  00000101 00000000 00000000 00000000
        // 一开始就获取到偏向锁
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o) {
            // hash code 的生成时机
            // 偏向锁：-> C.System.lazyHashCode
            // 轻量级锁：-> lock record(markWord)
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
