package com.message.queue.spring.listener;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xuweizhi
 * @since 2020/12/07 19:34
 */
public class SpringContextApplication {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/spring-advance/spring-rabbitmq-consumer.xml");
    }
}
