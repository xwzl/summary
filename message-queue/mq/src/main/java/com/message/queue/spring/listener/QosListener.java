package com.message.queue.spring.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * 限流逻辑
 */
@Component
public class QosListener implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        //获取到的消息
        System.out.println(new String(message.getBody()));
        Thread.sleep(1000);
        //处理业务逻辑
        //进行消息的签收,签收才进行下一条消息的处理
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }
}
