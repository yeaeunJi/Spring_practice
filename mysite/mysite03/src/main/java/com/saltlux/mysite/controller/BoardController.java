package com.saltlux.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.saltlux.mysite.security.Auth;
import com.saltlux.mysite.security.AuthUser;
import com.saltlux.mysite.service.BoardService;
import com.saltlux.mysite.vo.BoardVo;
import com.saltlux.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
//	
//	@RequestMapping("")
//	public String list() {
//		boardService.findAll(null, null)
//		return "board/list";
//	}
//	
//	@Auth
//	@RequestMapping(value="/write",  method =RequestMethod.GET)
//	public String insert() {
//		return "board/list";
//	}
//	
//	@Auth
//	@RequestMapping(value="/write",  method =RequestMethod.POST)
//	public String insert(@AuthUser UserVo authUser, BoardVo vo) {
//		
//		return "board/list";
//	}
}
