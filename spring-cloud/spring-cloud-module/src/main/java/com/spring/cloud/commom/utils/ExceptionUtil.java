package com.spring.cloud.commom.utils;

import com.alibaba.csp.sentinel.slots.block.BlockException;

@SuppressWarnings("all")
public class ExceptionUtil {

    public static ResultVO fallback(Integer id, Throwable e) {
        return ResultVO.error(-102, "=========被异常降级啦===");
    }

    public static ResultVO handleException(Integer id, BlockException e) {
        return ResultVO.error(-101, "==========被限流啦===");
    }
}
