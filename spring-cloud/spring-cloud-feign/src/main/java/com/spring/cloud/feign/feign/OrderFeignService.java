package com.spring.cloud.feign.feign;

import com.spring.cloud.commom.utils.ResultVO;
import com.spring.cloud.feign.vo.OrderVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@SuppressWarnings("all")
//FeignConfig局部配置
// @FeignClient(value = "spring-cloud-order",path = "/order",configuration = FeignConfig.class)
@FeignClient(value = "spring-cloud-order", path = "/order")
public interface OrderFeignService {

    @RequestMapping("/findOrderByUserId/{userId}")
    ResultVO findOrderByUserId(@PathVariable("userId") Integer userId);


    @RequestMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResultVO save(@RequestBody OrderVo order);

//    @RequestLine("GET /findOrderByUserId/{userId}")
//    ResultVO findOrderByUserId(@Param("userId") Integer userId);
}
