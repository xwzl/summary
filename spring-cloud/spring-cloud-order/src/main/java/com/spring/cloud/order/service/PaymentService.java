
package com.spring.cloud.order.service;


import com.spring.cloud.commom.order.entity.HmilyOrder;

/**
 * PaymentService.
 *
 * @author xuweizhi
 */
public interface PaymentService {

    /**
     * 订单支付.
     *
     * @param order 订单实体
     */
    void makePayment(HmilyOrder order);

    /**
     * Test make payment.
     *
     * @param order the order
     */
    void testMakePayment(HmilyOrder order);

    /**
     * mock订单支付的时候库存异常.
     *
     * @param order 订单实体
     * @return String string
     */
    String mockPaymentInventoryWithTryException(HmilyOrder order);

    /**
     * Mock payment account with try exception string.
     *
     * @param order the order
     * @return the string
     */
    String mockPaymentAccountWithTryException(HmilyOrder order);

    /**
     * mock订单支付的时候库存超时.
     *
     * @param order 订单实体
     * @return String string
     */
    String mockPaymentInventoryWithTryTimeout(HmilyOrder order);

    /**
     * Mock payment account with try timeout string.
     *
     * @param order the order
     * @return the string
     */
    String mockPaymentAccountWithTryTimeout(HmilyOrder order);

    /**
     * Make payment with nested.
     *
     * @param order the order
     * @return the string
     */
    String makePaymentWithNested(HmilyOrder order);

    /**
     * Make payment with nested exception.
     *
     * @param order the order
     * @return the string
     */
    String makePaymentWithNestedException(HmilyOrder order);
}
