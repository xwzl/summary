package com.spring.cloud.dubbo.provider.service;

import com.spring.cloud.dubbo.api.entity.User;
import com.spring.cloud.dubbo.api.service.UserService;
import com.spring.cloud.dubbo.provider.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xuweizhi
 */
@Slf4j
@DubboService
@RestController
@RequestMapping("user")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    @RequestMapping("list")
    public List<User> list() {
        log.info("查询user列表");
        return userMapper.list();
    }


    @Override
    @RequestMapping("/getById/{id}")
    public User getById(Integer id) {
        return userMapper.getById(id);
    }
}
