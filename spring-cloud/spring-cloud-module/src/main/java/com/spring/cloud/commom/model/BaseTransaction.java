package com.spring.cloud.commom.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 全局事务基础数据
 *
 * @author xuweizhi
 * @since 2022/03/22 14:19
 */
@Data
public class BaseTransaction implements Serializable {

    /**
     * 全局事务 id
     */
    private String globalTranceId;

}
