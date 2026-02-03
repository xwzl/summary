package com.message.queue;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProducerTest {

    //1.注入 RabbitTemplate
    private RabbitTemplate rabbitTemplate;

    @Before
    public void before(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/spring-product/spring-rabbitmq-producer.xml");
        rabbitTemplate = context.getBean(RabbitTemplate.class);
    }

    @Test
    public void testHelloWorld() {
        //2.发送消息
        rabbitTemplate.convertAndSend("spring_queue", "hello world spring....");
    }


    /**
     * 发送fanout消息
     */
    @Test
    public void testFanout() {
        //2.发送消息
        rabbitTemplate.convertAndSend("spring_fanout_exchange", "", "spring fanout....");
    }


    @Test
    public void testDirect() {
        //2.发送消息
        rabbitTemplate.convertAndSend("spring_direct_exchange", "info", "spring Direct....");
    }

    /**
     * 发送topic消息
     */
    @Test
    public void testTopics() {
        //2.发送消息
        rabbitTemplate.convertAndSend("spring_topic_exchange", "baiqi.hehe.haha", "spring topic....");
    }
}
