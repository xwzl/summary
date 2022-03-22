package com.spring.cloud.user.feign.dtos;

import com.spring.cloud.commom.model.BaseTransaction;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 账户 DTO
 *
 * @author xuweizhi
 * @since 2022/03/22 14:30
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AccountTransferDTO extends BaseTransaction {

    /**
     * 转账人
     */
    private String accountNo;

    /**
     * 金额
     */
    private Double amount;
}
