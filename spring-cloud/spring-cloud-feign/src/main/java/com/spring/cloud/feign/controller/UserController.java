package com.spring.cloud.feign.controller;


import com.spring.cloud.commom.utils.ResultVO;
import com.spring.cloud.feign.feign.OrderFeignService;

import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

@RestController
@SuppressWarnings("all")
@RequestMapping("/user")
public class UserController {

    @Resource
    private OrderFeignService orderFeignService;

    @RequestMapping(value = "/findOrderByUserId/{id}")
    public ResultVO findOrderByUserId(@PathVariable("id") Integer id) {
        //feign调用
        ResultVO result = orderFeignService.findOrderByUserId(id);
        return result;
    }


//    @RequestMapping(value = "/save")
//    public R save(@RequestBody OrderVo order){
//        return orderFeignService.save(order);
//    }


}
