package com.java.interview.java.report.enums;

import lombok.Getter;

/**
 * @author xuweizhi
 * @since 2022/05/31 14:23
 */
@Getter
public enum SystemConfigEnum {
    /**
     * 系统内置模板类型
     */
    SYSTEM_TEMPLATE_TYPE(0, ""),
    SYSTEM_UNIT_CUSTOMIZE(1, "定制组合类型"),

    SYSTEM_UNIT_HEALTHCARE_RECORD(2, "诊疗记录");

    private final Integer code;
    private final String message;

    SystemConfigEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
