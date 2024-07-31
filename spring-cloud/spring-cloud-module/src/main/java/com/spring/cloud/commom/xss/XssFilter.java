package com.spring.cloud.commom.xss;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * XSS过滤
 *
 * @author xuweizhi
 */
public class XssFilter implements Filter {

	@Override
	public void init(FilterConfig config) throws ServletException {
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
		XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(
				(HttpServletRequest) request);
		chain.doFilter(xssRequest, response);
	}

	@Override
	public void destroy() {
	}

}
