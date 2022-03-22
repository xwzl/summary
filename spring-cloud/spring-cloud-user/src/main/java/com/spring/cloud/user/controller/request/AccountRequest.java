package com.spring.cloud.user.controller.request;

import com.spring.cloud.commom.model.BaseTransaction;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 支付
 *
 * @author xuweizhi
 * @since 2022/03/22 14:01
 */
@Data
@ApiModel(description = "支付")
public class AccountRequest extends BaseTransaction implements Serializable {

    /**
     * 转账人
     */
    private String fromAccountNo;

    /**
     * 收账人
     */
    private String toAccountNo;

    /**
     * 金额
     */
    private Double amount;

}
