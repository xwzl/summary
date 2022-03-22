package com.spring.rocket.provider.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.spring.rocket.provider.entity.AccountChangeEvent;
import com.spring.rocket.provider.mapper.AccountMapper;
import com.spring.rocket.provider.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
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

    @Resource
    private RocketMQTemplate rocketMQTemplate;


    /**
     * 向mq发送转账消息
     *
     * @param accountChangeEvent
     */
    @Override
    public void sendTansferAccount(AccountChangeEvent accountChangeEvent) {
        //将accountChangeEvent转成json
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("accountChange", accountChangeEvent);
        //生成message类型
        Message<String> message = MessageBuilder.withPayload(jsonObject.toJSONString()).build();
        /**
         * 发送一条事务消息
         * String txProducerGroup:  生产组
         * String destination:  topic
         * Message<?> message: 消息内容
         * Object arg: 参数
         */
        rocketMQTemplate.sendMessageInTransaction("topic_txmsg", message, null);
    }

    /**
     * 更新账户，扣减金额
     * //本地事务
     *
     * @param accountChangeEvent
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reduceAccountBalance(AccountChangeEvent accountChangeEvent) {
        log.info("bank1更新本地账号，账号：{},金额：{}", accountChangeEvent.getFromAccountNo(), accountChangeEvent.getAmount());
        //幂等判断
        if (accountMapper.isExistTx(accountChangeEvent.getTxNo()) > 0) {
            return;
        }
        //扣减金额
        accountMapper.subtractAccountBalance(accountChangeEvent.getFromAccountNo(), accountChangeEvent.getAmount());
        //添加事务日志
        accountMapper.addTx(accountChangeEvent.getTxNo());
        if (accountChangeEvent.getAmount() == 20) {
            throw new RuntimeException("模拟异常");
        }

    }
}
