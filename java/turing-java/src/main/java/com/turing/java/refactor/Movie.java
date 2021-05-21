package com.turing.java.refactor;

/**
 * 电影
 *
 * @author xuweizhi
 * @since 2020/09/25 10:51
 */
public class Movie {

    private String title;

    Price price;

    public Movie(String title, Price price) {
        this.title = title;
        //setPriceCode(priceCode);
        this.price = price;
    }

    public Price getPrice() {
        return price;
    }

    // 多态干掉 switch
    private void setPriceCode(int priceCode) {
        switch (priceCode) {
            case Price.REGULAR:
                price = new RegularPrice();
                break;
            case Price.NEW_RELEASE:
                price = new NewReleasePrice();
                break;
            case Price.CHILDREN:
                price = new ChildrenPrice();
                break;
            default:
                throw new IllegalArgumentException("Incorrect Price code");
        }
    }

    public double getCharge(int daysRented) {
        return price.getCharge(daysRented);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPriceCode() {
        return price.getPriceCode();
    }

}
