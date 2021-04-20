package com.saltlux.mysite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saltlux.mysite.dao.UserDao;
import com.saltlux.mysite.vo.UserVo;
import com.saltlux.web.mvc.WebUtil;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("a");

		if ("joinform".equals(action)) {
			WebUtil.forward("/WEB-INF/views/user/joinform.jsp", request, response);

		} else if ("join".equals(action)) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");

			UserVo vo = new UserVo();
			vo.setName(name);
			vo.setEmail(email);
			vo.setGender(gender);
			vo.setPassword(password);

			boolean result = new UserDao().insert(vo);
			WebUtil.redirect(request.getContextPath()+"/user?a=joinsuccess", request, response);

		} else if ("joinsuccess".equals(action)) {
			WebUtil.forward("/WEB-INF/views/user/joinsuccess.jsp", request, response);
		}  else if ("logout".equals(action)) {
			HttpSession session = request.getSession();

			// 로그아웃 처리
			if(session != null && session.getAttribute("authUser")!= null) {
				session.removeAttribute("authUser");
				session.invalidate();
			}

			WebUtil.redirect(request.getContextPath()+"/main", request, response);
		} else if ("loginform".equals(action)) {
			WebUtil.forward("/WEB-INF/views/user/loginform.jsp", request, response);
		}  else if ("login".equals(action)) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			UserVo vo = new UserVo();
			vo.setEmail(email);
			vo.setPassword(password);

			UserVo authUser = new UserDao().findByEmailAndPassword(vo);
			if(authUser == null) {
				request.setAttribute("authResult", "fail");
				WebUtil.forward("/WEB-INF/views/user/loginform.jsp", request, response);
				return;	
			}

			// 인증 처리
			// session 매니저에게 요청 : true(없으면 생성해서 반환), false(없으면 null반환)\
			HttpSession session = request.getSession(true); 
			session.setAttribute("authUser", authUser);

			WebUtil.redirect(request.getContextPath()+"/main", request, response);
		}else if ("updateform".equals(action)) {
			// Access Control(접근 제어)
			HttpSession session = request.getSession(false);
			if(session == null) {
				WebUtil.redirect(request.getContextPath()+"/main", request, response);
				return;
			}
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			if(authUser == null) {
				WebUtil.redirect(request.getContextPath()+"/main", request, response);
				return;
			}
			Long no = authUser.getNo();
			UserVo userVo = new UserDao().findByNo(no);
			request.setAttribute("userVo", userVo);
			WebUtil.forward("/WEB-INF/views/user/updateform.jsp", request, response);
		}  else if ("update".equals(action)) {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");
			Long no = Long.parseLong(request.getParameter("no"));

			UserVo vo = new UserVo();
			boolean result = false;

			if(password.isBlank() && !name.isBlank()) {
				vo.setName(name);
				vo.setGender(gender);
				vo.setNo(no);
				result = new UserDao().updateNameAndGender(vo);
			} else {
				vo.setName(name);
				vo.setGender(gender);
				vo.setNo(no);
				vo.setPassword(password);
				result = new UserDao().updateAll(vo);
			}

			if (result) {
				UserVo authUser = new UserDao().findByNo(no);
				if(authUser == null) {
					WebUtil.forward("/WEB-INF/views/main", request, response);
					return;	
				}
				HttpSession session = request.getSession(true); 
				session.setAttribute("authUser", authUser);
			}
			WebUtil.redirect(request.getContextPath()+"/main", request, response);
		}
		else {
			WebUtil.redirect(request.getContextPath()+"/main", request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
