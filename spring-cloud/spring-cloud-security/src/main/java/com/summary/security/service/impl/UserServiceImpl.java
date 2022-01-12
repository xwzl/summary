package com.summary.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.summary.security.entity.User;
import com.summary.security.mapper.PermissionMapper;
import com.summary.security.mapper.UserMapper;
import com.summary.security.service.IUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 服务实现类
 *
 * @author xuweizhi
 * @since 2022-01-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public User getUserByName(String name) {
        return ChainWrappers.lambdaQueryChain(this.baseMapper).eq(User::getUsername, name).one();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByName(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户不存在或者密码错误");
        }
        List<GrantedAuthority> permissions = permissionMapper.selectByUserId(user.getId()).stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getEnglishName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), permissions);
    }
}
