package com.summary.security.config;

import com.summary.security.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 自定义登出SuccessHandler，在里面出来登录用户的session，不然虽然登出但是session还有效，下次登录会累加session次数，
 * 超出上面配置的maximumSessions，会提示 "Maximum sessions of {n} for this principal exceeded"
 *
 * @author xuweizhi
 * @since 2022/01/12 17:15
 */
@Slf4j
public class MyLogOutSuccessHandler implements LogoutSuccessHandler {

    private SessionRegistry sessionRegistry;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        log.info(authentication.toString());
        log.info(authentication.getName());
        List<Object> o = sessionRegistry.getAllPrincipals();
        //退出成功后删除当前用户session
        for (Object principal : o) {
            if (principal instanceof User) {
                final User loggedUser = (User) principal;
                if (authentication.getName().equals(loggedUser.getUsername())) {
                    List<SessionInformation> sessionsInfo = sessionRegistry.getAllSessions(principal, false);
                    if (null != sessionsInfo && !sessionsInfo.isEmpty()) {
                        for (SessionInformation sessionInformation : sessionsInfo) {
                            sessionInformation.expireNow();
                        }
                    }
                }
            }
        }
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write("退出成功，请重新登录");
    }
}
