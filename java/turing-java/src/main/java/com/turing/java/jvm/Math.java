package com.turing.java.jvm;

import org.apache.tomcat.jni.User;

/**
 * JVM 类加载机制
 *
 * @author xuweizhi
 * @since 2020/08/03 11:06
 */
public class Math {

    public static final int initData = 666;

    public static User user = new User();

    public int compute() {  //一个方法对应一块栈帧内存区域
        int a = 1;
        int b = 2;
        int c = (a + b) * 10;
        return c;
    }

    public static void main(String[] args) {
        Math math = new Math();
        math.compute();
    }
}
