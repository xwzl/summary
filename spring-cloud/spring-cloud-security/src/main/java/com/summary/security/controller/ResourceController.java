package com.summary.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * 资源地址
 *
 * @author xuweizhi
 * @since 2022/01/11 17:19
 */
@RestController
@RequestMapping("resource")
public class ResourceController {

    @GetMapping("test")
    public String test() {
        return LocalDateTime.now().toString();
    }

    /**
     * 会话管理（Session)用户认证通过后，为了避免用户的每次操作都进行认证可将用户的信息保存在会话中。spring security提供会话管理，
     * 认证通过后将身份信息放入SecurityContextHolder上下文，SecurityContext与当前线程进行绑定，方便获取用户身份。
     *
     * @return //
     */
    @GetMapping("/getUser")
    public String getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            username = userDetails.getUsername();
        }
        return username;
    }
}
