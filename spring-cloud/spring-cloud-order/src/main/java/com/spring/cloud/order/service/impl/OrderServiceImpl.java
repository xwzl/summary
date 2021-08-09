package com.spring.cloud.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.cloud.commom.module.utils.PageVO;
import com.spring.cloud.commom.module.utils.Query;
import com.spring.cloud.order.dao.OrderDao;
import com.spring.cloud.order.service.OrderService;
import com.spring.cloud.order.entity.OrderEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * @author xuweizhi
 */
@Service("orderService")
@SuppressWarnings("all")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {

    @Override
    public PageVO queryPage(Map<String, Object> params) {
        IPage<OrderEntity> page = this.page(
                new Query<OrderEntity>().getPage(params),
                new QueryWrapper<OrderEntity>()
        );
        return new PageVO(page);
    }

    @Override
    public List<OrderEntity> listByUserId(Integer userId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        return baseMapper.selectList(queryWrapper);
    }

}
