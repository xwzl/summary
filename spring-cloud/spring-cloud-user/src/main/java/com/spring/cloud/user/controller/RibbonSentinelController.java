package com.spring.cloud.user.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.spring.cloud.commom.module.utils.ResultVO;
import com.spring.cloud.user.feign.OrderFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Ribbon Sentinel 限流
 *
 * @author xuweizhi
 * @since 2021/09/07 15:36
 */
@RestController
@SuppressWarnings("all")
@RequestMapping("/ribbon/sentinel")
public class RibbonSentinelController {

    @Resource
    private RestTemplate sentinelRestTemplate;

    @RequestMapping(value = "/findOrderByUserId/{id}")
//    @SentinelResource(value = "findOrderByUserId",
//        fallback = "fallback",fallbackClass = ExceptionUtil.class,
//        blockHandler = "handleException",blockHandlerClass = ExceptionUtil.class
//    )
    public ResultVO findOrderByUserId(@PathVariable("id") Integer id) {

        //try  catch
        //ribbon实现    ribbon
        String url = "http://spring-cloud-order/order/findOrderByUserId/" + id;
        ResultVO result = sentinelRestTemplate.getForObject(url, ResultVO.class);
        if (id == 4) {
            throw new IllegalArgumentException("非法参数异常");
        }
        return result;
    }


    @Autowired
    private OrderFeignService orderFeignService;

    @RequestMapping(value = "/findOrderByUserIdPlus/{id}")
    public ResultVO findOrderByUserIdPlus(@PathVariable("id") Integer id) {
        //feign调用
        ResultVO result = orderFeignService.findOrderByUserId(id);
        return result;
    }
}
