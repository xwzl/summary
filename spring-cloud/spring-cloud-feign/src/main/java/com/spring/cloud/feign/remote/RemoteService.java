package com.spring.cloud.feign.remote;

import com.spring.cloud.commom.utils.ResultVO;
import com.spring.cloud.feign.vo.OrderVo;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestBody;

@SuppressWarnings("all")
public interface RemoteService {

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @RequestLine("GET /order/findOrderByUserId/{userId}")
    ResultVO findOrderByUserId(@Param("userId") Integer userId);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @RequestLine("POST /order/save")
    ResultVO save(@RequestBody OrderVo order);

}
