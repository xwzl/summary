
package com.spring.cloud.order.service.impl;

import com.spring.cloud.commom.order.entity.HmilyOrder;
import com.spring.cloud.commom.order.enums.OrderStatusEnum;
import com.spring.cloud.order.mapper.HmilyOrderMapper;
import com.spring.cloud.order.service.HmilyOrderService;
import com.spring.cloud.order.service.PaymentService;
import org.dromara.hmily.common.utils.IdWorkerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;


/**
 * Hmily Order Service
 *
 * @author xuweizhi
 * @since 2022/03/22 16:30
 */
@Service
public class HmilyOrderServiceImpl implements HmilyOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HmilyOrderServiceImpl.class);

    @Resource
    private HmilyOrderMapper hmilyOrderMapper;

    @Resource
    private PaymentService paymentService;


    @Override
    public String orderPay(Integer count, BigDecimal amount) {
        HmilyOrder order = saveOrder(count, amount);
        long start = System.currentTimeMillis();
        paymentService.makePayment(order);
        System.out.println("hmily-cloud分布式事务耗时：" + (System.currentTimeMillis() - start));
        return "success";
    }

    @Override
    public String testOrderPay(Integer count, BigDecimal amount) {
        HmilyOrder order = saveOrder(count, amount);
        paymentService.testMakePayment(order);
        return "success";
    }

    @Override
    public String mockInventoryWithTryException(Integer count, BigDecimal amount) {
        HmilyOrder order = saveOrder(count, amount);
        return paymentService.mockPaymentInventoryWithTryException(order);
    }

    @Override
    public String mockAccountWithTryException(Integer count, BigDecimal amount) {
        HmilyOrder order = saveOrder(count, amount);
        return paymentService.mockPaymentAccountWithTryException(order);
    }

    /**
     * 模拟在订单支付操作中，库存在try阶段中的timeout
     *
     * @param count  购买数量
     * @param amount 支付金额
     * @return string
     */
    @Override
    public String mockInventoryWithTryTimeout(Integer count, BigDecimal amount) {
        HmilyOrder order = saveOrder(count, amount);
        return paymentService.mockPaymentInventoryWithTryTimeout(order);
    }

    @Override
    public String mockAccountWithTryTimeout(Integer count, BigDecimal amount) {
        HmilyOrder order = saveOrder(count, amount);
        return paymentService.mockPaymentAccountWithTryTimeout(order);
    }

    @Override
    public String orderPayWithNested(Integer count, BigDecimal amount) {
        HmilyOrder order = saveOrder(count, amount);
        return paymentService.makePaymentWithNested(order);
    }

    @Override
    public String orderPayWithNestedException(Integer count, BigDecimal amount) {
        HmilyOrder order = saveOrder(count, amount);
        return paymentService.makePaymentWithNestedException(order);
    }

    @Override
    public void updateOrderStatus(HmilyOrder order) {
        hmilyOrderMapper.update(order);
    }

    private HmilyOrder saveOrder(Integer count, BigDecimal amount) {
        final HmilyOrder order = buildOrder(count, amount);
        hmilyOrderMapper.save(order);
        return order;
    }

    private HmilyOrder buildOrder(Integer count, BigDecimal amount) {
        LOGGER.debug("构建订单对象");
        HmilyOrder order = new HmilyOrder();
        order.setCreateTime(new Date());
        order.setNumber(String.valueOf(IdWorkerUtils.getInstance().createUUID()));
        //demo中的表里只有商品id为 1的数据
        order.setProductId("1");
        order.setStatus(OrderStatusEnum.NOT_PAY.getCode());
        order.setTotalAmount(amount);
        order.setCount(count);
        //demo中 表里面存的用户id为10000
        order.setUserId("10000");
        return order;
    }
}
