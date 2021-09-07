package com.spring.cloud.user.feign;

import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.spring.cloud.commom.module.utils.ResultVO;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author xuweizhi
 */
@Component
public class FallbackOrderFeignServiceFactory implements FallbackFactory<OrderFeignService> {
    @Override
    public OrderFeignService create(Throwable throwable) {
        return userId -> {
            if (throwable instanceof FlowException) {
                return ResultVO.error(100,"接口限流了");
            }
            return ResultVO.error(-1,"=======服务降级了========");
        };
    }
}
