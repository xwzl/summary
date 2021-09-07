package com.spring.cloud.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.cloud.commom.module.utils.PageVO;
import com.spring.cloud.user.entity.UserEntity;

import java.util.Map;


/**
 * @author xuweizhi
 */
@SuppressWarnings("all")
public interface UserService extends IService<UserEntity> {

    PageVO queryPage(Map<String, Object> params);

    UserEntity getUser(int id);
}

