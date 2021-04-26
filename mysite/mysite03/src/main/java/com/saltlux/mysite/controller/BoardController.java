package com.saltlux.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.saltlux.mysite.service.BoardService;
import com.saltlux.mysite.vo.BoardVo;
import com.saltlux.mysite.vo.PageVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping("")
	public String list(@RequestParam(name = "keyword", defaultValue = "") String keyword,  Model model) {
		
		/* paging 처리*/
		PageVo pagevo = new PageVo(0L, 2L);
		
		List<BoardVo> lilst = boardService.findAll(keyword, pagevo);
		model.addAttribute("list", lilst);
		return "board/index";
	}
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
