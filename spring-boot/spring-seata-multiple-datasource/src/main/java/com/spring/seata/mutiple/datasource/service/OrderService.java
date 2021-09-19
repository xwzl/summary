package com.spring.seata.mutiple.datasource.service;


import com.spring.seata.mutiple.datasource.entity.Order;
import com.spring.seata.mutiple.datasource.vo.OrderVo;

public interface OrderService {

    /**
     * 保存订单
     */
    Order saveOrder(OrderVo orderVo);
}
