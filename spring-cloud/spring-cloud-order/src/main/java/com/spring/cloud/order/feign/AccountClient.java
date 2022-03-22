package com.spring.cloud.order.feign;

import com.spring.cloud.commom.account.dto.AccountDTO;
import com.spring.cloud.commom.account.dto.AccountNestedDTO;
import org.dromara.hmily.annotation.Hmily;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * The interface Account client.
 *
 * @author xuweizhi
 */
@FeignClient(value = "account-service")
public interface AccountClient {

    /**
     * 用户账户付款.
     *
     * @param accountDO 实体类
     * @return true 成功
     */
    @RequestMapping("/account-service/account/payment")
    @Hmily
    Boolean payment(@RequestBody AccountDTO accountDO);

    /**
     * Test payment boolean.
     *
     * @param accountDO the account do
     * @return the boolean
     */
    @RequestMapping("/account-service/account/testPayment")
    Boolean testPayment(@RequestBody AccountDTO accountDO);

    /**
     * 获取用户账户信息.
     *
     * @param userId 用户id
     * @return AccountDO big decimal
     */
    @RequestMapping("/account-service/account/findByUserId")
    BigDecimal findByUserId(@RequestParam("userId") String userId);

    /**
     * Mock with try exception boolean.
     *
     * @param accountDO the account do
     * @return the boolean
     */
    @Hmily
    @RequestMapping("/account-service/account/mockWithTryException")
    Boolean mockWithTryException(@RequestBody AccountDTO accountDO);

    /**
     * Mock with try timeout boolean.
     *
     * @param accountDO the account do
     * @return the boolean
     */
    @Hmily
    @RequestMapping("/account-service/account/mockWithTryTimeout")
    Boolean mockWithTryTimeout(@RequestBody AccountDTO accountDO);

    /**
     * Payment with nested boolean.
     *
     * @param nestedDTO the nested dto
     * @return the boolean
     */
    @Hmily
    @RequestMapping("/account-service/account/paymentWithNested")
    Boolean paymentWithNested(@RequestBody AccountNestedDTO nestedDTO);

    /**
     * Payment with nested exception boolean.
     *
     * @param nestedDTO the nested dto
     * @return the boolean
     */
    @Hmily
    @RequestMapping("/account-service/account/paymentWithNestedException")
    Boolean paymentWithNestedException(@RequestBody AccountNestedDTO nestedDTO);
}
