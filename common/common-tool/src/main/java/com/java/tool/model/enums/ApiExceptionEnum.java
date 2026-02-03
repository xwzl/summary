package com.java.tool.model.enums;


/**
 * 公用返回枚举
 *
 * @author xuweizhi
 * @since 2021/05/25 14:05
 */
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

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
