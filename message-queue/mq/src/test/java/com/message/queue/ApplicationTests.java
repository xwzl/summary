package com.message.queue;

import com.message.queue.config.RabbitConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jakarta.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {QueueApplication.class})// 指定启动类
public class ApplicationTests {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 运行后才会创建
     */
    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            rabbitTemplate.convertAndSend(RabbitConfig.COMMODITY_EXCHANGE, "computer.msi", i);
        }

    }

}
