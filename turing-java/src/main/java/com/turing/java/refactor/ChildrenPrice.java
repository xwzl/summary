package com.turing.java.refactor;

/**
 * 儿童片
 *
 * @author xuweizhi
 * @since 2020/09/25 15:38
 */
public class ChildrenPrice implements Price {

    @Override
    public int getPriceCode() {
        return CHILDREN;
    }

    @Override
    public double getCharge(int daysRented) {
        double result = 1.5;
        if (daysRented > 3) {
            result += (daysRented - 3) * 1.5;
        }
        return result;
    }

}
