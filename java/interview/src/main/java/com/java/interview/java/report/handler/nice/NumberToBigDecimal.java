package com.java.interview.java.report.handler.nice;

import com.java.interview.java.report.handler.nice.Nice;

import java.math.BigDecimal;

/**
 * @author xuweizhi
 * @since 2022/05/31 15:05
 */
public class NumberToBigDecimal implements Nice<BigDecimal, Number> {

    @Override
    public BigDecimal baby(Number number) {
        return new BigDecimal(number.toString());
    }
}
