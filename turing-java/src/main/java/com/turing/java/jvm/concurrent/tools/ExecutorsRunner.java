package com.turing.java.jvm.concurrent.tools;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorsRunner {

    public static void main(String[] args) {
        ScheduledExecutorService es = Executors.newScheduledThreadPool(1);

        //延迟三秒执行
        es.schedule(new Runnable() {
            public void run() {
                System.out.println("我在跑......");
            }
        }, 3, TimeUnit.SECONDS);

        es.shutdown();

    }

}
