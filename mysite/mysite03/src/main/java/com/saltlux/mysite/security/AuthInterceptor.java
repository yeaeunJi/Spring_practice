package com.saltlux.mysite.security;

import javax.servlet.annotation.HandlesTypes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.saltlux.mysite.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//1. handler 종류 확인
		if(handler instanceof HandlerMethod == false) {
			// DefaultServletHandler가 처리하는 경우(보통, assets의 정적 자원 접근)
			// 정적 자원 요청을 다른 곳에서 처리할 수도 있음 : sping-servlet.xml에서 인터셉터안에  <mvc:exclude-mapping path ="/assets/**"/> 명시
			// 하지만 이렇게 처리하는 것이 안전함? 
			return true;  // 통과시킴
		}
		
		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		
		// 3. Method에 @Auth가 달려 있는지 체크 
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		// 4. Method에 @Auth가 안달려 있으면 
		System.out.println("handlerMethod.getBean().getClass() : "+handlerMethod.getBean().getClass());
		System.out.println("handlerMethod.getBean().getClass().getAnnotation(Auth.class) : "+handlerMethod.getBean().getClass().getAnnotation(Auth.class));
		
		if(auth == null) {
			// class에 @Auth가 달려 있는지 체크
			
			return true;				
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
		
		if("user".equals(authUser.getRole()) && "admin".equals(auth.role()))
		{
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		
		return true;
	}

}
