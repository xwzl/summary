package com.spring.cloud.ribbon.controller;


import com.spring.cloud.commom.module.utils.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@SuppressWarnings("all")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/findOrderByUserId/{id}")
    public ResultVO findOrderByUserId(@PathVariable("id") Integer id) {
        // RestTemplate调用
        //String url = "http://localhost:8020/order/findOrderByUserId/"+id;
        //模拟ribbon实现  从注册中心获取service list  ----> service
        //
        //String url = getUri("mall-order")+"/order/findOrderByUserId/"+id;
        // 添加@LoadBalanced
        String url = "http://spring-cloud-order/order/findOrderByUserId/" + id;
        ResultVO result = restTemplate.getForObject(url, ResultVO.class);

        return result;
    }

    @RequestMapping(value = "/findAccountByUserId/{id}")
    public ResultVO findAccountByUserId(@PathVariable("id") Integer id) {

        String url = "http://mall-account/account/infoByUserId/" + id;
        ResultVO result = restTemplate.getForObject(url, ResultVO.class);

        return result;
    }


    /**
     * 模拟 ribbon 负载均衡
     */
    @Resource
    private DiscoveryClient discoveryClient;

    private AtomicInteger nextIndex = new AtomicInteger(0);

    @GetMapping("getUri")
    public String getUri(String serviceName) {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);
        if (serviceInstances == null || serviceInstances.isEmpty()) {
            return null;
        }
        int serviceSize = serviceInstances.size();
        //轮询
        int indexServer = incrementAndGetModulo(serviceSize);
        return serviceInstances.get(indexServer).getUri().toString();
    }



    private int incrementAndGetModulo(int modulo) {
        for (; ; ) {
            int current = nextIndex.get();
            int next = (current + 1) % modulo;
            if (nextIndex.compareAndSet(current, next) && current < modulo) {
                return current;
            }
        }
    }


}
