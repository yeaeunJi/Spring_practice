package com.saltlux.web.mvc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saltlux.mysite.dao.BoardDao;
import com.saltlux.mysite.dao.UserDao;
import com.saltlux.mysite.vo.PageVo;
import com.saltlux.mysite.vo.UserVo;

public class WebUtil {

	public static void redirect(String url, HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		// 지금과 같이 jsp 에서 insert가 일어난 경우에는 새로고침 시 계속해서 데이터가 중복되어 들어가므로 현재 페이지에 
		// 머무르지 않고 다른 페이지로 이동해야 함
		response.sendRedirect(url); // 응답으로 브라우저에게 이동할 url을 알려주면 브라우즈는 다시 이 url로 이동해달라고 요청
					
	}
	
	public static void forward(String path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(path); // 해당 목적지로 분기할 dispatcher 객체를 얻음
		rd.forward(request, response); // request, response를 위의 목적지로 전달
	}
	
	public static UserVo getAuthUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		return (UserVo) session.getAttribute("authUser");
	}
	

}
