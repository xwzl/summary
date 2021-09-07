package com.spring.cloud.user.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.cloud.commom.module.utils.PageVO;
import com.spring.cloud.commom.module.utils.Query;
import com.spring.cloud.user.dao.UserDao;
import com.spring.cloud.user.entity.UserEntity;
import com.spring.cloud.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * @author xuweizhi
 */
@Service("userService")
@SuppressWarnings("all")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Override
    public PageVO queryPage(Map<String, Object> params) {
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                new QueryWrapper<UserEntity>()
        );

        return new PageVO(page);
    }

    @Override
    @SentinelResource(value = "getUser")  //aop
    public UserEntity getUser(int id) {
        return baseMapper.selectById(id);
    }

    public UserEntity handleException(int id, BlockException ex) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("===被限流降级啦===");
        return userEntity;
    }


}
