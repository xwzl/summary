package com.turing.java.jvm.concurrent.jmm.tmodel;

/**
 * @author xuweizhi
 * @since 2020/09/16
 **/
public class ThreadModel {

    public static void main(String[] args) {

        for (int i = 0; i < 200; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }

}
