package com.spring.cloud.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.cloud.commom.module.utils.PageVO;
import com.spring.cloud.commom.module.utils.Query;
import com.spring.cloud.user.dao.UserDao;
import com.spring.cloud.user.service.UserService;
import com.spring.cloud.user.entity.UserEntity;
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

}
