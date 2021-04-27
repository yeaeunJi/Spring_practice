package com.saltlux.mydictionary.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.saltlux.mydictionary.vo.BookmarkVo;

@RequestMapping("/dictionary")
@Controller
public class DictionaryController {
	
	private String searchUrl = "https://openapi.naver.com/v1/search/encyc.json";
	private String naverSearchClientId = "";
	private String naverSearchClientSecret = "";
	
	@RequestMapping("/search")
	public String search(String keyword, Model model){		
		BookmarkVo board1 = new BookmarkVo("title1",202, "contents1", "link1", keyword, "thumbnail1", "description1");
		board1.setWordNo(1);
		board1.setRegDate("20210428");
		List<BookmarkVo> list = new ArrayList<>();
		list.add(board1);
		model.addAttribute("list", list);
		return "dictionary/index";
	}
	
	@RequestMapping("")
	public String index(String keyword, Model model){		
		List<BookmarkVo> list = new ArrayList<>();
		model.addAttribute("list", list);
		return "dictionary/index";
	}
}
