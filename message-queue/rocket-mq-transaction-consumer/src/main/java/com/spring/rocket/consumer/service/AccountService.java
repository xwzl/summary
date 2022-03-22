package com.spring.rocket.consumer.service;

import com.spring.rocket.consumer.entity.AccountChangeEvent;

/**
 * @author Fox
 */
public interface AccountService {

    /**
     * 更新账户，增加金额
     */
    public void addAccountBalance(AccountChangeEvent accountChangeEvent);

}
