package com.saltlux.mydictionary.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.saltlux.mydictionary.service.UserService;
import com.saltlux.mydictionary.vo.UserVo;


public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		UserVo userVo = new UserVo();
		userVo.setId(id);
		userVo.setPassword(password);
		
		UserVo authUser = userService.getUser(userVo);
		if(authUser == null) {
			response.sendRedirect(request.getContextPath()+"/user/login?result=fail");
			return false;
		}
		
		/* session 처리 */
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		response.sendRedirect(request.getContextPath()+"/");
		return false;
	}

}
