package com.spring.cloud.order.controller.param;

import com.spring.cloud.commom.model.BaseTransaction;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 支付
 *
 * @author xuweizhi
 * @since 2022/03/22 14:01
 */
@Data
@ApiModel(description = "支付")
@EqualsAndHashCode(callSuper = true)
public class AccountRequest extends BaseTransaction implements Serializable {

    /**
     * 转账人
     */
    private String accountNo;

    /**
     * 金额
     */
    private Double amount;

}
