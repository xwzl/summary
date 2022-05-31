package com.java.interview.java.report.handler.nice;

import com.java.interview.java.report.handler.nice.Nice;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author xuweizhi
 * @since 2022/05/31 15:05
 */
public class LocalDateToString implements Nice<String, LocalDate> {

    private final DateTimeFormatter formatter;

    public LocalDateToString(String pattern) {
        this.formatter = DateTimeFormatter.ofPattern(pattern);
    }

    @Override
    public String baby(LocalDate localDate) {
        return localDate.format(formatter);
    }
}
