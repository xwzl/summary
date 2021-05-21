package com.message.queue.listen;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.message.queue.config.RabbitConfig.COMPUTER_QUEUE;

/**
 * Rabbit MQ listener
 *
 * @author xuweizhi
 * @since 2020/12/07 16:01
 */
@Slf4j
@Component
public class RabbitMqListener {

    @RabbitListener(queues = COMPUTER_QUEUE)
    public void onMessage(Message message) {
        log.info(message.toString());
    }
}
