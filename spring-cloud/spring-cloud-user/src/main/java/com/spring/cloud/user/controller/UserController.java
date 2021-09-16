package com.spring.cloud.user.controller;

import com.spring.cloud.commom.module.utils.PageVO;
import com.spring.cloud.commom.module.utils.ResultVO;
import com.spring.cloud.user.entity.UserEntity;
import com.spring.cloud.user.feign.OrderFeignService;
import com.spring.cloud.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author xuweizhi
 */
@RestController
@RequestMapping("/user")
@Slf4j
@SuppressWarnings("all")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private OrderFeignService orderFeignService;


    @Value("${common.age}")
    private String age;

    @Value("${common.name}")
    private String name;

    @GetMapping("/index")
    public String hello() {
        return name + "," + age;
    }


    @RequestMapping(value = "/findOrderByUserId/{id}")
    public ResultVO findOrderByUserId(@PathVariable("id") Integer id) {
        log.info("根据userId:" + id + "查询订单信息");
        // RestTemplate调用
//        String url = "http://localhost:8020/order/findOrderByUserId/"+id;
//        R result = restTemplate.getForObject(url,R.class);

        //模拟ribbon实现
        //String url = getUri("mall-order")+"/order/findOrderByUserId/"+id;
        // 添加@LoadBalanced
        String url = "http://spring-cloud-order/order/findOrderByUserId/" + id;
        // ResultVO result = restTemplate.getForObject(url, ResultVO.class);

        //feign调用
        //R result = orderFeignService.findOrderByUserId(id);

        // return result;
        return null;
    }


    @Resource
    private DiscoveryClient discoveryClient;

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

    private AtomicInteger nextIndex = new AtomicInteger(0);

    private int incrementAndGetModulo(int modulo) {
        for (; ; ) {
            int current = nextIndex.get();
            int next = (current + 1) % modulo;
            if (nextIndex.compareAndSet(current, next) && current < modulo) {
                return current;
            }
        }
    }


    /**
     * 列表
     */
    @RequestMapping("/list")
    public ResultVO list(@RequestParam Map<String, Object> params) {
        PageVO page = userService.queryPage(params);

        return ResultVO.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public ResultVO info(@PathVariable("id") Integer id) {
        UserEntity user = userService.getById(id);

        return ResultVO.ok().put("user", user);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public ResultVO save(@RequestBody UserEntity user) {
        userService.save(user);

        return ResultVO.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public ResultVO update(@RequestBody UserEntity user) {
        userService.updateById(user);

        return ResultVO.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public ResultVO delete(@RequestBody Integer[] ids) {
        userService.removeByIds(Arrays.asList(ids));

        return ResultVO.ok();
    }

}
