package com.turing.java.jvm.concurrent.jmm;

/**
 * @author xuweizhi
 * @since 2020/09/16
 **/
public class HappensBefore {
    public static volatile int r = 3;

    public static int g = 6;

    public static volatile double pai = 3.14;

    public static volatile double area;

    public static void caculate() {
        int a = r;
        int b = g;
        area = a * b * pai;
    }

    public static void main(String[] args) {
        caculate();
    }
}
