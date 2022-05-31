package com.java.interview.java.report.enums;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author xuweizhi
 * @since 2022/05/31 14:23
 */
@Getter
@SuppressWarnings("all")
public enum TypeEnum {

    STRING(String.class.getName()),
    DATE(Date.class.getName()),
    LOCALDATE(LocalDate.class.getName()),
    LOCALDATETIME(LocalDateTime.class.getName()),
    BIGDECIMAL(BigDecimal.class.getName()),
    NUMBER(Number.class.getName());

    private String code;

    TypeEnum(String code) {
        this.code = code;
    }
}
