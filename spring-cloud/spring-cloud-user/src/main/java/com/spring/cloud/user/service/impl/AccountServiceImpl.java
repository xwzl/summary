package com.spring.cloud.user.service.impl;

import com.spring.cloud.commom.enums.TransactionEnum;
import com.spring.cloud.user.controller.request.AccountRequest;
import com.spring.cloud.user.feign.BankFeignClient;
import com.spring.cloud.user.feign.dtos.AccountTransferDTO;
import com.spring.cloud.user.mapper.AccountMapper;
import com.spring.cloud.user.service.AccountService;
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

    @Resource
    private BankFeignClient bankFeignClient;


    /**
     * try方法执行逻辑：
     * 1.try幂等校验
     * 2.try悬挂处理
     * 3.检查余额是否足够扣减
     * 4.扣减金额
     * 只要标记@Hmily就是try方法，在注解中指定confirm、cancel两个方法的名字
     *
     * @param accountRequest 请求对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @HmilyTCC(confirmMethod = "commit", cancelMethod = "rollback")
    public void transfer(AccountRequest accountRequest) {
        //获取全局事务id
        accountRequest.setGlobalTranceId(HmilyContextHolder.get().getTransId().toString());
        log.info("bank1 try begin 开始执行...xid:{}", accountRequest.getGlobalTranceId());
        //幂等判断 判断local_transaction_log表中是否有try日志记录，如果有则不再执行
        if (accountMapper.isExistTransactionLogByType(accountRequest.getGlobalTranceId(), TransactionEnum.TRY.getValue()) > 0) {
            log.info("bank1 try 已经执行，无需重复执行,xid:{}", accountRequest.getGlobalTranceId());
            return;
        }

        //try悬挂处理，如果cancel、confirm有一个已经执行了，try不再执行
        if (accountMapper.isExistTransactionLogByType(accountRequest.getGlobalTranceId(), TransactionEnum.CONFIRM.getValue()) > 0
                || accountMapper.isExistTransactionLogByType(accountRequest.getGlobalTranceId(), TransactionEnum.CANCEL.getValue()) > 0) {
            log.info("bank1 try悬挂处理  cancel或confirm已经执行，不允许执行try,xid:{}", accountRequest.getGlobalTranceId());
            return;
        }

        //扣减金额
        if (accountMapper.subtractAccountBalance(accountRequest.getFromAccountNo(), accountRequest.getAmount()) <= 0) {
            //扣减失败
            throw new RuntimeException("bank1 try 扣减金额失败,xid:" + accountRequest.getGlobalTranceId());
        }
        //插入try执行记录,用于幂等判断
        accountMapper.addTransactionLog(accountRequest.getGlobalTranceId(), TransactionEnum.TRY.getValue());

        //转账,远程调用bank2
        AccountTransferDTO accountTransfer = new AccountTransferDTO();
        accountTransfer.setAccountNo(accountRequest.getToAccountNo());
        accountTransfer.setAmount(accountRequest.getAmount());
        if (!bankFeignClient.transferTo(accountTransfer)) {
            throw new RuntimeException("bank1 远程调用bank2微服务失败,xid:" + accountRequest.getGlobalTranceId());
        }
        if (accountRequest.getAmount() == 20) {
            throw new RuntimeException("人为制造异常,xid:" + accountRequest.getGlobalTranceId());
        }
        log.info("bank1 try end 结束执行...xid:{}", accountRequest.getGlobalTranceId());

    }

    @Transactional(rollbackFor = Exception.class)
    public void commit(AccountRequest accountRequest) {
        //获取全局事务id
        log.info("bank1 confirm begin 开始执行...xid:{},accountNo:{},amount:{}", accountRequest.getGlobalTranceId(), accountRequest.getFromAccountNo(), accountRequest.getAmount());
    }


    /**
     * cancel方法执行逻辑：
     * 1. cancel 幂等校验
     * 2. cancel 空回滚处理
     * 3. 增加可用余额
     *
     * @param accountRequest 账号
     */
    @Transactional(rollbackFor = Exception.class)
    public void rollback(AccountRequest accountRequest) {
        //获取全局事务id

        String globalTranceId = accountRequest.getGlobalTranceId();
        log.info("bank1 cancel begin 开始执行...xid:{}", globalTranceId);
        //	cancel幂等校验
        if (accountMapper.isExistTransactionLogByType(globalTranceId, TransactionEnum.CANCEL.getValue()) > 0) {
            log.info("bank1 cancel 已经执行，无需重复执行,xid:{}", globalTranceId);
            return;
        }
        //cancel空回滚处理，如果try没有执行，cancel不允许执行
        if (accountMapper.isExistTransactionLogByType(globalTranceId, TransactionEnum.TRY.getValue()) <= 0) {
            log.info("bank1 空回滚处理，try没有执行，不允许cancel执行,xid:{}", globalTranceId);
            return;
        }
        //	增加可用余额
        accountMapper.addAccountBalance(accountRequest.getFromAccountNo(), accountRequest.getAmount());
        //插入一条cancel的执行记录
        accountMapper.addTransactionLog(globalTranceId, TransactionEnum.CANCEL.getValue());
        log.info("bank1 cancel end 结束执行...xid:{}", globalTranceId);

    }
}
