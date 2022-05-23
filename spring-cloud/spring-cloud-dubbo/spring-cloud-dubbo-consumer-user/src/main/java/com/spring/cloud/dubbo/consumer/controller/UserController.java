package com.spring.cloud.dubbo.consumer.controller;

import com.alibaba.cloud.dubbo.annotation.DubboTransported;
import com.spring.cloud.dubbo.api.entity.User;
import com.spring.cloud.dubbo.api.service.UserService;
import com.spring.cloud.dubbo.consumer.feign.UserDubboFeignService;
import com.spring.cloud.dubbo.consumer.feign.UserFeignService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author xuweizhi
 * @since 2021/08/11 15:28
 */
@RestController
@SuppressWarnings("all")
@RequestMapping("user")
public class UserController {

    /*================================= dubbo ====================================*/
    @DubboReference
    private UserService userService;

    @RequestMapping("/info/{id}")
    public User info(@PathVariable("id") Integer id) {
        return userService.getById(id);
    }

    @GetMapping("/list")
    public List<User> list() {
        return userService.list();
    }

    /*================================= feign ====================================*/


    /**
     * 思考dubbo协议是否生效？  Spring  单例模式   不生效
     */
    @Autowired
    @DubboTransported(protocol = "dubbo")
    private UserFeignService userFeignService;

    @RequestMapping("/listFeign")
    public List<User> listFeign() {
        return userFeignService.list();
    }

    @Autowired
    private UserDubboFeignService userDubboFeignService;

    @RequestMapping("/list2")
    public List<User> list2() {
        return userDubboFeignService.list();
    }

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    @DubboTransported
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RequestMapping("/list3")
    public List<User> list3() {
        String url = "http://spring-cloud-dubbo-provider-user/user/list";
        return restTemplate.getForObject(url, List.class);
    }

}
