package com.turing.java.jvm.concurrent.sync;


public class LockOnClass {
    static int stock;

    public static synchronized void decrStock(){
        System.out.println(--stock);
    }

    public static synchronized void cgg(){
        System.out.println();
    }

    public static void main(String[] args) {
        //Juc_LockOnClass.class对象
        LockOnClass.decrStock();
    }

}
