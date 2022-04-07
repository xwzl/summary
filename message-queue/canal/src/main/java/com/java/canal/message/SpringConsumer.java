package com.java.canal.message;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * Canal Message 消费
 *
 * @author xuweizhi
 * @since 2022/04/07 11:44
 */
@Component
@RocketMQMessageListener(consumerGroup = "my", topic = "test_es_test")
public class SpringConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        System.out.println("Received message : " + message);
    }

}
