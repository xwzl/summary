package com.summary.security.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

/**
 * 比如想实现登录成功后重定向其他页面，可以利用 AuthenticationFailureHandler 接口实现自定义的认证成功控制器。
 *
 * @author xuweizhi
 * @since 2022/01/11 23:26
 */
@Data
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private String redirectUrl;

    public MyAuthenticationFailureHandler(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.sendRedirect(redirectUrl);
    }
}
