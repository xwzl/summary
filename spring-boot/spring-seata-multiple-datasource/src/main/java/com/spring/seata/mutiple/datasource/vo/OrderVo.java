package com.spring.seata.mutiple.datasource.vo;

import lombok.Data;

/**
 * @author xuweizhi
 */
@Data
public class OrderVo {
    private String userId;
    /**商品编号**/
    private String commodityCode;

    private Integer count;

    private Integer money;
}
