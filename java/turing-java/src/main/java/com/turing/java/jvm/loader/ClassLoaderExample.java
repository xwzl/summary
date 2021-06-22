package com.turing.java.jvm.loader;

/**
 * 常量在编译确定不会触发类加载或者类的初始化C
 *
 * @author xuweizhi
 * @since 2021/06/09 09:53
 */
public class ClassLoaderExample {

    static {
        System.out.println("该类也不会被加载");
    }
    public static class FinalConstant {
        public final static String COMPILE_CONSTANT = "编译器确定常量静态代码块不会被执行";
        public final static String RUNTIME_CONSTANT = Math.random() + "";
        static {
            System.out.println("执行"); }
    }



}
