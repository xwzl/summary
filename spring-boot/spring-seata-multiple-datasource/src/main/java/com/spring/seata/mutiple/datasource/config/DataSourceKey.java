package com.spring.seata.mutiple.datasource.config;

import lombok.Getter;

/**
 * @author xuweizhi
 */
@Getter
public enum DataSourceKey {
    /**
     * Order data source key.
     */
    ORDER,
    /**
     * Storage data source key.
     */
    STORAGE,
    /**
     * Account data source key.
     */
    ACCOUNT,
}
