package com.java.interview.java.base;

/**
 * 枚举测试
 *
 * @author xuweizhi
 * @since 2021/04/27 17:34
 */
public enum ConstructEnum {

    /**
     * java
     */
    JAVA("Java 入门到放弃","秒速"),
    /**
     * 放弃
     */
    PYTHON("python 入门到放弃","hello");

    private final String name;

    private final String desc;

    ConstructEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
        System.out.printf("初始化枚举值 %s ,描述 %s%n", name, desc);
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
