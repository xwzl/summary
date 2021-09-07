package com.spring.cloud.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.cloud.user.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

}
