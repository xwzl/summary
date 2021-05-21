package com.turing.java.jvm.concurrent.collections.map;

public class MapDeadLock {

    public static void main(String[] args) {
        for (int i=0;i<10;i++){
            new Thread(new MultiThread()).start();
        }
    }
}
