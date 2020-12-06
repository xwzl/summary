package com.message.queue.example.routing;

import com.message.queue.constant.RabbitConstant;
import com.message.queue.util.RabbitUtils;
import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 百度订阅端
 *
 * @author xuweizhi
 * @since 2020/12/06 15:20
 */
@Slf4j
public class BaiduSub {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitUtils.getRabbitMQConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(RabbitConstant.QUEUE_BAIDU, false, false, false, null);
        // weather 交换机和 百度 队列绑定，不指定路由 key
        channel.queueBind(RabbitConstant.QUEUE_BAIDU, RabbitConstant.EXCHANGE_WEATHER_ROUTING, RoutingEnum.BAIDU_ONE.getRouting());
        channel.queueBind(RabbitConstant.QUEUE_BAIDU, RabbitConstant.EXCHANGE_WEATHER_ROUTING, RoutingEnum.BAIDU_TWO.getRouting());
        channel.basicConsume(RabbitConstant.QUEUE_BAIDU, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                log.info("百度接受消息：{}", new String(body));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
