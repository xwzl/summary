package com.spring.cloud.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.cloud.commom.utils.PageVO;
import com.spring.cloud.order.entity.OrderEntity;

import java.util.List;
import java.util.Map;


/**
 * @author xuweizhi
 */
@SuppressWarnings("all")
public interface OrderService extends IService<OrderEntity> {

    PageVO queryPage(Map<String, Object> params);

    List<OrderEntity> listByUserId(Integer userId);
}

