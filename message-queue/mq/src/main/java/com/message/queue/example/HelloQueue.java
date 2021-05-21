package com.message.queue.example;


import com.message.queue.constant.RabbitConstant;
import com.message.queue.util.RabbitUtils;
import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 对应最简单的 queue 模型
 * <p>
 * p -> queue -> c
 *
 * @author xuweizhi
 * @since 2020/12/02 17:38
 */
@Slf4j
public class HelloQueue {

    public static void main(String[] args) throws IOException, TimeoutException {

        //获取TCP长连接
        Connection connection = RabbitUtils.getRabbitMQConnection();

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    AtomicInteger atomicInteger = new AtomicInteger();
                    Channel consumer = connection.createChannel();
                    consumer.queueDeclare(RabbitConstant.QUEUE_HELLOWORLD, false, false, false, null);
                    String name = Thread.currentThread().getName();
                    //从MQ服务器中获取数据
                    //创建一个消息消费者
                    //第一个参数：队列名
                    //第二个参数代表是否自动确认收到消息，false代表手动编程来确认消息，这是MQ的推荐做法
                    //第三个参数要传入DefaultConsumer的实现类
                    consumer.basicConsume(RabbitConstant.QUEUE_HELLOWORLD, false, new DefaultConsumer(consumer) {
                        @Override
                        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                            //super.handleDelivery(consumerTag, envelope, properties, body);
                            String message = new String(body);
                            log.info("{} 消费消息：{},消费标记为{},当前消费数量{}", name, message,
                                    envelope.getDeliveryTag(), atomicInteger.getAndIncrement());
                            //false只确认签收当前的消息，设置为true的时候则代表签收该消费者所有未签收的消息
                            consumer.basicAck(envelope.getDeliveryTag(), false);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }, "示例消费者" + i).start();
        }

        //创建通信“通道”，相当于TCP中的虚拟连接
        Channel channel = connection.createChannel();

        //创建队列,声明并创建一个队列，如果队列已存在，则使用这个队列
        //第一个参数：队列名称ID
        //第二个参数：是否持久化，false对应不持久化数据，MQ停掉数据就会丢失
        //第三个参数：是否队列私有化，false则代表所有消费者都可以访问，true代表只有第一次拥有它的消费者才能一直使用，其他消费者不让访问
        //第四个：是否自动删除,false代表连接停掉后不自动删除掉这个队列
        //其他额外的参数, null
        channel.queueDeclare(RabbitConstant.QUEUE_HELLOWORLD, false, false, false, null);
        for (int i = 0; i < 1000; i++) {
            String message = UUID.randomUUID().toString();
            //四个参数
            //exchange 交换机，暂时用不到，在后面进行发布订阅时才会用到
            //队列名称;额外的设置属性;最后一个参数是要传递的消息字节数组
            channel.basicPublish("", RabbitConstant.QUEUE_HELLOWORLD, null, message.getBytes());
            log.info("生产者发送数量{}", i);
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        channel.close();
        connection.close();
    }
}
