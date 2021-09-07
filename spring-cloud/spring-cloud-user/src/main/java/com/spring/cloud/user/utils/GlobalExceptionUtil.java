package com.spring.cloud.user.utils;

import com.alibaba.cloud.sentinel.rest.SentinelClientHttpResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.cloud.commom.module.utils.ResultVO;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;

@SuppressWarnings("all")
public class GlobalExceptionUtil {
    /**
     * 注意： static修饰，参数类型不能出错
     * @param request  org.springframework.http.HttpRequest
     * @param body
     * @param execution
     * @param ex
     * @return
     */
    public static SentinelClientHttpResponse handleException(HttpRequest request,
                                                             byte[] body, ClientHttpRequestExecution execution, BlockException ex) {
        ResultVO r = ResultVO.error(-1, "===被限流啦===");
        try {
            return new SentinelClientHttpResponse(new ObjectMapper().writeValueAsString(r));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static SentinelClientHttpResponse fallback(HttpRequest request,
                                                      byte[] body, ClientHttpRequestExecution execution, BlockException ex) {
        ResultVO r = ResultVO.error(-2, "===被异常降级啦===");
        try {
            return new SentinelClientHttpResponse(new ObjectMapper().writeValueAsString(r));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
