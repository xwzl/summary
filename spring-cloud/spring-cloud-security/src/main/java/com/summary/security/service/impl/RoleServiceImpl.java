package com.summary.security.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.summary.security.entity.Role;
import com.summary.security.mapper.RoleMapper;
import com.summary.security.service.IRoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
