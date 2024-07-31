package com.spring.cloud.order.controller;

import com.spring.cloud.order.service.HmilyOrderService;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.math.BigDecimal;

/**
 * TCC 事务
 *
 * @author xuweizhi
 * @since 2022/03/22 16:27
 */
@Data
@RestController
@RequestMapping("order")
public class HmilyOrderController {

    @Resource
    private HmilyOrderService hmilyOrderService;


    @PostMapping(value = "/orderPay")
//    @ApiOperation(value = "订单支付接口（注意这里模拟的是创建订单并进行支付扣减库存等操作）")
    public String orderPay(@RequestParam(value = "count") Integer count,
                           @RequestParam(value = "amount") BigDecimal amount) {
        return hmilyOrderService.orderPay(count, amount);
    }

    @PostMapping(value = "/testOrderPay")
//    @ApiOperation(value = "测试订单支付接口(这里是压测接口不添加分布式事务)")
    public String testOrderPay(@RequestParam(value = "count") Integer count,
                               @RequestParam(value = "amount") BigDecimal amount) {
        final long start = System.currentTimeMillis();
        String result = hmilyOrderService.testOrderPay(count, amount);
        System.out.println("消耗时间为:" + (System.currentTimeMillis() - start));
        return result;
    }

    @PostMapping(value = "/mockInventoryWithTryException")
//    @ApiOperation(value = "模拟下单付款操作在try阶段时候，库存异常，此时账户系统和订单状态会回滚，达到数据的一致性（注意:这里模拟的是系统异常，或者rpc异常）")
    public String mockInventoryWithTryException(@RequestParam(value = "count") Integer count,
                                                @RequestParam(value = "amount") BigDecimal amount) {
        return hmilyOrderService.mockInventoryWithTryException(count, amount);
    }

    @PostMapping(value = "/mockInventoryWithTryTimeout")
//    @ApiOperation(value = "模拟下单付款操作在try阶段时候，库存超时异常（但是自身最后又成功了），此时账户系统和订单状态会回滚，（库存依赖事务日志进行恢复），达到数据的一致性（异常指的是超时异常）")
    public String mockInventoryWithTryTimeout(@RequestParam(value = "count") Integer count,
                                              @RequestParam(value = "amount") BigDecimal amount) {
        return hmilyOrderService.mockInventoryWithTryTimeout(count, amount);
    }

    @PostMapping(value = "/mockAccountWithTryException")
//    @ApiOperation(value = "模拟下单付款操作在try阶段时候，账户rpc异常，此时订单状态会回滚，达到数据的一致性（注意:这里模拟的是系统异常，或者rpc异常）")
    public String mockAccountWithTryException(@RequestParam(value = "count") Integer count,
                                              @RequestParam(value = "amount") BigDecimal amount) {
        return hmilyOrderService.mockAccountWithTryException(count, amount);
    }

    @PostMapping(value = "/mockAccountWithTryTimeout")
//    @ApiOperation(value = "模拟下单付款操作在try阶段时候，账户rpc超时异常（但是最后自身又成功了），此时订单状态会回滚，账户系统依赖自身的事务日志进行调度恢复，达到数据的一致性（异常指的是超时异常）")
    public String mockAccountWithTryTimeout(@RequestParam(value = "count") Integer count,
                                            @RequestParam(value = "amount") BigDecimal amount) {
        return hmilyOrderService.mockAccountWithTryTimeout(count, amount);
    }

    @PostMapping(value = "/orderPayWithNested")
//    @ApiOperation(value = "订单支付接口（这里模拟的是rpc的嵌套调用 order--> account--> inventory）")
    public String orderPayWithNested(@RequestParam(value = "count") Integer count,
                                     @RequestParam(value = "amount") BigDecimal amount) {
        return hmilyOrderService.orderPayWithNested(count, amount);
    }

    @PostMapping(value = "/orderPayWithNestedException")
//    @ApiOperation(value = "订单支付接口（里模拟的是rpc的嵌套调用 order--> account--> inventory, inventory异常情况")
    public String orderPayWithNestedException(@RequestParam(value = "count") Integer count,
                                              @RequestParam(value = "amount") BigDecimal amount) {
        return hmilyOrderService.orderPayWithNestedException(count, amount);
    }
}
