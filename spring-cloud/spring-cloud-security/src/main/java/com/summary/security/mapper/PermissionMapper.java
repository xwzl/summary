package com.summary.security.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.summary.security.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author xuweizhi
 * @since 2022-01-11
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 查询菜单集
     *
     * @param userId //
     * @return //
     */
    List<Permission> selectByUserId(@Param("userId") Long userId);
}
