package com.message.queue;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProducerAdvanceTest {

    //1.注入 RabbitTemplate
    private RabbitTemplate rabbitTemplate;

    @Before
    public void before() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/spring-advance/spring-rabbitmq-producer.xml");
        rabbitTemplate = context.getBean(RabbitTemplate.class);
    }

    @Test
    public void testConfirm() {
        //定义回调
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            System.out.println("confirm方法被执行了....");
            //ack 为  true表示 消息已经到达交换机
            if (ack) {
                //接收成功
                System.out.println("接收成功消息" + cause);
            } else {
                //接收失败
                System.out.println("接收失败消息" + cause);
                //做一些处理，让消息再次发送。
            }
        });
        //进行消息发送
        rabbitTemplate.convertAndSend("test_exchange_confirm", "confirm", "message Confirm...");

        //进行睡眠操作
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 定义回调,消息到达交换机但是没有发送到指定队列
     */
    @Test
    public void testReturn() {
        //设置交换机处理失败消息的模式   为true的时候，消息达到不了 队列时，会将消息重新返回给生产者
        rabbitTemplate.setMandatory(true);

        //定义回调,消息到达交换机但是没有发送到指定队列
        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returned) {
                System.out.println("return 执行了....");
                System.out.println(returned.toString());
                //处理
            }
        });
        //进行消息发送
        rabbitTemplate.convertAndSend("test_exchange_confirm", "confirm", "message return...");
        //rabbitTemplate.convertAndSend("test_exchange_confirm", "confirm1", "message return...");

        //进行睡眠操作
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //批量发送消息，让消费者每次拉去指定的数量
    @Test
    public void testQos() {
        for (int i = 0; i < 10; i++) {
            // 发送消息
            rabbitTemplate.convertAndSend("test_exchange_confirm", "confirm", "message confirm....");
        }
    }

    /**
     * 测试 ttl 队列
     */
    @Test
    public void testTtl(){
        for (int i = 0; i < 10; i++) {
            // 发送消息
            rabbitTemplate.convertAndSend("test_exchange_ttl", "ttl.test", "message confirm....");
        }
    }

    /**
     * 测试 ttl 队列
     */
    @Test
    public void testDeathLetterQueue(){
        for (int i = 0; i < 10; i++) {
            // 发送消息
            rabbitTemplate.convertAndSend("test_exchange_dlx", "test.dlx..test", "message confirm....");
        }
    }
}
