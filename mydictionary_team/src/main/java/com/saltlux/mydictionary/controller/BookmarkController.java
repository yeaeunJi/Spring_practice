package com.saltlux.mydictionary.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.saltlux.mydictionary.security.Auth;
import com.saltlux.mydictionary.security.AuthUser;
import com.saltlux.mydictionary.service.BookmarkListService;
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
	
	@Autowired
	private BookmarkListService bmkListService;

	
	@RequestMapping("")
	public String list(@AuthUser UserVo authUser,  @RequestParam(name="bmkListNo", required = false, defaultValue = "-1") long bmkListNo, Model model) {
		int total = bookmarkService.getCountByUserNo(authUser.getUserNo());
		PageVo page = new PageVo(total, 0, "");
		settingModelToPaging(page, bmkListNo, authUser,  model);
		return "bookmark/index";
	}
	
	
	@RequestMapping({"/searchBmkList", "/search"})
	public String searchBmkList(PageVo vo,   @RequestParam(name="bmkListNo", required = false, defaultValue = "-1") long bmkListNo, @AuthUser UserVo authUser, Model model) {
		PageVo page = null;
		Map<String, Object> map = new HashMap<>();
		map.put("userNo", authUser.getUserNo());
		map.put("keyword", vo.getKeyword());
		map.put("selectCondition", vo.getSelectCondition());
		map.put("bmkListNo", bmkListNo);
		
		int total = bookmarkService.getCountBySelectConditionAndBmkListNo(map);
		
		if(total != 0) {
			page = new PageVo(total, vo.getStartRow(), vo.getKeyword(), vo.getSelectCondition());
		}else {
			page = vo;
		}
		
		map.put("startRow", page.getStartRow());
		map.put("showNum", page.getShowNum());
		List<BookmarkVo> list = bookmarkService.findBySelectConditionAndBmkListNo(map);
		
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("bmkListNo", bmkListNo);
		return "bookmark/index";
	}
	
	@RequestMapping("delete")
	public String delete(BookmarkVo bookmarkVo, @AuthUser UserVo authUser, Model model) {
		bookmarkVo.setUserNo(authUser.getUserNo());
		bookmarkVo.setBmkListNo(bookmarkVo.getBmkListNo());
		
		bookmarkService.delete(bookmarkVo);
		bmkListService.updateWordCount(authUser.getUserNo());
		return "redirect:/bookmark?bmkListNo="+ bookmarkVo.getBmkListNo();
	}
	
	@RequestMapping("/onePageNext")
	public String onePageNext(PageVo page, String selectCondition, long bmkListNo,  @AuthUser UserVo authUser, Model model){	
		page.nextPage(0);
		settingModelToPaging(page, bmkListNo, authUser, model);
		return "bookmark/index";
	}
	
	@RequestMapping("/selectPage")
	public String onePageNext( int selectPage, PageVo page, long bmkListNo, @AuthUser UserVo authUser, Model model){	
		page.selectPage(selectPage, 0);
		settingModelToPaging(page, bmkListNo, authUser, model);
		return "bookmark/index";
	}
	
	@RequestMapping("/onePagePrev")
	public String onePagePrev(PageVo page, @AuthUser UserVo authUser, long bmkListNo,  Model model){	
		page.prevPage(0);
		settingModelToPaging(page, bmkListNo, authUser, model);
		return "bookmark/index";
	}
	
	
	private void settingModelToPaging(PageVo page, long bmkListNo, UserVo uservo,  Model model) {
		Map<String, Object> map = settingSearchCondition(uservo, page, bmkListNo);
		List<BookmarkVo> list = bookmarkService.findBySelectConditionAndBmkListNo(map);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("bmkListNo", bmkListNo);
	}
	
	private Map<String, Object> settingSearchCondition(UserVo authUser, PageVo page, long bmkListNo){
		Map<String, Object> map = new HashMap<>();
		map.put("userNo", authUser.getUserNo());
		map.put("keyword", page.getKeyword());
		map.put("selectCondition", page.getSelectCondition());
		map.put("startRow", page.getStartRow());
		map.put("showNum", page.getShowNum());
		map.put("bmkListNo", bmkListNo);
		return map;
	}
	

}
