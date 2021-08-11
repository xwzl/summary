package com.spring.cloud.dubbo.provider.mapper;


import com.spring.cloud.dubbo.api.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
@SuppressWarnings("all")
public interface UserMapper {
    @Select("select * from t_user")
    List<User> list();

    @Select("select * from t_user where id=#{id}")
    User getById(Integer id);
}
