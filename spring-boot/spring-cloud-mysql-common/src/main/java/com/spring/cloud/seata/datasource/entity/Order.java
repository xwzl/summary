package com.spring.cloud.seata.datasource.entity;

import lombok.Data;

/**
 * @author xuweizhi
 */
@Data
public class Order {
    private Integer id;
    
    private String userId;
    /** 商品编号 */
    private String commodityCode;
    
    private Integer count;
    
    private Integer money;
    
    private Integer status;
}
