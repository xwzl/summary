package com.turing.java.jvm.java8;

import java.lang.reflect.Field;

/**
 * String 从设计上来讲是不可变对象
 *
 * @author xuweizhi
 * @since 2021/02/01 17:47
 */
public class StringTest {

    /**
     * 讲道理字符串里面得内容是可以变化的，最好不要那么做
     */
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        String s = "hello world";
        Field field = String.class.getDeclaredField("value");
        field.setAccessible(true);
        char[] chars = (char[])field.get(s);
        System.out.println(s);
        chars[5] = '_';
        System.out.println(s);
        String s1 = "hello world";
        System.out.println(s1);
    }
}
