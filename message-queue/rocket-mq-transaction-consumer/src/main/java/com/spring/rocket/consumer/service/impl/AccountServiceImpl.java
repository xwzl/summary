package com.spring.rocket.consumer.service.impl;

import com.spring.rocket.consumer.entity.AccountChangeEvent;
import com.spring.rocket.consumer.mapper.AccountMapper;
import com.spring.rocket.consumer.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Fox
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    /**
     * 更新账户，增加金额
     *
     * @param accountChangeEvent //
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addAccountBalance(AccountChangeEvent accountChangeEvent) {
        log.info("bank2更新本地账号，账号：{},金额：{}", accountChangeEvent.getToAccountNo(), accountChangeEvent.getAmount());
        //幂等性校验
        if (accountMapper.isExistTx(accountChangeEvent.getTxNo()) > 0) {
            return;
        }
        //增加金额
        accountMapper.addAccountBalance(accountChangeEvent.getToAccountNo(), accountChangeEvent.getAmount());
        //添加事务记录，用于幂等
        accountMapper.addTx(accountChangeEvent.getTxNo());

        if (accountChangeEvent.getAmount() == 30) {
            throw new RuntimeException("模拟异常");
        }

    }
}
