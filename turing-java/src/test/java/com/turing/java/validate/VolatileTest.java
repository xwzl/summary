package com.turing.java.validate;

/**
 * validate test
 *
 * @author xuweizhi
 * @since 2020/09/04 13:11
 */
public class VolatileTest {

    private volatile static int i;

    static {
        i = 0;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int j = 0; j < 10; j++) {
            Thread thread = new Thread(() -> {
                for (int k = 0; k < 10000; k++) {
                    System.out.println(i++);
                }
            });
            thread.start();
        }
        //while (true) {
        //    if (Thread.activeCount() == 2) {
        //        break;
        //    }
        //    System.out.printf("current thread name :%s,current thread active count: %d%n",
        //            Thread.currentThread().getName(), Thread.activeCount());
        //    Thread.sleep(100);
        //}

    }

}
