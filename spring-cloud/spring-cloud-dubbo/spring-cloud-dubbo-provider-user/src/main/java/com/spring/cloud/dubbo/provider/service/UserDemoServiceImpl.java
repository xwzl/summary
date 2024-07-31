package com.spring.cloud.dubbo.provider.service;

import com.spring.cloud.dubbo.api.entity.User;
import com.spring.cloud.dubbo.api.service.UserDemoService;
import com.spring.cloud.dubbo.provider.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * @author xuweizhi
 */
@Slf4j
@DubboService
public class UserDemoServiceImpl implements UserDemoService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> list() {
        log.info("查询user列表");
        return userMapper.list();
    }


    @Override
    public User getById(Integer id) {
        return userMapper.getById(id);
    }
}
