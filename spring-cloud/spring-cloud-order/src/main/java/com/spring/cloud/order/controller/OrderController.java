package com.spring.cloud.order.controller;

import com.spring.cloud.commom.module.utils.PageVO;
import com.spring.cloud.commom.module.utils.ResultVO;
import com.spring.cloud.order.entity.OrderEntity;
import com.spring.cloud.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * @author xuweizhi
 */
@Slf4j
@RestController
@RequestMapping("/order")
@SuppressWarnings("all")
public class OrderController {
    @Resource
    private OrderService orderService;

    /**
     * 根据用户id查询订单信息
     *
     * @param userId
     * @return
     */
    @RequestMapping("/findOrderByUserId/{userId}")
    public ResultVO findOrderByUserId(@PathVariable("userId") Integer userId) {

//        try {
//            Thread.sleep(8000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //模拟异常
       if(userId==5){
           throw new IllegalArgumentException("非法参数异常");
       }

        log.info("根据userId:" + userId + "查询订单信息");
        List<OrderEntity> orderEntities = orderService.listByUserId(userId);
        return ResultVO.ok().put("orders", orderEntities);
    }

    /**
     * 测试gateway
     * @param request
     * @return
     * @throws Exception
     */
//    @GetMapping("/testgateway")
//    public String testGateway(HttpServletRequest request) throws Exception {
//        log.info("gateWay获取请求头X-Request-color："
//                +request.getHeader("X-Request-color"));
//        return "success";
//    }
//    @GetMapping("/testgateway2")
//    public String testGateway(@RequestHeader("X-Request-color") String color) throws Exception {
//        log.info("gateWay获取请求头X-Request-color："+color);
//        return "success";
//    }
//    @GetMapping("/testgateway3")
//    public String testGateway3(@RequestParam("color") String color) throws Exception {
//        log.info("gateWay获取请求参数color:"+color);
//        return "success";
//    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public ResultVO list(@RequestParam Map<String, Object> params) {
        PageVO page = orderService.queryPage(params);

        return ResultVO.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public ResultVO info(@PathVariable("id") Integer id) {
        OrderEntity order = orderService.getById(id);
        return ResultVO.ok().put("order", order);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public ResultVO save(@RequestBody OrderEntity order) {
        orderService.save(order);

        return ResultVO.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public ResultVO update(@RequestBody OrderEntity order) {
        orderService.updateById(order);

        return ResultVO.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public ResultVO delete(@RequestBody Integer[] ids) {
        orderService.removeByIds(Arrays.asList(ids));

        return ResultVO.ok();
    }

}
