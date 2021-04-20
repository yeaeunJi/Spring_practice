package com.saltlux.mysite.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saltlux.mysite.db.Mongo;
import com.saltlux.mysite.db.Mysql;
import com.saltlux.mysite.vo.UserVo;
import com.saltlux.web.mvc.WebUtil;


public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//	System.out.println("doGet()!!!!!!!!");
		int visitCount = 0;

		//	getServletContext().setAttribute(getServletName(), response); // 어플리케이션 범위로 객체 저장

		// 만약 mongodb 로 접속한다면 임의로 세션 부여
		// session 매니저에게 요청 : true(없으면 생성해서 반환), false(없으면 null반환)
		try {
			if( Mysql.getConnection() == null) {
				System.out.println("Mysql이 연결되지 않으므로 자동으로 세션을 부여합니다.");
			HttpSession session = request.getSession(true); 
			UserVo authUser = new UserVo();
			authUser.setNo(99L);
			authUser.setName("admin99");
			System.out.println(authUser);
			session.setAttribute("authUser", authUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 쿠키 읽기
		Cookie[] cookies = request.getCookies();
		if(cookies != null && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				if("visitCount".equals(cookie.getName())) {
					visitCount = Integer.parseInt(cookie.getValue());
				}
			}
		}

		visitCount++;
		// 쿠키 쓰기 : 쿠키는 문자열로 써야함
		Cookie cookie = new Cookie("visitCount", String.valueOf(visitCount));
		cookie.setPath(request.getContextPath());
		cookie.setMaxAge(24*60*60); // 1day
		response.addCookie(cookie);

		WebUtil.forward("/WEB-INF/views/main/index.jsp", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//		System.out.println("doPost()!!!!!!!!");
		doGet(request, response);
	}

	@Override
	public void destroy() {
		//		System.out.println("destroy()!!!!!!");
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		//		String configPath = this.getServletConfig().getInitParameter("config");
		//		System.out.println("init()!!!!!!"+configPath);
		super.init();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//		System.out.println("service()!!!!");
		super.service(req,resp);
	}

}
