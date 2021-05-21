package com.turing.java.module;

/**
 * 验证类加载
 *
 * @author xuweizhi
 * @since 2020/08/03 14:20
 */
public class User1 {

    public void print() {
        System.out.println(this.getClass().getClassLoader().getParent());
    }
}
