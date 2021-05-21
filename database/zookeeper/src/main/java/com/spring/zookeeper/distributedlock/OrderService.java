package com.spring.zookeeper.distributedlock;


import com.spring.zookeeper.distributedlock.entity.Order;
import com.spring.zookeeper.distributedlock.entity.Product;
import com.spring.zookeeper.distributedlock.mapper.OrderMapper;
import com.spring.zookeeper.distributedlock.mapper.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Service
public class OrderService {

    @Resource
    private ProductMapper productMapper;

    @Resource
    private OrderMapper orderMapper;


    @Transactional
    public void reduceStock(Integer id) {
        // 1.    获取库存
        Product product = productMapper.getProduct(id);
        // 模拟耗时业务处理
        sleep(500); // 其他业务处理

        if (product.getStock() <= 0) {
            throw new RuntimeException("out of stock");
        }
        // 2.    减库存
        int i = productMapper.deductStock(id);
        if (i == 1) {
            Order order = new Order();
            order.setUserId(UUID.randomUUID().toString());
            order.setPid(id);
            orderMapper.insert(order);
        } else {
            throw new RuntimeException("deduct stock fail, retry.");
        }

    }

    /**
     * 模拟耗时业务处理
     *
     * @param wait
     */
    public void sleep(long wait) {
        try {
            TimeUnit.MILLISECONDS.sleep(wait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
