package com.message.queue.example;

import com.message.queue.constant.RabbitConstant;
import com.message.queue.util.RabbitUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuweizhi
 * @since 2020/12/02 18:33
 */
@Slf4j
public class WorkQueue {

    public static void main(String[] args) throws InterruptedException, IOException {
        //获取TCP长连接
        DefaultMqQueue defaultMqQueue = new DefaultMqQueue(RabbitUtils.getRabbitMQConnection()) {
            @Override
            public void consumer() {
                for (int i = 0; i < 3; i++) {
                    new Thread(() -> {
                        try {
                            AtomicInteger atomicInteger = new AtomicInteger();
                            Channel consumer = connection.createChannel();
                            String name = Thread.currentThread().getName();
                            consumer.queueDeclare(RabbitConstant.QUEUE_SMS, false, false, false, null);
                            //如果不写basicQos（1），则自动MQ会将所有请求平均发送给所有消费者
                            //basicQos,MQ不再对消费者一次发送多个请求，而是消费者处理完一个消息后（确认后），在从队列中获取一个新的
                            consumer.basicQos(1);//处理完一个取一个
                            consumer.basicConsume(RabbitConstant.QUEUE_SMS, false, new DefaultConsumer(consumer) {
                                @Override
                                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                                    log.info("{} 消费 消息：{},消费标记为{},当前消费数量{}", name, new String(body),
                                            envelope.getDeliveryTag(), atomicInteger.getAndIncrement());
                                    if (name.endsWith("1")) {
                                        try {
                                            Thread.sleep(100);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    consumer.basicAck(envelope.getDeliveryTag(), false);
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }, "示例消费者" + i).start();
                }
            }

            @Override
            public void product() throws IOException, TimeoutException, InterruptedException {
                Channel channel = connection.createChannel();
                channel.queueDeclare(RabbitConstant.QUEUE_SMS, false, false, false, null);
                for (int i = 0; i < 100; i++) {
                    String message = UUID.randomUUID().toString();
                    channel.basicPublish("", RabbitConstant.QUEUE_SMS, null, message.getBytes());
                    log.info("生产者发送数量{}", i);
                }
                Thread.sleep(5000);
                channel.close();
            }
        };
        defaultMqQueue.start();
        defaultMqQueue.join();
        defaultMqQueue.close();
    }

}
