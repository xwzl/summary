package com.summary.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.summary.security.entity.UserRole;
import com.summary.security.mapper.UserRoleMapper;
import com.summary.security.service.IUserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xuweizhi
 * @since 2022-01-11
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
