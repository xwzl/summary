package com.summary.security.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.summary.security.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xuweizhi
 * @since 2022-01-11
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
