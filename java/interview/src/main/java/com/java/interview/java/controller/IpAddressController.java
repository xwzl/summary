package com.java.interview.java.controller;

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
        return LocalDateTime.now() + " 新版本 " + Inet4Address.getLocalHost().getHostAddress();
    }
}
