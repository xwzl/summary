package com.java.interview.java.report.enums;

import lombok.Getter;

/**
 * 处理链路枚举
 *
 * @author xuweizhi
 * @since 2022/05/30 16:53
 */
@Getter
public enum HandlerEnum {

    /**
     * 诊疗记录
     */
    COMMON_PARAM("param", "公用参数枚举"),
    HEALTHCARE_RECORD("healthcare_record", "诊疗记录"),

    AREA_LOG("area_log", "病区日志");

    public final String key;

    public final String param;

    HandlerEnum(String key, String param) {
        this.key = key;
        this.param = param;
    }
}
