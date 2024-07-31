package com.summary.security.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import java.io.IOException;

/**
 * @author xuweizhi
 * @since 2022/01/12 00:18
 */
public class MyExpiredSessionStrategy implements SessionInformationExpiredStrategy {

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        HttpServletResponse response = event.getResponse();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("您已被挤兑下线！");
    }
}
