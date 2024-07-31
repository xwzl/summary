package com.spring.cloud.user.controller;

import com.spring.cloud.user.controller.request.AccountRequest;
import com.spring.cloud.user.service.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

/**
 * Tcc 事务
 *
 * @author xuweizhi
 * @since 2022/03/22 00:42
 */
@RestController
@RequestMapping("/bank")
public class TccController {

    @Resource
    private AccountService accountService;

    /**
     * 转账接口
     *
     * @param accountRequest 支付对象
     * @return 返回值
     */
    @PostMapping("/transfer")
    public Boolean transfer(@RequestBody AccountRequest accountRequest) {
        accountService.transfer(accountRequest);
        return true;
    }
}
