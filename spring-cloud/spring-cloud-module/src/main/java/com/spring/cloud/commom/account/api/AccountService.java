
package com.spring.cloud.commom.account.api;

import com.spring.cloud.commom.account.dto.HmilyAccountDTO;
import com.spring.cloud.commom.account.dto.AccountNestedDTO;
import com.spring.cloud.commom.account.entity.HmilyAccountDO;
import org.dromara.hmily.annotation.Hmily;

/**
 * The interface Account service.
 *
 * @author xuweizhi
 */
public interface AccountService {

    /**
     * 扣款支付
     *
     * @param accountDTO 参数dto
     */
    @Hmily
    boolean payment(HmilyAccountDTO accountDTO);

    /**
     * Mock try payment exception.
     *
     * @param accountDTO the account dto
     */
    @Hmily
    boolean mockTryPaymentException(HmilyAccountDTO accountDTO);

    /**
     * Mock try payment timeout.
     *
     * @param accountDTO the account dto
     */
    @Hmily
    boolean mockTryPaymentTimeout(HmilyAccountDTO accountDTO);

    /**
     * Test payment boolean.
     *
     * @param accountDTO the account dto
     * @return the boolean
     */
    @Hmily
    boolean testPayment(HmilyAccountDTO accountDTO);

    /**
     * 扣款支付
     *
     * @param accountNestedDTO 参数dto
     * @return true boolean
     */
    @Hmily
    boolean paymentWithNested(AccountNestedDTO accountNestedDTO);

    /**
     * Payment with nested exception boolean.
     *
     * @param accountNestedDTO the account nested dto
     * @return the boolean
     */
    @Hmily
    boolean paymentWithNestedException(AccountNestedDTO accountNestedDTO);

    /**
     * 获取用户账户信息
     *
     * @param userId 用户id
     * @return AccountDO account do
     */
    HmilyAccountDO findByUserId(String userId);
}
