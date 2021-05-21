package com.turing.java.refactor;

/**
 * @author xuweizhi
 * @since 2020/09/25 15:37
 */
public interface Price {

    int CHILDREN = 2;
    int REGULAR = 0;
    int NEW_RELEASE = 1;

    /**
     * 得到电影的价格
     *
     * @return 返回值
     */
    int getPriceCode();

    /**
     * 得到电影对应的价格
     *
     * @param daysRented 对应的价格
     * @return 返回值
     */
    double getCharge(int daysRented);

    default int getFrequentRenterPoint(int daysRented) {
        return 1;
    }
    //default double getCharge(int daysRented) {
    //    double result = 0;
    //    switch (getPriceCode()) {
    //        case Price.REGULAR:
    //            result += 2;
    //            if (daysRented > 2) {
    //                result += (daysRented - 2) * 1.5;
    //            }
    //            break;
    //        case Price.NEW_RELEASE:
    //            result += daysRented * 3;
    //            break;
    //        case Price.CHILDREN:
    //            result += 1.5;
    //            if (daysRented > 3) {
    //                result += (daysRented - 3) * 1.5;
    //            }
    //            break;
    //        default:
    //            throw new RuntimeException("未找到对应影片信息");
    //    }
    //    return result;
    //}
}
