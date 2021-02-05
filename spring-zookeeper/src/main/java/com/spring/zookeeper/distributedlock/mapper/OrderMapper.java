package com.spring.zookeeper.distributedlock.mapper;

import com.spring.zookeeper.distributedlock.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

public interface OrderMapper {

    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    @Insert(" insert into `order`(user_id,pid) values(#{id},#{pid}) ")
    int insert(Order order);
}
