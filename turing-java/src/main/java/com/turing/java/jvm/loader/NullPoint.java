package com.turing.java.jvm.loader;

import lombok.Data;

/**
 * 空指针测试
 *
 * @author xuweizhi
 * @since 2020/09/29 13:47
 */
@Data
public class NullPoint {

    private int x;
    private Integer y;

    public static void main(String[] args) {
        NullPoint nullPoint = new NullPoint();
        System.out.println( 1== nullPoint.y);
        System.out.println(nullPoint.getY() == null);
    }
}
