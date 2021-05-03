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
	public String search(PageVo vo, @AuthUser UserVo authUser, Model model){	
		List<DictionaryVo> list = dictionaryService.search(vo);
		Map<String, Object> map = new HashMap<>();
		map.put("keyword", vo.getKeyword());
		map.put("userNo", authUser.getUserNo());
		List<String> compareList = bookmarkService.findLinkByUserNoAndKeyword(map);
		list = dictionaryService.markingBookmarkFlag(compareList, list);
	
		PageVo page = null;
	
		if(list.size() != 0) {
//			System.out.println("total Row : "+list.get(0).getTotal()+", list.size() : "+list.size());
			page = new PageVo(list.get(0).getTotal(), vo.getStartRow(), vo.getKeyword());
		}else {
			page = vo;
		}
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "dictionary/index";
	}
	
	/*
	 * Get : PageVo Parameter: curPage=${page.curPage}&startPage=${page.startPage}&totalRow=${page.totalRow}&endPage=${page.endPage}&totalPage=${page.totalPage}&keyword=${keyword}
	 * */
	@RequestMapping("/search/onePageNext")
	public String onePageNext( PageVo page, @AuthUser UserVo authUser, Model model){	
//		System.out.println("================= onePageNext");
		page.nextPage(1);
//		System.out.println("dictionary.search : "+ page);
		
		List<DictionaryVo> list = dictionaryService.search(page);
		Map<String, Object> map = new HashMap<>();
		map.put("keyword", page.getKeyword());
		map.put("userNo", authUser.getUserNo());
		List<String> compareList = bookmarkService.findLinkByUserNoAndKeyword(map);
		list = dictionaryService.markingBookmarkFlag(compareList, list);

		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "dictionary/index";
	}
	
	/*
	 * Get : PageVo Parameter: selectPage=${showNum}&curPage=${page.curPage}&startPage=${page.startPage}&totalRow=${page.totalRow}&endPage=${page.endPage}&totalPage=${page.totalPage}&keyword=${keyword}
	 * */	
	@RequestMapping("/search/selectPage")
	public String onePageNext(int selectPage, PageVo page, @AuthUser UserVo authUser, Model model){	
//		System.out.println("================= onePageNext");
		page.selectPage(selectPage, 1);
//		System.out.println("dictionary.search : "+ page);
		
		List<DictionaryVo> list = dictionaryService.search(page);
		Map<String, Object> map = new HashMap<>();
		map.put("keyword", page.getKeyword());
		map.put("userNo", authUser.getUserNo());
		List<String> compareList = bookmarkService.findLinkByUserNoAndKeyword(map);
		list = dictionaryService.markingBookmarkFlag(compareList, list);

		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "dictionary/index";
	}
	
	/*
	 * Get : PageVo Parameter: curPage=${page.curPage}&startPage=${page.startPage}&totalRow=${page.totalRow}&endPage=${page.endPage}&totalPage=${page.totalPage}&keyword=${keyword}
	 * */
	@RequestMapping("/search/onePagePrev")
	public String onePagePrev(PageVo page, @AuthUser UserVo authUser, Model model){	
//		System.out.println("================= onePagePrev");
		page.prevPage(1);
//		System.out.println("dictionary.search : "+ page);
		
		List<DictionaryVo> list = dictionaryService.search(page);
		Map<String, Object> map = new HashMap<>();
		map.put("keyword", page.getKeyword());
		map.put("userNo", authUser.getUserNo());
		List<String> compareList = bookmarkService.findLinkByUserNoAndKeyword(map);
		list = dictionaryService.markingBookmarkFlag(compareList, list);
		
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "dictionary/index";
	}
	
	@RequestMapping("")
	public String index(@AuthUser UserVo authUser, Model model){
		PageVo page = new PageVo();
		model.addAttribute("page", page);
		return "dictionary/index";
	}
}
