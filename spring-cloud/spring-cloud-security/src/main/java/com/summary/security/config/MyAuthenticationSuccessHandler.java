package com.summary.security.config;

import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 比如想实现登录成功后重定向其他页面，可以利用 AuthenticationSuccessHandler 接口实现自定义的认证成功控制器。
 *
 * @author xuweizhi
 * @since 2022/01/11 23:26
 */
@Data
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private String redirectUrl;

    public MyAuthenticationSuccessHandler(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.sendRedirect(redirectUrl);
    }
}
