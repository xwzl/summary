package com.spring.cloud.dubbo.controller;

import com.spring.cloud.dubbo.api.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xuweizhi
 * @since 2021/08/11 15:28
 */
@RestController
@SuppressWarnings("all")
@RequestMapping("user")
public class UserController {

    @DubboReference
    private UserService userService;

    @RequestMapping("/info/{id}")
    public User info(@PathVariable("id") Integer id){
        return userService.getById(id);
    }

    @RequestMapping("/list")
    public List<User> list(){

        return userService.list();
    }
}
