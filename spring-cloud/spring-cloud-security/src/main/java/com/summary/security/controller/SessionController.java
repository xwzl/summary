package com.summary.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Session 管理
 *
 * @author xuweizhi
 * @since 2022/01/11 22:57
 */
@Slf4j
@Controller
@RequestMapping("/session")
public class SessionController {

    @GetMapping("/invalid")
    public String index() {
        log.info("Token 失效");
        return "redirect:/login.html";
    }


}
