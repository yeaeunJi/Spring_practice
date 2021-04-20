package com.saltlux.mysite.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// request 처리
		request.setCharacterEncoding("utf-8");
		// 세션 확인
		chain.doFilter(request, response); 

		// 재귀호출
		// response 처리
		// 세션업으면 쿠키 생성?
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
