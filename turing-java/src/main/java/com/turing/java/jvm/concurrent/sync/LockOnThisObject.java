package com.turing.java.jvm.concurrent.sync;

import org.openjdk.jol.info.ClassLayout;


public class LockOnThisObject {

    private Integer stock = 10;

    public synchronized void decrStock(){
        --stock;
        System.out.println(ClassLayout.parseInstance(this).toPrintable());
    }

    public static void main(String[] args) {
        /*try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        LockOnThisObject to = new LockOnThisObject();
        //System.out.println(ClassLayout.parseInstance(to).toPrintable());
        to.decrStock();

        LockOnThisObject to1 = new LockOnThisObject();
        to1.decrStock();
    }
}
