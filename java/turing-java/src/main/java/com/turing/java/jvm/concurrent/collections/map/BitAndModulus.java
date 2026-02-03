package com.turing.java.jvm.concurrent.collections.map;

import org.junit.jupiter.api.Test;

public class BitAndModulus {

    @Test
    public void bit() {
        int number = 100 * 1000;//分别取值10万、100万、1000万、1亿
        int a = 1;
        long start = System.currentTimeMillis();
        for(int i = number; i > 0 ; i++) {
            a = a & i;
        }
        long end = System.currentTimeMillis();
        System.out.println("位运算耗时： " + (end - start));
    }

    @Test
    public void modulus() {
        int number = 10000 * 10;//分别取值10万、100万、1000万、1亿
        int a = 1;
        long start = System.currentTimeMillis();
        for(int i = number; i > 0; i++) {
            a %= i;
        }
        long end = System.currentTimeMillis();
        System.out.println("取模运算耗时： " + (end - start));
    }
}
