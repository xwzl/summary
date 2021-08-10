package com.spring.cloud.feign.vo;


import lombok.Data;

import java.io.Serializable;

/**
 * @author fox
 */
@Data
public class OrderVo implements Serializable {


    private Integer userId;

    private String commodityCode;

    private Integer count;

    private Integer amount;


}
