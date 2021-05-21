package com.turing.java.jvm.java8;

/**
 * -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly
 */
public class VolatileTest {

    private  volatile int j;
    private   int s;
    private   int a;
    private   int b;

    public static void main(String[] args) {
        for (int i = 0; i < 1000000; i++) {
           new VolatileTest().SSS();
        }
    }

    private  void SSS() {
        s = 0;
        s = 1;
        j = 1;
        a = s;
        b = j;
    }
}
