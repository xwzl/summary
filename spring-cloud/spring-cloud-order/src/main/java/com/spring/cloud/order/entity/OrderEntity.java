package com.spring.cloud.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xuweizhi
 */
@Data
@TableName("t_order")
public class OrderEntity implements Serializable {

    @TableId
    private Integer id;

    private String userId;
    /**
     * 商品编号
     */
    private String commodityCode;

    private Integer count;

    private Integer amount;
}
