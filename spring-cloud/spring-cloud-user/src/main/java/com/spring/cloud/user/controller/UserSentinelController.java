package com.spring.cloud.user.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.spring.cloud.commom.module.utils.PageVO;
import com.spring.cloud.commom.module.utils.ResultVO;
import com.spring.cloud.user.entity.UserEntity;
import com.spring.cloud.user.feign.OrderFeignService;
import com.spring.cloud.user.sentinel.handler.CommonBlockHandler;
import com.spring.cloud.user.sentinel.handler.CommonFallback;
import com.spring.cloud.user.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;


/**
 * UserSentinelController Controller
 *
 * @author xuweizhi
 * @since 2021/09/06 16:02
 */
@RestController
@RequestMapping(value = "/user/sentinel")
public class UserSentinelController {

    @Resource
    private UserService userService;

    @Resource
    OrderFeignService orderFeignService;

    @RequestMapping(value = "/findOrderByUserId/{id}")
    // @SentinelResource(value = "findOrderByUserId", blockHandlerClass = CommonBlockHandler.class, blockHandler = "handleException2")
    public ResultVO findOrderByUserId(@PathVariable("id") Integer id) {

//        try {
//            // 模拟测试并发线程数限流
//            Thread.sleep(900);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        // findOrderByUserId  限流规则  2    sentinel dashboard 定义规则

        //feign调用
        ResultVO orderByUserId = orderFeignService.findOrderByUserId(id);
        return orderByUserId;
    }


    /**
     * 列表
     */
    @RequestMapping("/list")
    @SentinelResource(value = "userlist", blockHandlerClass = CommonBlockHandler.class, blockHandler = "handleException", fallback = "fallback")
    public ResultVO list(@RequestParam Map<String, Object> params) {
        PageVO page = userService.queryPage(params);
        // int i=1/0;
        return ResultVO.ok().put("page", page);
    }

    public ResultVO handleException(@RequestParam Map<String, Object> params, BlockException exception) {
        return ResultVO.error(-1, "===被限流降级啦===");
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @SentinelResource(value = "userinfo", blockHandlerClass = CommonBlockHandler.class,
            blockHandler = "handleException2", fallbackClass = CommonFallback.class, fallback = "fallback")
    public ResultVO info(@PathVariable("id") Integer id) {
        UserEntity user = userService.getById(id);

        if (id == 4) {
            throw new IllegalArgumentException("异常参数");
        }

        return ResultVO.ok().put("user", user);
    }

    public ResultVO handleException2(@PathVariable("id") Integer id, BlockException exception) {
        return ResultVO.error(-1, "===被限流降级啦===");
    }

    public ResultVO fallback(@PathVariable("id") Integer id, Throwable e) {
        return ResultVO.error(-1, "===被熔断降级啦===" + e.getMessage());
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
