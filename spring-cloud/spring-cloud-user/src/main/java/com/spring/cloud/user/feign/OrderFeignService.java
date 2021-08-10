package com.spring.cloud.user.feign;


import com.spring.cloud.commom.module.utils.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@SuppressWarnings("all")
@FeignClient(value = "spring-cloud-order", path = "/order")
public interface OrderFeignService {

    @RequestMapping("/findOrderByUserId/{userId}")
    public ResultVO findOrderByUserId(@PathVariable("userId") Integer userId);
}
