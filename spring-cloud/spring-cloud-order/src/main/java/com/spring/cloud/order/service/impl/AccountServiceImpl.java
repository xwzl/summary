package com.spring.cloud.order.service.impl;

import com.spring.cloud.commom.enums.TransactionEnum;
import com.spring.cloud.order.controller.param.AccountRequest;
import com.spring.cloud.order.dao.AccountMapper;
import com.spring.cloud.order.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.HmilyTCC;
import org.dromara.hmily.core.context.HmilyContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Tcc 事务
 *
 * @author xuweizhi
 * @since 2022/03/22 00:42
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    @HmilyTCC(confirmMethod = "confirmMethod", cancelMethod = "cancelMethod")
    public void transferTo(AccountRequest accountRequest) {
        //获取全局事务id
        accountRequest.setGlobalTranceId(HmilyContextHolder.get().getTransId().toString());
        log.info("bank2 try begin 开始执行...xid:{}", accountRequest.getGlobalTranceId());
        if (accountRequest.getAmount() == 21) {
            try {
                Thread.sleep(610000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // throw new RuntimeException("超时验证,悬挂（try 之前 cancel ）验证");
        }
        log.info("bank2 try end 结束执行...xid:{}", accountRequest.getGlobalTranceId());
    }

    /**
     * confirm方法:
     * 1.confirm幂等校验
     * 2.正式增加金额
     *
     * @param accountRequest //
     */
    @Transactional(rollbackFor = Exception.class)
    public void confirmMethod(AccountRequest accountRequest) {
        //获取全局事务id
        log.info("bank2 confirm begin 开始执行...xid:{}", accountRequest.getGlobalTranceId());
        if (accountMapper.isExistTransactionLogByType(accountRequest.getGlobalTranceId(), TransactionEnum.CONFIRM.getValue()) > 0) {
            log.info("bank2 confirm 已经执行，无需重复执行...xid:{}", accountRequest.getGlobalTranceId());
            return;
        }
        //增加金额
        accountMapper.addAccountBalance(accountRequest.getAccountNo(), accountRequest.getAmount());
        //增加一条confirm日志，用于幂等校验
        accountMapper.addTransactionLog(accountRequest.getGlobalTranceId(), TransactionEnum.CONFIRM.getValue());
        log.info("bank2 confirm end 结束执行...xid:{}", accountRequest.getGlobalTranceId());
    }

    /**
     * 取消接口
     *
     * @param accountRequest //
     */
    public void cancelMethod(AccountRequest accountRequest) {
        //获取全局事务id
        log.info("bank2 cancel begin 开始执行...xid:{}", accountRequest.getGlobalTranceId());

    }
}
