package com.roy.sharding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.roy.sharding.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface UserMapper extends BaseMapper<User> {

    @Select("select u.id,u.username,d.value status from user u left join dict d on u.status = d.status")
    public List<User> queryUserStatus();
}
