package com.spring.cloud.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.cloud.order.entity.OrderEntity;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author xuweizhi
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {

}
