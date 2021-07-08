package com.turing.java.module;

/**
 * 验证类加载
 *
 * @author xuweizhi
 * @since 2020/08/03 14:20
 */
public class User {

    private int id;

    private String name;

    public void print() {
        System.out.println(this.getClass().getClassLoader().getParent());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
