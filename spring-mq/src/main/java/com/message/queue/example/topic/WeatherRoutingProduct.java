package com.message.queue.example.topic;

import com.message.queue.constant.RabbitConstant;
import com.message.queue.example.routing.RoutingEnum;
import com.message.queue.util.RabbitUtils;
import com.rabbitmq.client.*;
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
        channel.exchangeDeclare(RabbitConstant.EXCHANGE_WEATHER_TOPIC, BuiltinExchangeType.TOPIC.getType());

        //开启confirm监听模式
        channel.confirmSelect();
        channel.addConfirmListener(new ConfirmListener() {
            // 表示 Rabbit MQ 正常接收下消息
            public void handleAck(long l, boolean b) throws IOException {
                //第二个参数代表接收的数据是否为批量接收，一般我们用不到。
                System.out.println("消息已被Broker接收,Tag:" + l);
            }

            // 表示 Rabbit MQ 拒绝接收消息
            public void handleNack(long l, boolean b) throws IOException {
                System.out.println("消息已被Broker拒收,Tag:" + l);
            }
        });
        channel.addReturnListener(new ReturnCallback() {
            // 表示 Rabbit MQ 正常接收消息，但是没有对应的 Queue 来存放消息
            public void handle(Return r) {
                System.err.println("===========================");
                System.err.println("Return编码：" + r.getReplyCode() + "-Return描述:" + r.getReplyText());
                System.err.println("交换机:" + r.getExchange() + "-路由key:" + r.getRoutingKey());
                System.err.println("Return主题：" + new String(r.getBody()));
                System.err.println("===========================");
            }
        });

        Scanner scanner = new Scanner(System.in);
        int length = RoutingEnum.values().length;
        int index = 0;
        while (true) {
            log.info("请输入发送的内容");
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            }
            // 向交换机发送消息，队列中消息的产生有由交换机进行处理
            //Routing key 第二个参数相当于数据筛选的条件
            //第三个参数为：mandatory true代表如果消息无法正常投递则return回生产者，如果false，则直接将消息放弃。
            channel.basicPublish(RabbitConstant.EXCHANGE_WEATHER_TOPIC, getRoutingEnumByCode(index % length).getRouting(), true, null, input.getBytes());
            index++;
        }
        scanner.close();
        channel.close();
        connection.close();
    }
}
