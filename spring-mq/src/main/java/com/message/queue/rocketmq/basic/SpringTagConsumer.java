package com.message.queue.rocketmq.basic;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(consumerGroup = "testTag", topic = "TestTopic", selectorExpression = "testTag")
public class SpringTagConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        System.out.println("Received message testTag : " + message);
    }

}
