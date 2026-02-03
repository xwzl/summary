package com.spring.cloud.order.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc_v6x.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.springframework.stereotype.Component;


/**
 * 自定义sentinel异常返回信息
 * @author xuweizhi
 */
@Component
public class CustomerBlockHandler implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String s, BlockException e) throws Exception {
        SentinelErrorMsg sentinelErrorMsg = new SentinelErrorMsg();
        if (e instanceof FlowException) {
            sentinelErrorMsg.setMsg("接口限流了");
            sentinelErrorMsg.setStatus(101);
        } else if (e instanceof DegradeException) {
            sentinelErrorMsg.setMsg("服务降级了");
            sentinelErrorMsg.setStatus(102);
        } else if (e instanceof ParamFlowException) {
            sentinelErrorMsg.setMsg("热点参数限流了");
            sentinelErrorMsg.setStatus(103);
        } else if (e instanceof SystemBlockException) {
            sentinelErrorMsg.setMsg("系统规则（负载/...不满足要求）");
            sentinelErrorMsg.setStatus(104);
        } else if (e instanceof AuthorityException) {
            sentinelErrorMsg.setMsg("授权规则不通过");
            sentinelErrorMsg.setStatus(105);
        }
        // http状态码
        httpServletResponse.setStatus(500);
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        // spring mvc自带的json操作工具，叫jackson
        new ObjectMapper()
                .writeValue(
                        httpServletResponse.getWriter(),
                        sentinelErrorMsg
                );
    }

    @PostConstruct
    public void init() {
        new CustomerBlockHandler();
    }



    @Data
    static class SentinelErrorMsg {
        private Integer status;
        private String msg;
    }
}
