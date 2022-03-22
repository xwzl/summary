package com.spring.cloud.user.service;

import com.spring.cloud.commom.account.dto.AccountDTO;
import com.spring.cloud.commom.account.dto.AccountNestedDTO;
import com.spring.cloud.commom.account.entity.AccountDO;

/**
 * @author xuweizhi
 */
public interface HmilyAccountService {

    /**
     * 扣款支付.
     *
     * @param accountDTO 参数dto
     * @return true boolean
     */
    boolean payment(AccountDTO accountDTO);

    /**
     * Test payment boolean.
     *
     * @param accountDTO the account dto
     * @return the boolean
     */
    boolean testPayment(AccountDTO accountDTO);

    /**
     * Mock with try exception boolean.
     *
     * @param accountDTO the account dto
     * @return the boolean
     */
    boolean mockWithTryException(AccountDTO accountDTO);

    /**
     * Mock with try timeout boolean.
     *
     * @param accountDTO the account dto
     * @return the boolean
     */
    boolean mockWithTryTimeout(AccountDTO accountDTO);

    /**
     * Payment with nested boolean.
     *
     * @param nestedDTO the nested dto
     * @return the boolean
     */
    boolean paymentWithNested(AccountNestedDTO nestedDTO);

    /**
     * Payment with nested exception boolean.
     *
     * @param nestedDTO the nested dto
     * @return the boolean
     */
    boolean paymentWithNestedException(AccountNestedDTO nestedDTO);

    /**
     * 获取用户账户信息.
     *
     * @param userId 用户id
     * @return AccountDO account do
     */
    AccountDO findByUserId(String userId);

}
