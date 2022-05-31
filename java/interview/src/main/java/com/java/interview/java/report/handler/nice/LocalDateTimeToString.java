package com.java.interview.java.report.handler.nice;

import com.java.interview.java.report.handler.nice.Nice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author xuweizhi
 * @since 2022/05/31 15:05
 */
public class LocalDateTimeToString implements Nice<String, LocalDateTime> {

    private final DateTimeFormatter formatter;

    public LocalDateTimeToString(String pattern) {
        this.formatter = DateTimeFormatter.ofPattern(pattern);
    }

    @Override
    public String baby(LocalDateTime localDate) {
        return localDate.format(formatter);
    }
}
