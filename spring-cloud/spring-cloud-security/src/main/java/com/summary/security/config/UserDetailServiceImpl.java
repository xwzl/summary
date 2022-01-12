package com.summary.security.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Security 获取密码
 *
 * @author xuweizhi
 * @since 2022/01/11 19:09
 */
@Service
@Deprecated
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Spring Security 5 以后用 noop 方式加密，如果密码不指定{id}会抛异常
        return User.withUsername("username").password("{MD5}123456").authorities("admin").build();
    }

}
