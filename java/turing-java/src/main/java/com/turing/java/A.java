package com.turing.java;

import com.turing.java.jvm.concurrent.collections.list.CopyOnWriteArrayListRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.applet.AppletContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class A {

    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new CopyOnWriteArrayList<>(new ArrayList<>());



        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Thread e = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    list.add(j);
                }
            });
            threads.add(e);
            e.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

        int size = list.size();
        System.out.println(size);
    }
}
