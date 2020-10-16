package com.turing.java.jvm.concurrent.unsafe;


import com.turing.java.jvm.concurrent.utils.UnsafeInstance;
import sun.misc.Unsafe;

public class ObjectMonitorRunner {
    static Object object = new Object();
    static Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();
    public void method1(){
        unsafe.monitorEnter(object);
    }

    public void method2(){
        unsafe.monitorExit(object);
    }

    public static void main(String[] args) {
        //jvm内置锁
        synchronized (object){

            //写逻辑
        }
    }

}
