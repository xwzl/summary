package com.turing.java.module;

import lombok.Data;

/**
 * 验证类加载
 *
 * @author xuweizhi
 * @since 2020/08/03 14:20
 */
@Data
public class User {

    private int id;

    private String name;

    public void print() {
        System.out.println(this.getClass().getClassLoader().getParent());
    }
}
