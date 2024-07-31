package com.spring.rocket.provider.controller;

import com.spring.rocket.provider.entity.AccountChangeEvent;
import com.spring.rocket.provider.service.AccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.util.UUID;

@RestController
@RequestMapping("/provider")
public class ProviderController {

    @Resource
    private AccountService accountService;

    /**
     * 转账接口
     *
     * @param fromAccountNo 转出账户
     * @param toAccountNo   转入账户
     * @param amount        转账的金额
     * @return //
     */
    @RequestMapping("/transfer")
    public String transfer(@RequestParam("fromAccountNo") String fromAccountNo,
                           @RequestParam("toAccountNo") String toAccountNo,
                           @RequestParam("amount") Double amount) {

        //创建一个事务id，作为消息内容发到mq  全局唯一id
        String txNo = UUID.randomUUID().toString();
        AccountChangeEvent accountChangeEvent = new AccountChangeEvent(fromAccountNo, toAccountNo, amount, txNo);
        //发送消息
        accountService.sendTansferAccount(accountChangeEvent);
        return "ok";
    }

}
