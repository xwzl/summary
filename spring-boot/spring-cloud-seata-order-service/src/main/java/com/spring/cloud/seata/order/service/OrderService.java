package com.spring.cloud.seata.order.service;

import com.spring.cloud.seata.datasource.entity.Order;
import com.spring.cloud.seata.order.vo.OrderVo;
import io.seata.core.exception.TransactionException;

public interface OrderService {

    /**
     * 保存订单
     */
    Order saveOrder(OrderVo orderVo) throws TransactionException;
}