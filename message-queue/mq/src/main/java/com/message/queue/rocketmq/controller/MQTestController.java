package com.message.queue.rocketmq.controller;

import com.message.queue.rocketmq.basic.SpringProducer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = {"rocket MQ"})
@RestController
@RequestMapping("/MQTest")
public class MQTestController {

    private final String topic = "TestTopic";

    @Resource
    private SpringProducer producer;

    @ApiOperation("sendMessage")
    @GetMapping("/sendMessage")
    public String sendMessage(String message) {
        // :tagTest 声明 tag,类似 rabbitMq exchange 概念
        // Spring Topic(Topic:Tag) = Rocket Topic + Tag
        producer.sendMessage(topic + ":testTag", message);
        return "消息发送完成";
    }

    //这个发送事务消息的例子中有很多问题，需要注意下。
    @ApiOperation("sendTransactionMessage")
    @GetMapping("/sendTransactionMessage")
    public String sendTransactionMessage(String message) throws InterruptedException {
        producer.sendMessageInTransaction(topic, message);
        return "消息发送完成";
    }
}
