package com.spring.cloud.user.sentinel.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.spring.cloud.commom.module.utils.ResultVO;

import java.util.Map;

/**
 * CommonBlockHandler
 *
 * @author xuweizhi
 * @since 2021/09/06 16:02
 */
public class CommonBlockHandler {

    /**
     * 注意： 必须为 static 函数   多个方法之间方法名不能一样
     *
     * @param exception
     * @return
     */
    public static ResultVO handleException(Map<String, Object> params, BlockException exception) {
        return ResultVO.error(-1, "===被限流啦===" + exception);
    }

    public static ResultVO handleException2(Integer id, BlockException exception) {
        return ResultVO.error(-1, "===被限流啦===" + exception);
    }

    public static String handleException3(BlockException exception) {
        return "===被限流啦===" + exception;
    }
}
