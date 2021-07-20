package com.summary.elasticsearch.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Logstash 日志收集
 *
 * @author xuweizhi
 * @since 2021/07/19 23:08
 */
@Slf4j
@RestController
@Api(tags = "logstash")
@RequestMapping("logstash")
public class LogstashController {

    /**
     * 测试不同格式的message过滤处理之后写入不同的elasticsearch-index
     *
     * @param type
     * @return
     */
    @GetMapping("test")
    @ApiOperation("test")
    public Boolean test(int type) {
        switch (type) {
            case 1:
                log.info("{} {} {} {}", "HTTP", "GET", "/api/test", "none");
                break;
            case 2:
                log.info("{} {} {} {}", "HTTP", "POST", "/api/test", "天王盖地虎");
                break;
            case 3:
                log.info("{} {} {}", "WS", "ChenFabao", "我已到达");
                break;
            default:
                log.info("{} {} {} {}", "HTTP", "GET", "/api/test", "other");
                break;
        }
        return true;
    }


}
