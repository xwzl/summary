package com.spring.cloud.commom.enums;

/**
 * Tcc 事务枚举
 *
 * @author xuweizhi
 */
public enum TransactionEnum {

    /**
     * 尝试
     */
    TRY(1),
    /**
     * 确认
     */
    CONFIRM(2),
    /**
     * 取消
     */
    CANCEL(3);

    private final int value;

    TransactionEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
