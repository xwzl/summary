package com.turing.java.refactor;

import lombok.Data;

/**
 * 租赁
 *
 * @author xuweizhi
 * @since 2020/09/25 10:53
 */
@Data
public class Rental {

    Movie movie;

    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public double getCharge() {
        return movie.getCharge(daysRented);
    }

    public int getFrequentRenterPoint() {
        return movie.getPrice().getFrequentRenterPoint(daysRented);
    }
}
