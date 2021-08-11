package com.spring.cloud.dubbo.api.service;


import com.spring.cloud.dubbo.api.entity.User;

import java.util.List;

/**
 * @author xuweizhi
 */
public interface UserService {

    /**
     * 获取 list
     *
     * @return 返回值
     */
    List<User> list();

    /**
     * 通过用户主键获取获取细腻
     *
     * @param id userId
     * @return 用户详情
     */
    User getById(Integer id);

}
