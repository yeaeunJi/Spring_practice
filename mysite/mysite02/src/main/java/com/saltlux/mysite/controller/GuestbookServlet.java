package com.saltlux.mysite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saltlux.mysite.dao.GuestbookDao;
import com.saltlux.mysite.vo.GuestbookVo;
import com.saltlux.web.mvc.WebUtil;

public class GuestbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("a");

		if ("insert".equals(action)) {
			GuestbookVo vo = new GuestbookVo();
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String contents = request.getParameter("content");
			vo.setName(name);
			vo.setPassword(password);
			vo.setContents(contents);

			new GuestbookDao().insert(vo);
			WebUtil.redirect(request.getContextPath()+"/guestbook", request, response);
		} else if("deleteform".equals(action)) {
			request.setAttribute("no", request.getParameter("no")); // request안에 보내줄 데이터를 담음
			WebUtil.forward("/WEB-INF/views/guestbook/deleteform.jsp", request, response);
		}  else if("delete".equals(action)) {
			Long no = Long.parseLong(request.getParameter("no"));
			String password = request.getParameter("password");

			GuestbookVo vo = new GuestbookVo();
			vo.setNo(no);
			vo.setPassword(password);
			new GuestbookDao().delete(vo);
			WebUtil.redirect(request.getContextPath()+"/guestbook", request, response);
		} else {
			List<GuestbookVo> list = new GuestbookDao().findAll();
			request.setAttribute("list", list); // request안에 보내줄 데이터를 담음
			WebUtil.forward("/WEB-INF/views/guestbook/index.jsp", request, response);
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
