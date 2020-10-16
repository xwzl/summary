package com.turing.java.jvm.concurrent.collections.map;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThread implements Runnable {
    private static Map<Integer,Integer> map = new HashMap<Integer, Integer>(2);

    private static AtomicInteger atomicInteger = new AtomicInteger();

    public void run() {
        while(atomicInteger.get() < 1000000){
            map.put(atomicInteger.get(),atomicInteger.get());
            atomicInteger.incrementAndGet();
        }
    }
}
