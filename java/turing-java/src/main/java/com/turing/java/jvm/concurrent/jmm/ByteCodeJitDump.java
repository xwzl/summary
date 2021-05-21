package com.turing.java.jvm.concurrent.jmm;

/**
 * @author xuweizhi
 * @since 2020/09/16
 */
public class ByteCodeJitDump {
    private static int c = 1;

    public static int refresh() {
        int a = 0;
        int b = 1;
        int sub = a + b + c;
        return sub;
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread0 = new Thread(() -> {
            System.out.println(String.format("sub0:%d", refresh()));
        });

        thread0.start();

        Thread thread1 = new Thread(() -> {
            System.out.println(String.format("sub1:%d", refresh()));
        });

        thread1.start();
    }
}
