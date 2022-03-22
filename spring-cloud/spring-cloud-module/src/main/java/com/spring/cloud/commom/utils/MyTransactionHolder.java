package com.spring.cloud.commom.utils;

/**
 * @author xuweizhi
 * @since 2022/03/22 21:44
 */
public class MyTransactionHolder {

    private static final ThreadLocal<String> CURRENT_LOCAL = new ThreadLocal<>();

    /**
     * set value.
     *
     * @param transId 事务id
     */
    public static void set(final String transId) {
        CURRENT_LOCAL.set(transId);
    }

    /**
     * get value.
     *
     * @return TccTransactionContext
     */
    public static String get() {
        return CURRENT_LOCAL.get();
    }

    /**
     * clean threadLocal for gc.
     */
    public static void remove() {
        CURRENT_LOCAL.remove();
    }
}
