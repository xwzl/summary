package com.spring.cloud.user.sentinel.handler;

import com.spring.cloud.commom.utils.ResultVO;

/**
 * CommonFallback
 *
 * @author xuweizhi
 * @since 2021/09/06 16:02
 */
public class CommonFallback {
    /**
     * 注意： 必须为 static 函数
     *
     * @param e
     * @return
     */
    public static ResultVO fallback(Integer id, Throwable e) {
        return ResultVO.error(-2, "===被异常降级啦===" + e.getMessage());
    }
}
