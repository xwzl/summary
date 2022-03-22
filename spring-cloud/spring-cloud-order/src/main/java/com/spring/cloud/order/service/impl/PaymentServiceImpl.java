
package com.spring.cloud.order.service.impl;

import com.spring.cloud.commom.account.dto.AccountDTO;
import com.spring.cloud.commom.account.dto.AccountNestedDTO;
import com.spring.cloud.commom.inventory.dto.InventoryDTO;
import com.spring.cloud.commom.order.entity.Order;
import com.spring.cloud.commom.order.enums.OrderStatusEnum;
import com.spring.cloud.commom.order.mapper.OrderMapper;
import com.spring.cloud.order.feign.AccountClient;
import com.spring.cloud.order.feign.InventoryClient;
import com.spring.cloud.order.service.PaymentService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.dromara.hmily.common.exception.HmilyRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Hmily Order Service
 *
 * @author xuweizhi
 * @since 2022/03/22 16:30
 */
@Service
@SuppressWarnings("all")
public class PaymentServiceImpl implements PaymentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceImpl.class);

    private final OrderMapper orderMapper;

    private final AccountClient accountClient;

    private final InventoryClient inventoryClient;

    @Autowired(required = false)
    public PaymentServiceImpl(OrderMapper orderMapper,
                              AccountClient accountClient,
                              InventoryClient inventoryClient) {
        this.orderMapper = orderMapper;
        this.accountClient = accountClient;
        this.inventoryClient = inventoryClient;
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public void makePayment(Order order) {
        updateOrderStatus(order, OrderStatusEnum.PAYING);
//        //检查数据
//        final BigDecimal accountInfo = accountClient.findByUserId(order.getUserId());
//        final Integer inventoryInfo = inventoryClient.findByProductId(order.getProductId());
//        if (accountInfo.compareTo(order.getTotalAmount()) < 0) {
//            throw new HmilyRuntimeException("余额不足！");
//        }
//        if (inventoryInfo < order.getCount()) {
//            throw new HmilyRuntimeException("库存不足！");
//        }
        accountClient.payment(buildAccountDTO(order));
        inventoryClient.decrease(buildInventoryDTO(order));
    }

    @Override
    public void testMakePayment(Order order) {
        updateOrderStatus(order, OrderStatusEnum.PAYING);
        //扣除用户余额
        accountClient.testPayment(buildAccountDTO(order));
        //进入扣减库存操作
        inventoryClient.testDecrease(buildInventoryDTO(order));
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public String mockPaymentInventoryWithTryException(Order order) {
        LOGGER.debug("===========执行springcloud  mockPaymentInventoryWithTryException 扣减资金接口==========");
        updateOrderStatus(order, OrderStatusEnum.PAYING);
        //扣除用户余额
        accountClient.payment(buildAccountDTO(order));
        inventoryClient.mockWithTryException(buildInventoryDTO(order));
        return "success";
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public String mockPaymentAccountWithTryException(Order order) {
        updateOrderStatus(order, OrderStatusEnum.PAYING);
        accountClient.mockWithTryException(buildAccountDTO(order));
        return "success";
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public String mockPaymentInventoryWithTryTimeout(Order order) {
        LOGGER.debug("===========执行springcloud  mockPaymentInventoryWithTryTimeout 扣减资金接口==========");
        updateOrderStatus(order, OrderStatusEnum.PAYING);
        accountClient.payment(buildAccountDTO(order));
        inventoryClient.mockWithTryTimeout(buildInventoryDTO(order));
        return "success";
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public String mockPaymentAccountWithTryTimeout(Order order) {
        updateOrderStatus(order, OrderStatusEnum.PAYING);
        accountClient.mockWithTryTimeout(buildAccountDTO(order));
        return "success";
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public String makePaymentWithNested(Order order) {
        updateOrderStatus(order, OrderStatusEnum.PAYING);
        final BigDecimal balance = accountClient.findByUserId(order.getUserId());
        if (balance.compareTo(order.getTotalAmount()) <= 0) {
            throw new HmilyRuntimeException("余额不足！");
        }
        accountClient.paymentWithNested(buildAccountNestedDTO(order));
        return "success";
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public String makePaymentWithNestedException(Order order) {
        updateOrderStatus(order, OrderStatusEnum.PAYING);
        final BigDecimal balance = accountClient.findByUserId(order.getUserId());
        if (balance.compareTo(order.getTotalAmount()) <= 0) {
            throw new HmilyRuntimeException("余额不足！");
        }
        accountClient.paymentWithNestedException(buildAccountNestedDTO(order));
        return "success";
    }

    public void confirmOrderStatus(Order order) {
        updateOrderStatus(order, OrderStatusEnum.PAY_SUCCESS);
        LOGGER.info("=========进行订单confirm操作完成================");
    }

    public void cancelOrderStatus(Order order) {
        updateOrderStatus(order, OrderStatusEnum.PAY_FAIL);
        LOGGER.info("=========进行订单cancel操作完成================");
    }

    private void updateOrderStatus(Order order, OrderStatusEnum orderStatus) {
        order.setStatus(orderStatus.getCode());
        orderMapper.update(order);
    }

    private AccountDTO buildAccountDTO(Order order) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAmount(order.getTotalAmount());
        accountDTO.setUserId(order.getUserId());
        return accountDTO;
    }

    private InventoryDTO buildInventoryDTO(Order order) {
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setCount(order.getCount());
        inventoryDTO.setProductId(order.getProductId());
        return inventoryDTO;
    }

    private AccountNestedDTO buildAccountNestedDTO(Order order) {
        AccountNestedDTO nestedDTO = new AccountNestedDTO();
        nestedDTO.setAmount(order.getTotalAmount());
        nestedDTO.setUserId(order.getUserId());
        nestedDTO.setProductId(order.getProductId());
        nestedDTO.setCount(order.getCount());
        return nestedDTO;
    }
}
