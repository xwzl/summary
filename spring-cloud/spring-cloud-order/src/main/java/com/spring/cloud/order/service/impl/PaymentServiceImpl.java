
package com.spring.cloud.order.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.spring.cloud.commom.account.dto.AccountNestedDTO;
import com.spring.cloud.commom.account.dto.HmilyAccountDTO;
import com.spring.cloud.commom.inventory.dto.InventoryDTO;
import com.spring.cloud.commom.order.entity.HmilyOrder;
import com.spring.cloud.commom.order.enums.OrderStatusEnum;
import com.spring.cloud.order.feign.AccountClient;
import com.spring.cloud.order.feign.InventoryClient;
import com.spring.cloud.order.mapper.HmilyOrderMapper;
import com.spring.cloud.order.service.PaymentService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.dromara.hmily.common.exception.HmilyRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
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

    @Resource
    private HmilyOrderMapper hmilyOrderMapper;

    @Resource
    private AccountClient accountClient;

    @Resource
    private InventoryClient inventoryClient;


    @Override
    @HmilyTCC(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public void makePayment(HmilyOrder order) {
        updateOrderStatus(order, OrderStatusEnum.PAYING);
//        //检查数据
        final BigDecimal accountInfo = accountClient.findByUserId(order.getUserId());
        final Integer inventoryInfo = inventoryClient.findByProductId(order.getProductId());
        if (accountInfo.compareTo(order.getTotalAmount()) < 0) {
            throw new HmilyRuntimeException("余额不足！");
        }
        if (inventoryInfo < order.getCount()) {
            throw new HmilyRuntimeException("库存不足！");
        }
        accountClient.payment(buildAccountDTO(order));
        inventoryClient.decrease(buildInventoryDTO(order));
    }

    @Override
    public void testMakePayment(HmilyOrder order) {
        updateOrderStatus(order, OrderStatusEnum.PAYING);
        //扣除用户余额
        accountClient.testPayment(buildAccountDTO(order));
        //进入扣减库存操作
        inventoryClient.testDecrease(buildInventoryDTO(order));
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public String mockPaymentInventoryWithTryException(HmilyOrder order) {
        LOGGER.debug("===========执行springcloud  mockPaymentInventoryWithTryException 扣减资金接口==========");
        updateOrderStatus(order, OrderStatusEnum.PAYING);
        //扣除用户余额
        accountClient.payment(buildAccountDTO(order));
        Boolean aBoolean = inventoryClient.mockWithTryException(buildInventoryDTO(order));
        return "success";
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public String mockPaymentAccountWithTryException(HmilyOrder order) {
        updateOrderStatus(order, OrderStatusEnum.PAYING);
        accountClient.mockWithTryException(buildAccountDTO(order));
        return "success";
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public String mockPaymentInventoryWithTryTimeout(HmilyOrder order) {
        LOGGER.debug("===========执行springcloud  mockPaymentInventoryWithTryTimeout 扣减资金接口==========");
        updateOrderStatus(order, OrderStatusEnum.PAYING);
        accountClient.payment(buildAccountDTO(order));
        inventoryClient.mockWithTryTimeout(buildInventoryDTO(order));
        return "success";
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public String mockPaymentAccountWithTryTimeout(HmilyOrder order) {
        updateOrderStatus(order, OrderStatusEnum.PAYING);
        accountClient.mockWithTryTimeout(buildAccountDTO(order));
        return "success";
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public String makePaymentWithNested(HmilyOrder order) {
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
    public String makePaymentWithNestedException(HmilyOrder order) {
        updateOrderStatus(order, OrderStatusEnum.PAYING);
        final BigDecimal balance = accountClient.findByUserId(order.getUserId());
        if (balance.compareTo(order.getTotalAmount()) <= 0) {
            throw new HmilyRuntimeException("余额不足！");
        }
        accountClient.paymentWithNestedException(buildAccountNestedDTO(order));
        return "success";
    }

    public void confirmOrderStatus(HmilyOrder order) {
        updateOrderStatus(order, OrderStatusEnum.PAY_SUCCESS);
        LOGGER.info("=========进行订单confirm操作完成================");
    }

    public void cancelOrderStatus(HmilyOrder order) {
        updateOrderStatus(order, OrderStatusEnum.PAY_FAIL);
        LOGGER.info("=========进行订单cancel操作完成================");
    }

    private void updateOrderStatus(HmilyOrder order, OrderStatusEnum orderStatus) {
        order.setStatus(orderStatus.getCode());
        hmilyOrderMapper.update(order);
    }

    private HmilyAccountDTO buildAccountDTO(HmilyOrder order) {
        HmilyAccountDTO accountDTO = new HmilyAccountDTO();
        accountDTO.setAmount(order.getTotalAmount());
        accountDTO.setUserId(order.getUserId());
        return accountDTO;
    }

    private InventoryDTO buildInventoryDTO(HmilyOrder order) {
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setCount(order.getCount());
        inventoryDTO.setProductId(order.getProductId());
        return inventoryDTO;
    }

    private AccountNestedDTO buildAccountNestedDTO(HmilyOrder order) {
        AccountNestedDTO nestedDTO = new AccountNestedDTO();
        nestedDTO.setAmount(order.getTotalAmount());
        nestedDTO.setUserId(order.getUserId());
        nestedDTO.setProductId(order.getProductId());
        nestedDTO.setCount(order.getCount());
        return nestedDTO;
    }
}
