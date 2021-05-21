package com.message.queue.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * RabbitMQ 客户端连接工具
 *
 * @author xuweizhi
 * @since 2020/12/02 17:48
 */
public class RabbitUtils {

    private static Connection connection;

    static {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(ContextUtil.ip);
        connectionFactory.setPort(ContextUtil.port);
        // 生产和消费者必须是同一个用户，否则会出现接收不到消息的情况
        connectionFactory.setPassword(ContextUtil.password);
        connectionFactory.setUsername(ContextUtil.username);
        // 类似 mysql database concept,需要在控制台预先创建
        connectionFactory.setVirtualHost("/test");
        try {
            connection = connectionFactory.newConnection();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    public static Connection getRabbitMQConnection() {
        if (connection == null) {
            throw new RuntimeException("rabbit mq client init failure");
        }
        return connection;
    }
}
