package com.summary.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录
 *
 * @author xuweizhi
 * @since 2022/01/11 22:57
 */
@Controller
@RequestMapping
public class LoginController {

    @PostMapping("/index")
    public String index() {
        return "redirect:/index.html";
    }

    @PostMapping("/errors")
    public String error() {
        return "redirect:/login.html";
    }
}
