/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.rocketmq.example.quickstart;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.example.untils.IpAddressConfig;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This example shows how to subscribe and consume messages using providing {@link DefaultMQPushConsumer}.
 */
@Slf4j
public class Consumer {

    public static Map<String, Integer> countMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException, MQClientException {

        for (int j = 0; j < 3; j++) {
            /*
             * Instantiate with specified consumer group name.
             */
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("death_queue" + j);

            /*
             * Specify name server addresses.
             * <p/>
             *
             * Alternatively, you may specify name server addresses via exporting environmental variable: NAMESRV_ADDR
             * <pre>
             * {@code
             * consumer.setNamesrvAddr("name-server1-ip:9876;name-server2-ip:9876");
             * }
             * </pre>
             */
            consumer.setNamesrvAddr(IpAddressConfig.getRabbitMqAddress());
            /*
             * Specify where to start in case the specified consumer group is a brand new one.
             */
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

            /*
             * Subscribe one more more topics to consume.
             */
            consumer.subscribe("TopicTest" + j, "*");

            consumer.setMaxReconsumeTimes(2);

            consumer.setConsumeThreadMin(4);
            consumer.setConsumeThreadMax(10);
            /*
             *  Register callback to execute on arrival of messages fetched from brokers.
             */
            consumer.registerMessageListener(new MessageListenerConcurrently() {

                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    for (MessageExt msg : msgs) {
                        String keys = msg.getKeys();
                        Integer orDefault = countMap.getOrDefault(keys, 0);
                        countMap.put(keys, ++orDefault);
                        if (keys.startsWith("death")) {
                            log.info("第 {} 次尝试 {}", orDefault, new String(msg.getBody()));
                            throw new RuntimeException("死信队列固定异常");
                        }
                        // log.info("消费成功:{}", new String(msg.getBody()));
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });

            /*
             *  Launch the consumer instance.
             */
            consumer.start();

            System.out.printf("Consumer Started.%n");
        }
    }

}
