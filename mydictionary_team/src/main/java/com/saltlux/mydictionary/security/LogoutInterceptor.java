package com.saltlux.mydictionary.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LogoutInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		if(session != null) {
			/* 로그아웃 처리*/
			session.removeAttribute("authUser");
			session.invalidate(); // 즉시 제거
		}
		
		response.sendRedirect(request.getContextPath());
		
		return false;
	}


}
