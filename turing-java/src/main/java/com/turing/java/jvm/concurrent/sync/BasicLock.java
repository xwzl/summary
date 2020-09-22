package com.turing.java.jvm.concurrent.sync;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;


@Slf4j
public class BasicLock {
    public static void main(String[] args) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Object o = new Object();
        log.info(ClassLayout.parseInstance(o).toPrintable());

        new Thread(() -> {
            synchronized (o) {
                log.info(ClassLayout.parseInstance(o).toPrintable());
            }
        }).start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info(ClassLayout.parseInstance(o).toPrintable());
        new Thread(() -> {
            synchronized (o) {
                log.info(ClassLayout.parseInstance(o).toPrintable());
            }
        }).start();
    }
}
