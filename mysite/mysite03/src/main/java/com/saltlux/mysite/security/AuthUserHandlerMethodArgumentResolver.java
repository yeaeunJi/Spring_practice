package com.saltlux.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.saltlux.mysite.vo.UserVo;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// 판단
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
		
		// @AuthUser가 안붙어 있는 경우
		if(authUser==null) {
			return false;
		}
		
		// 파라미터 타입이 UserVo가 아니면... 
		if(!parameter.getParameterType().equals(UserVo.class)) {
			return false;	
		}
		System.out.println("supportsParameter : ");
		return true;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		if(!supportsParameter(parameter)) { // @AuthUser에서는 UserVo만 사용 가능하도록
			return WebArgumentResolver.UNRESOLVED; // 다음 리졸버가 작동 
		}
		// NativeWebRequest webRequest : request 
		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
		HttpSession session = request.getSession();
		if(session == null) {
			System.out.println("resolveArgument : session is null ");
			return null;
		}
		
		System.out.println("resolveArgument : "+session.getAttribute("authUser"));
		return session.getAttribute("authUser");
	}

}
