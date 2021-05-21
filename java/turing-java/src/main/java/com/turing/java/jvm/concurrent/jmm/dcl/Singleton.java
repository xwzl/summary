package com.turing.java.jvm.concurrent.jmm.dcl;

/**
 * @author xuweizhi
 * @since 2020/8/2
 */
public class Singleton {

    /**
     * 查看汇编指令
     * -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -Xcomp
     */
    private volatile static Singleton myInstance;

    /**
     * 双重锁机制保证单例安全
     */
    public static Singleton getInstance() {
        if (myInstance == null) {
            synchronized (Singleton.class) {
                if (myInstance == null) {
                    myInstance = new Singleton();
                }
            }
        }
        return myInstance;
    }

    public static void main(String[] args) {
        Singleton.getInstance();
    }
}
