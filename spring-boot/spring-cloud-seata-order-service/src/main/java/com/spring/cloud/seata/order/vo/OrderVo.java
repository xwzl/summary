package com.spring.cloud.seata.order.vo;

import lombok.Data;

/**
 * @author xuweizhi
 */
@Data
public class OrderVo {

    private String userId;
    /**ååįžå·**/
    private String commodityCode;

    private Integer count;

    private Integer money;
}
