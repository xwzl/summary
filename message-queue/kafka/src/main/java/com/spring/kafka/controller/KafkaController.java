package com.spring.kafka.controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xuweizhi
 * @since 2020/12/25 17:02
 */
@RestController
public class KafkaController {

    private final static String TOPIC_NAME = "my-replicated-topic";

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("/send")
    public void send() {
        kafkaTemplate.send(TOPIC_NAME, 0, "key", "this is a msg");
    }

}
