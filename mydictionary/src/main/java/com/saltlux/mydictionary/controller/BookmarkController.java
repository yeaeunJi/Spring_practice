package com.saltlux.mydictionary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.saltlux.mydictionary.security.Auth;
import com.saltlux.mydictionary.security.AuthUser;
import com.saltlux.mydictionary.service.BookmarkService;
import com.saltlux.mydictionary.vo.BookmarkVo;
import com.saltlux.mydictionary.vo.PageVo;
import com.saltlux.mydictionary.vo.UserVo;

@Controller
@RequestMapping("/bookmark")
@Auth
public class BookmarkController  {

	@Autowired
	private BookmarkService bookmarkService;
	
	@RequestMapping("")
	public String list(@AuthUser UserVo authUser, Model model) {
		
		/* paging 처리*/
		
		PageVo pagevo = new PageVo(0,10);
		
		List<BookmarkVo> list = bookmarkService.findAll(pagevo, authUser);
		model.addAttribute("list", list);
		return "bookmark/index";
	}
	
	@RequestMapping("search")
	public String search(@RequestParam(name = "keyword", defaultValue = "") String keyword, @AuthUser UserVo authUser, Model model) {
		
		/* paging 처리*/
		
		PageVo pagevo = new PageVo(0,10);
		
		List<BookmarkVo> list = bookmarkService.findAllByKeyword(keyword, pagevo, authUser);
		model.addAttribute("list", list);
		return "bookmark/index";
	}
	
//	@RequestMapping(value="/insert",  method = RequestMethod.POST)
//	public String insert(@AuthUser UserVo authUser, BookmarkVo vo, Model model) {
//		bookmarkService.insert(authUser.getUserNo(), vo);
//		PageVo pagevo = new PageVo(0L, 2L);
//		
//		List<BookmarkVo> list = bookmarkService.findAll(keyword, pagevo, authUser);
//		model.addAttribute("list", list);
//		return "bookmark/list";
//	
//	}
//	
//	@RequestMapping(value="/delete")
//	public String delete(long wordNo, @RequestParam(name = "keyword", defaultValue = "") String keyword, @AuthUser UserVo authUser, Model model) {
//		bookmarkService.delete(wordNo);
//		
//		/* paging 처리*/
//		PageVo pagevo = new PageVo(0, 2);		
//		List<BookmarkVo> list = bookmarkService.findAll(keyword, pagevo, authUser);
//		model.addAttribute("list", list);
//		return "bookmark/index";
//	}



}
