package com.spring.rocket.consumer.message;

import com.alibaba.fastjson.JSONObject;
import com.spring.rocket.consumer.entity.AccountChangeEvent;
import com.spring.rocket.consumer.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

@Component
@Slf4j
@RocketMQMessageListener(consumerGroup = "${provider.consumer.group}", topic = "${provider.consumer.topic}")
public class TxmsgConsumer implements RocketMQListener<String> {

    @Resource
    private AccountService accountService;

    /**
     * 接收消息
     */
    @Override
    public void onMessage(String message) {
        log.info("开始消费消息:{}", message);

        //解析消息
        JSONObject jsonObject = JSONObject.parseObject(message);
        String accountChangeString = jsonObject.getString("accountChange");
        //转成AccountChangeEvent
        AccountChangeEvent accountChangeEvent = JSONObject.parseObject(accountChangeString, AccountChangeEvent.class);
        //更新本地账户，增加金额
        accountService.addAccountBalance(accountChangeEvent);

    }
}
