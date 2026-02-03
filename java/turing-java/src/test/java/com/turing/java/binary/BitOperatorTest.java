package com.turing.java.binary;

import org.junit.jupiter.api.Test;

/**
 * 位运算的妙用
 *
 * @author xuweizhi
 * @since 2020/11/09 9:52
 */
public class BitOperatorTest {

    @Test
    public void intPosition() {
        //int max = Integer.MAX_VALUE;
        int max = 257;
        for (int i = 0; i < 10; i++) {
            System.out.print(max >> i & 1);
        }
        System.out.println("");
        int mav = 0b100000001;
        System.out.println(mav);
        //Integer.valueOf(257).
        System.out.println(Integer.toString(257, 2));
    }
}
