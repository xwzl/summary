package com.spring.cloud.order.service;

import com.spring.cloud.order.controller.param.AccountRequest;

public interface AccountService {

    /**
     * 增加账户余额
     *
     * @param accountRequest 账户
     */
    void transferTo(AccountRequest accountRequest);
}
