package com.saltlux.mydictionary.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String list(@AuthUser UserVo authUser,  Model model) {
		int total = bookmarkService.getCountByUserNo(authUser.getUserNo());
		PageVo page = new PageVo(total, 0, "");
		List<BookmarkVo> list = bookmarkService.findAll(page, authUser);
//		System.out.println(page);
		model.addAttribute("page", page);
		model.addAttribute("list", list);
		return "bookmark/index";
	}
	
	@RequestMapping("/search")
	public String search(PageVo vo, @AuthUser UserVo authUser, Model model) {
		PageVo page = null;
		Map<String, Object> map = new HashMap<>();
		map.put("userNo", authUser.getUserNo());
		map.put("keyword", vo.getKeyword());
		int total = bookmarkService.getCountByUserNoAndKeyword(map);
		
		if(total != 0) {
			page = new PageVo(total, vo.getStartRow(), vo.getKeyword());
		}else {
			page = vo;
		}
		List<BookmarkVo> list = bookmarkService.findAllByKeyword(page, authUser);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "bookmark/index";
	}
	
	@RequestMapping("/onePageNext")
	public String onePageNext(PageVo page, @AuthUser UserVo authUser, Model model){	
		page.nextPage(0);
		List<BookmarkVo> list = bookmarkService.findAllByKeyword(page, authUser);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "bookmark/index";
	}
	
	@RequestMapping("/selectPage")
	public String onePageNext( int selectPage, PageVo page, @AuthUser UserVo authUser, Model model){	
		page.selectPage(selectPage, 0);
		List<BookmarkVo> list = bookmarkService.findAllByKeyword(page, authUser);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "bookmark/index";
	}
	
	@RequestMapping("/onePagePrev")
	public String onePagePrev(PageVo page, @AuthUser UserVo authUser, Model model){	
		page.prevPage(0);
		List<BookmarkVo> list = bookmarkService.findAllByKeyword(page, authUser);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "bookmark/index";
	}
	

}
