package com.spring.cloud.seata.datasource.entity;

import lombok.Data;

/**
 * @author xuweizhi
 */
@Data
public class Order {
    private Integer id;
    
    private String userId;
    /** ååįžå· */
    private String commodityCode;
    
    private Integer count;
    
    private Integer money;
    
    private Integer status;
}
