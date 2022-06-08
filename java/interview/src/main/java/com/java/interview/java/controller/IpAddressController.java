package com.java.interview.java.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

/**
 * IP 地址
 *
 * @author xuweizhi
 * @since 2022/05/23 15:27
 */
@RestController
@RequestMapping("hello")
public class IpAddressController {

    @GetMapping("printIpAddress")
    public String printIpAddress() throws UnknownHostException {
        return LocalDateTime.now() + " 新版本 222" + Inet4Address.getLocalHost().getHostAddress();
    }

    public static void main(String[] args) {

        Object parse = JSON.parse("{  \"2\": {    \"1\": \"盘亏\",    \"2\": \"亏损\",    \"3\": \"科室领用\",    \"4\": \"外借\",    \"5\": \"其他\",    \"99\": \"留诊急观察\"  }}");
        System.out.println(JSON.toJSONString(parse));
        System.out.println("{" +
                "  \"2\": {" +
                "    \"1\": \"盘亏\"," +
                "    \"2\": \"亏损\"," +
                "    \"3\": \"科室领用\"," +
                "    \"4\": \"外借\"," +
                "    \"5\": \"其他\"," +
                "    \"99\": \"留诊急观察\"" +
                "  }" +
                "}");
    }
}
