package com.summary.security.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.summary.security.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 服务类
 *
 * @author xuweizhi
 * @since 2022-01-11
 */
public interface IUserService extends IService<User>, UserDetailsService {


    /**
     * 通过名称获取 User
     *
     * @param name //
     * @return //
     */
    User getUserByName(String name);

}
