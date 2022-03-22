package com.spring.cloud.user.service;

import com.spring.cloud.user.controller.request.AccountRequest;

/**
 * @author xuweizhi
 */
public interface AccountService {

    /**
     * 转账
     *
     * @param accountRequest 支付
     */
    void transfer(AccountRequest accountRequest);
}
