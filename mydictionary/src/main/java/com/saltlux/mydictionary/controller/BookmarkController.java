package com.saltlux.mydictionary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.saltlux.mydictionary.service.BookmarkService;
import com.saltlux.mydictionary.service.PageInterface;
import com.saltlux.mydictionary.vo.BookmarkVo;
import com.saltlux.mydictionary.vo.PageVo;

@Controller
@RequestMapping("/bookmark")
public class BookmarkController  {

	@Autowired
	private BookmarkService service;
	
	@RequestMapping("")
	public String list(@RequestParam(name = "keyword", defaultValue = "") String keyword,  Model model) {
		
		/* paging 처리*/
		
		PageVo pagevo = new PageVo(0L, 2L);
		
		List<BookmarkVo> lilst = service.findAll(keyword, pagevo);
		model.addAttribute("list", lilst);
		return "bookmark/index";
	}
//	
//	@Auth
//	@RequestMapping(value="/write",  method =RequestMethod.GET)
//	public String insert() {
//		return "bookmark/list";
//	}
//	
//	@Auth
//	@RequestMapping(value="/write",  method =RequestMethod.POST)
//	public String insert(@AuthUser UserVo authUser, BoardVo vo) {
//		
//		return "bookmark/list";
//	}
}
