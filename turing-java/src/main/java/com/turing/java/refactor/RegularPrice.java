package com.turing.java.refactor;

/**
 * @author xuweizhi
 * @since 2020/09/25 15:40
 */
public class RegularPrice implements Price {

    @Override
    public int getPriceCode() {
        return REGULAR;
    }

    @Override
    public double getCharge(int daysRented) {
        double result = 2;
        if (daysRented > 2) {
            result += (daysRented - 2) * 1.5;
        }
        return result;
    }
}
