package com.summary.security.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.summary.security.entity.Permission;
import com.summary.security.mapper.PermissionMapper;
import com.summary.security.service.IPermissionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author xuweizhi
 * @since 2022-01-11
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
