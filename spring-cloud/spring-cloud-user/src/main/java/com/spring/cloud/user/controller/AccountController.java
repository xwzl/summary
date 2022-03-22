
package com.spring.cloud.user.controller;

import com.spring.cloud.commom.account.dto.HmilyAccountDTO;
import com.spring.cloud.commom.account.dto.AccountNestedDTO;
import com.spring.cloud.user.service.HmilyAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * AccountController.
 *
 * @author xuweizhi
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    private final HmilyAccountService hmilyAccountService;

    @Autowired
    public AccountController(HmilyAccountService hmilyAccountService) {
        this.hmilyAccountService = hmilyAccountService;
    }

    @RequestMapping("/payment")
    public Boolean payment(@RequestBody HmilyAccountDTO accountDO) {
        return hmilyAccountService.payment(accountDO);
    }

    @RequestMapping("/testPayment")
    public Boolean testPayment(@RequestBody HmilyAccountDTO accountDO) {
        return hmilyAccountService.testPayment(accountDO);
    }

    @RequestMapping("/mockWithTryException")
    public Boolean mockWithTryException(@RequestBody HmilyAccountDTO accountDO) {
        return hmilyAccountService.mockWithTryException(accountDO);
    }

    @RequestMapping("/mockWithTryTimeout")
    public Boolean mockWithTryTimeout(@RequestBody HmilyAccountDTO accountDO) {
        return hmilyAccountService.mockWithTryTimeout(accountDO);
    }

    @RequestMapping("/paymentWithNested")
    public Boolean paymentWithNested(@RequestBody AccountNestedDTO nestedDTO) {
        return hmilyAccountService.paymentWithNested(nestedDTO);
    }

    @RequestMapping("/paymentWithNestedException")
    public Boolean paymentWithNestedException(@RequestBody AccountNestedDTO nestedDTO) {
        return hmilyAccountService.paymentWithNestedException(nestedDTO);
    }

    @RequestMapping("/findByUserId")
    public BigDecimal findByUserId(@RequestParam("userId") String userId) {
        return hmilyAccountService.findByUserId(userId).getBalance();
    }
}
