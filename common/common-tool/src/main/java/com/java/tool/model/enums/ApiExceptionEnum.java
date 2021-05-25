package com.java.tool.model.enums;

import lombok.Getter;

/**
 * 公用返回枚举
 *
 * @author xuweizhi
 * @since 2021/05/25 14:05
 */
@Getter
public enum ApiExceptionEnum {
    /**
     * 失败
     */
    FAILURE(-1,"failure"),
    /**
     * 成功
     */
    SUCCESS(0,"success");

    /**
     * 状态码
     */
    private final Integer status;
    /**
     * 状态码信息
     */
    private final String message;

    ApiExceptionEnum(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
