package com.turing.java.refactor;

/**
 * @author xuweizhi
 * @since 2020/09/25 15:39
 */
public class NewReleasePrice implements Price {

    @Override
    public int getPriceCode() {
        return NEW_RELEASE;
    }

    @Override
    public double getCharge(int daysRented) {
        return daysRented * 3;
    }

    @Override
    public int getFrequentRenterPoint(int daysRented) {
        return daysRented > 1 ? 2 : 1;
    }
}
