package com.message.queue.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Rabbit MQ 队列 交换机 和 绑定关系声明
 *
 * @author xuweizhi
 * @since 2020/12/07 15:43
 */
@Configuration
public class RabbitConfig {

    /**
     * 声明交换机
     */
    public static final String COMMODITY_EXCHANGE = "commodity-exchange";

    /**
     * 声明队列
     */
    public static final String COMPUTER_QUEUE = "computer.msi";

    /**
     * 创建交换机
     */
    @Bean("commodityExchange")
    public Exchange commodityExchange() {
        return ExchangeBuilder.topicExchange(COMMODITY_EXCHANGE).durable(true).build();
    }

    /**
     * 创建队列
     */
    @Bean("computerMsi")
    public Queue computerMsi() {
        return QueueBuilder.durable(COMPUTER_QUEUE).build();
    }

    /**
     * 绑定交换机
     */
    @Bean
    public Binding bindingQueueExchange(Queue computerMsi, Exchange commodityExchange) {
        return BindingBuilder.bind(computerMsi).to(commodityExchange).with("computer.#").noargs();
    }
}
