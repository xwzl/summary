package com.message.queue.example.routing;

import com.message.queue.constant.RabbitConstant;
import com.message.queue.util.RabbitUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import static com.message.queue.example.routing.RoutingEnum.getRoutingEnumByCode;

/**
 * 消息发布者,向订阅消息的（绑定）队列发送信息，默认 光膜
 *
 * @author xuweizhi
 * @since 2020/12/06 15:14
 */
@Slf4j
public class WeatherRoutingProduct {


    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitUtils.getRabbitMQConnection();
        Channel channel = connection.createChannel();
        // 声明广播消息交换机
        channel.exchangeDeclare(RabbitConstant.EXCHANGE_WEATHER_ROUTING, BuiltinExchangeType.DIRECT.getType());
        Scanner scanner = new Scanner(System.in);
        int index = 0;
        String routingKey;
        int length = RoutingEnum.values().length;
        while (true) {
            log.info("请输入发送的内容");
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            }
            // 向交换机发送消息，队列中消息的产生有由交换机进行处理
            channel.basicPublish(RabbitConstant.EXCHANGE_WEATHER_ROUTING, getRoutingEnumByCode(index % length).getRouting(), null, input.getBytes());
            index++;
        }
        scanner.close();
        channel.close();
        connection.close();
    }
}
