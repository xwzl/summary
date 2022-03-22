package com.spring.cloud.user.feign;

import com.spring.cloud.commom.utils.ResultVO;
import org.springframework.stereotype.Component;

/**
 * 必须交给spring 管理
 *
 * @author xuweizhi
 */
@Component
@SuppressWarnings("all")
public class FallbackOrderFeignService implements OrderFeignService {
    @Override
    public ResultVO findOrderByUserId(Integer userId) {
        return ResultVO.error(-1, "=======服务降级了========");
    }
}
