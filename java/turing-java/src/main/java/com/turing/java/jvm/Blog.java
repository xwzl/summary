package com.turing.java.jvm;

import org.openjdk.jol.info.ClassLayout;

public class Blog {


    private int a = 10;
    private int b = 10;


    public static void main(String[] args) {
        //对象中无属性的数组
        Object[] objArray = new Object[3];
        //对象中存在两个int型属性的数组
        Blog[] blogArray = new Blog[3];
        //基本类型数组
        int[] intArray = new int[1];
        System.out.println(ClassLayout.parseInstance(blogArray).toPrintable());
        System.out.println(ClassLayout.parseInstance(objArray).toPrintable());
        System.out.println(ClassLayout.parseInstance(intArray).toPrintable());
    }
}
