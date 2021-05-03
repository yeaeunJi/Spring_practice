package com.saltlux.mydictionary.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.saltlux.mydictionary.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(handler instanceof HandlerMethod == false) {
			return true;  // 통과시킴
		}
		
		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		
		// 3. Method에 @Auth가 달려 있는지 체크 
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		// 4-1 . Method에 @Auth가 안달려 있으면 
		// 4-2. class에도 @Auth가 달려있지 않으면 
		if(auth == null) {
			auth = handlerMethod.getBean().getClass().getAnnotation(Auth.class);
			if (auth == null) {
				return true;				
			}
		}
		
		/* 인증 확인 */
		// 5. @Auth가 달려 있는 경우에는 인증(Authetication) 여부 확인
		HttpSession session = request.getSession();
		if(session == null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		return true;
	}

}
