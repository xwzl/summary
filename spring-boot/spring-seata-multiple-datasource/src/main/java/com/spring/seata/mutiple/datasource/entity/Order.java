package com.spring.seata.mutiple.datasource.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author xuweizhi
 */
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order {

    private Integer id;

    private String userId;
    /**
     * ååįžå·
     */
    private String commodityCode;

    private Integer count;

    private Integer money;

    private Integer status;
}
