package com.spring.cloud.dubbo.consumer.feign;

import com.spring.cloud.dubbo.api.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;



@SuppressWarnings("all")
@FeignClient(value = "spring-cloud-dubbo-provider-user",path = "/user")
public interface UserFeignService {

    @RequestMapping("/list")
    List<User> list();

    @RequestMapping("/getById/{id}")
    User getById(@PathVariable("id") Integer id);
}
