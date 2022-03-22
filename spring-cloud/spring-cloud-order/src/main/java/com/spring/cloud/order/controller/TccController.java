package com.spring.cloud.order.controller;

import com.spring.cloud.order.controller.param.AccountRequest;
import com.spring.cloud.order.service.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Tcc 事务
 *
 * @author xuweizhi
 * @since 2022/03/22 00:42
 */
@RestController
@RequestMapping("/bank2")
public class TccController {

    @Resource
    private AccountService accountService;

    /**
     * 转账接口
     *
     * @param accountRequest 转账
     * @return 返回值
     */
    @PostMapping("/transferTo")
    public Boolean transferTo(@RequestBody AccountRequest accountRequest) {
        accountService.transferTo(accountRequest);
        return true;
    }

}
