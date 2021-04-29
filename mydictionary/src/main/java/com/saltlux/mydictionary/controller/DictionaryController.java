package com.saltlux.mydictionary.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.saltlux.mydictionary.security.Auth;
import com.saltlux.mydictionary.security.AuthUser;
import com.saltlux.mydictionary.service.BookmarkService;
import com.saltlux.mydictionary.service.DictionaryService;
import com.saltlux.mydictionary.vo.DictionaryVo;
import com.saltlux.mydictionary.vo.PageVo;
import com.saltlux.mydictionary.vo.UserVo;

@RequestMapping("/dictionary")
@Controller
@Auth
public class DictionaryController {
	
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@Autowired
	private BookmarkService bookmarkService;
	
	@RequestMapping("/search")
	public String search(String keyword, int display, int start, @AuthUser UserVo authUser, Model model){		
		List<DictionaryVo> list = dictionaryService.search(keyword, display, start);
		List<String> compareList = bookmarkService.findLinkByUserNoAndKeyword(keyword, authUser);
		list = dictionaryService.markingBookmarkFlag(compareList, list);
		
		PageVo pagevo = new PageVo(1, 5);
		
		model.addAttribute("keyword", keyword);
		model.addAttribute("list", list);
		model.addAttribute("page", pagevo);
		return "dictionary/index";
	}
	
	@RequestMapping("")
	public String index(Model model){
		PageVo pagevo = new PageVo();
		
		List<DictionaryVo> list = new ArrayList<>();
		model.addAttribute("page", pagevo);
		model.addAttribute("list", list);
		model.addAttribute("keyword", "");
		return "dictionary/index";
	}
}
