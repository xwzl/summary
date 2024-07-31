package com.spring.cloud.user.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.spring.cloud.user.entity.UserEntity;
import com.spring.cloud.user.sentinel.handler.CommonBlockHandler;
import com.spring.cloud.user.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * LimitFlowController Controller
 *
 * @author xuweizhi
 * @since 2021/09/06 16:02
 */
@RestController
public class LimitFlowController {

    @RequestMapping("/test")
    // @SentinelResource(value = "test", blockHandlerClass = CommonBlockHandler.class, blockHandler = "handleException3")
    public String test() {

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "========test()========";
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    @RequestMapping("/test2")
    public String test2() {
        atomicInteger.getAndIncrement();
        if (atomicInteger.get() % 2 == 0) {
            //模拟异常和异常比率
            int i = 1 / 0;
        }

        return "========test2()========";
    }

    @Resource
    private UserService userService;

    @RequestMapping(value = "/test3")  //CommonFilter
    public UserEntity test3() {
        return userService.getUser(1);
    }

    @RequestMapping(value = "/test4")
    public UserEntity test4() {
        return userService.getUser(1);
    }


    public String handleException(BlockException exception) {
        return "===被限流降级啦===";
    }
}
