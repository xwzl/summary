package com.spring.cloud.seata.datasource.entity;

import lombok.Data;


/**
 * @author xuweizhi
 */
@Data
public class Account {
    private Integer id;
    
    private String userId;
    
    private Integer money;
}
