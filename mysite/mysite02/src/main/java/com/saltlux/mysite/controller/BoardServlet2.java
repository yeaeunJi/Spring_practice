package com.saltlux.mysite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saltlux.mysite.dao.BoardDao2;
import com.saltlux.mysite.vo.BoardVo2;
import com.saltlux.mysite.vo.PageVo;
import com.saltlux.mysite.vo.UserVo;
import com.saltlux.web.mvc.WebUtil;

public class BoardServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Long showNum = 2L;
	private Long pageShowNum = 3L;
	private BoardDao2 dao = new BoardDao2();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("a");

		if ("writeform".equals(action)) {
			WebUtil.forward("/WEB-INF/views/board2/write.jsp", request, response);
		} 
		else if ("write".equals(action)) {
			UserVo authUser = WebUtil.getAuthUser(request, response); // session 객체에 담겨 있는 name 가져오기

			if (authUser == null) {
				WebUtil.redirect(request.getContextPath()+"/loginform", request, response);
				return;
			}

			int userNo =  Integer.parseInt(authUser.getNo().toString());
			String writer = authUser.getName();
			String title = request.getParameter("title");
			String contents = request.getParameter("content");

			if(title.isBlank() || contents.isBlank())
				WebUtil.redirect(request.getContextPath()+"/board2", request, response);
			else {
				int gNo = dao.getNewGNo();
				BoardVo2 vo = new BoardVo2(title, writer, contents, userNo, gNo, 1, 0);
				dao.insert(vo);
				WebUtil.redirect(request.getContextPath()+"/board2", request, response);
			}
		} 
		else if ("replyform".equals(action)) {
			UserVo authUser = WebUtil.getAuthUser(request, response);

			if (authUser == null) {
				WebUtil.redirect(request.getContextPath()+"/loginform", request, response);
				return;
			}

			String no = request.getParameter("no");
			request.setAttribute("no", no);
			WebUtil.forward("/WEB-INF/views/board2/reply.jsp", request, response);
		} 
		else if ("reply".equals(action)) {
			// session 객체에 담겨 있는 name 가져오기
			UserVo authUser = WebUtil.getAuthUser(request, response);

			if (authUser == null) {
				WebUtil.redirect(request.getContextPath()+"/loginform", request, response);
				return;
			}

			int userNo =  Integer.parseInt(authUser.getNo().toString());
			String contents = request.getParameter("contents");
			String title = request.getParameter("title");
			String no =  request.getParameter("no");
			String writer = authUser.getName();

			if(contents.isBlank())
				WebUtil.redirect(request.getContextPath()+"/board2", request, response);
			else {
				BoardVo2 parent = dao.getParentInfo(no);

				if(parent ==null) {
					WebUtil.redirect(request.getContextPath()+"/board2", request, response);
					return;
				}

				int gNo = parent.getgNo(); 
				int oNo = parent.getoNo();
				int depth = parent.getDepth() + 1;
				int maxONo = dao.getMaxONo(gNo);

				if (maxONo > oNo) {  // 부모 oNo가 maxONo 보다 작으면 oNo이상 +1
					dao.updateOrderNo(gNo, oNo, 1); // 답글에 답글이 달릴 경우에는 해당 답글 뒤의 order가 밀림
				}

				oNo += 1; 

				BoardVo2 vo = new BoardVo2(title, writer, contents, userNo, gNo, oNo, depth);
				dao.insert(vo);
				WebUtil.redirect(request.getContextPath()+"/board2", request, response);
			}
		} 
		else if ("view".equals(action)) {
			String no = request.getParameter("no");
			BoardVo2 vo = dao.findOne(no);
			if(vo!=null) {
				request.setAttribute("vo", vo);
				WebUtil.forward("/WEB-INF/views/board2/view.jsp", request, response);
			}else {
				WebUtil.forward("/WEB-INF/views/board2/index.jsp", request, response);
				return;
			}
		}
		else if ("updateform".equals(action)) {
			String no = request.getParameter("no");
			BoardVo2 vo = dao.findOne(no);
			if(vo!=null) {
				request.setAttribute("vo", vo);
				WebUtil.forward("/WEB-INF/views/board2/modify.jsp", request, response);
			}else {
				WebUtil.forward("/WEB-INF/views/board2/index.jsp", request, response);
			}
		} else if ("update".equals(action)) {
			String no = request.getParameter("no");
			String title = request.getParameter("title");
			String contents = request.getParameter("content");

			if(title.isBlank() || contents.isBlank())
				WebUtil.redirect(request.getContextPath()+"/board2", request, response);
			else {
				BoardVo2 vo = new BoardVo2(no, title, contents);
				dao.update(vo);
				WebUtil.redirect(request.getContextPath()+"/board2", request, response);
			}
		} else if ("delete".equals(action)) {
			String no = request.getParameter("no");
			UserVo authUser = WebUtil.getAuthUser(request, response);

			if (authUser == null) {
				WebUtil.redirect(request.getContextPath()+"/loginform", request, response);
				return;
			}

			BoardVo2 vo = dao.findOne(no); 
			if (!dao.isGetChild(vo)	) {
				dao.delete(no);
				dao.updateOrderNo(vo.getgNo(), vo.getoNo(), -1);
				WebUtil.redirect(request.getContextPath()+"/board2", request, response);
			}else {
				WebUtil.redirect(request.getContextPath()+"/board2?msg=false", request, response);
			}
		} 
		else if ("onePageBefore".equals(action)) {
			Long curPage = request.getParameter("curPage") == null?1L:Long.parseLong(request.getParameter("curPage"));
			Long endPage = request.getParameter("endPage") == null?1L:Long.parseLong(request.getParameter("endPage"));
			Long startPage = request.getParameter("startPage") == null?1L:Long.parseLong(request.getParameter("startPage"));

			startPage -= pageShowNum;

			if (curPage % pageShowNum == 1) {
				endPage =curPage-1;
			}

			curPage --;

			Long start = (curPage-1)*showNum;
			PageVo page = dao.paging(curPage, start, showNum, startPage, endPage, pageShowNum);

			page.setPageShowNum(pageShowNum);

			List<BoardVo2> list = dao.findAll(page);
			request.setAttribute("list", list);
			request.setAttribute("page", page);
			WebUtil.forward("/WEB-INF/views/board2/index.jsp", request, response);
		} 	
		else if ("onePageNext".equals(action)) {
			Long curPage = request.getParameter("curPage") == null?1L:Long.parseLong(request.getParameter("curPage"));
			Long endPage = request.getParameter("endPage") == null?1L:Long.parseLong(request.getParameter("endPage"));
			Long totalPage = request.getParameter("totalPage") == null?1L:Long.parseLong(request.getParameter("totalPage"));
			Long startPage = request.getParameter("startPage") == null?1L:Long.parseLong(request.getParameter("startPage"));

			if (curPage % pageShowNum == 0 && totalPage != curPage)
				startPage += pageShowNum;

			if (totalPage - startPage < pageShowNum)	endPage = totalPage;
			else if(curPage % pageShowNum == 0) {
				endPage += pageShowNum;
			}

			curPage ++;

			Long start = (curPage-1)*showNum;
			PageVo page = dao.paging(curPage, start, showNum, startPage, endPage, pageShowNum);
			List<BoardVo2> list = dao.findAll(page);

			request.setAttribute("list", list);
			request.setAttribute("page", page);
			WebUtil.forward("/WEB-INF/views/board2/index.jsp", request, response);
		} 	
		else if ("mulPageNext".equals(action)) {
			Long endPage = request.getParameter("endPage") == null?1L:Long.parseLong(request.getParameter("endPage"));
			Long totalPage = request.getParameter("totalPage") == null?1L:Long.parseLong(request.getParameter("totalPage"));
			Long start = endPage*showNum;
			PageVo page =  dao.paging(endPage+1, start, showNum, endPage+1, endPage, pageShowNum);

			if (totalPage - (endPage+1) < pageShowNum)	endPage = totalPage;
			else if(endPage % pageShowNum == 0) {
				endPage += pageShowNum;
			}

			page.setEndPage(endPage);
			List<BoardVo2> list = dao.findAll(page);

			request.setAttribute("list", list);
			request.setAttribute("page", page);
			WebUtil.forward("/WEB-INF/views/board2/index.jsp", request, response);
		} 	
		else if ("mulPageBefore".equals(action)) {
			Long startPage = request.getParameter("startPage") == null?1L:Long.parseLong(request.getParameter("startPage"));
			Long newStartPage = startPage - pageShowNum;
			Long start = (newStartPage-1)*showNum;

			PageVo page = dao.paging(newStartPage, start, showNum, newStartPage, startPage-1, pageShowNum);
			List<BoardVo2> list = dao.findAll(page);

			request.setAttribute("list", list);
			request.setAttribute("page", page);
			WebUtil.forward("/WEB-INF/views/board2/index.jsp", request, response);
		}
		else if("movePage".equals(action)) { 			// 전체 게시판 조회
			String movePage = request.getParameter("movePage"); // 4
			Long curPage = 1L;

			if(!movePage.isBlank()) {
				curPage =Long.parseLong(movePage);  // 4
			}

			Long endPage =1L;
			Long startPage = 1L; // 
			Long start = (curPage-1)*showNum;

			if(curPage != 1) {
				startPage= curPage - ((curPage-1)%pageShowNum);
			}

			PageVo page = dao.paging(curPage, start, showNum, startPage, endPage, pageShowNum);

			if (page.getTotal() - startPage < pageShowNum)	endPage = page.getTotal();
			else  endPage = startPage + pageShowNum -1;

			page.setEndPage(endPage);

			List<BoardVo2> list = dao.findAll(page);
			request.setAttribute("list", list);
			request.setAttribute("page", page);
			WebUtil.forward("/WEB-INF/views/board2/index.jsp", request, response);
		}
		else { 		
			String msg = request.getParameter("msg") == null ? "":request.getParameter("msg");

			if(!"".equals(msg)) {
				request.setAttribute("msg", "답글이 있는 게시글은 삭제할 수 없습니다");
			}
			
			
			
			PageVo page = new PageVo();
			Long curPage = 1L;
			Long endPage =1L;
			Long startPage = 1L;
			Long start = (curPage-1)*showNum;
			page = dao.paging(curPage, start, showNum, startPage, endPage, pageShowNum);

			if (page.getTotal() - startPage < pageShowNum)	{
				endPage = page.getTotal();
			}else {
				endPage = (Long)pageShowNum;
			}
			page.setEndPage(endPage);

			List<BoardVo2> list = dao.findAll(page);
			request.setAttribute("list", list);
			request.setAttribute("page", page);
			WebUtil.forward("/WEB-INF/views/board2/index.jsp", request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}