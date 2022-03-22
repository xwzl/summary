package com.spring.rocket.provider.service;

import com.spring.rocket.provider.entity.AccountChangeEvent;

public interface AccountService {

    /**
     * 向mq发送转账消息
     *
     * @param accountChangeEvent
     */
    public void sendTansferAccount(AccountChangeEvent accountChangeEvent);

    /**
     * 更新账户，扣减金额
     *
     * @param accountChangeEvent
     */
    public void reduceAccountBalance(AccountChangeEvent accountChangeEvent);
}
