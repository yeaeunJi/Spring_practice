package com.saltlux.mydictionary.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.saltlux.mydictionary.dto.JsonResult;
import com.saltlux.mydictionary.security.Auth;
import com.saltlux.mydictionary.security.AuthUser;
import com.saltlux.mydictionary.service.BookmarkListService;
import com.saltlux.mydictionary.service.BookmarkService;
import com.saltlux.mydictionary.vo.BookmarkListVo;
import com.saltlux.mydictionary.vo.BookmarkVo;
import com.saltlux.mydictionary.vo.UserVo;

@Controller("bookmarkApiController")
@RequestMapping("/api/bookmark")
@Auth
public class BookmarkController  {

	@Autowired
	private BookmarkService bookmarkService;
	
	@Autowired
	private BookmarkListService bmkListService;
		
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	@ResponseBody
	public JsonResult insert(BookmarkVo bookmarkVo, @AuthUser UserVo authUser) {
		bookmarkVo.setUserNo(authUser.getUserNo());
		bookmarkVo.setBmkListNo(authUser.getBasicBookmarkListNo());
		
		if(bookmarkService.existBookmark(bookmarkVo)) {
			return JsonResult.success(true);
		}
		
		boolean result = bookmarkService.insert(bookmarkVo);
		if(result) {
			bmkListService.updateWordCount(authUser.getUserNo());
		}
		return JsonResult.success(result);
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(BookmarkVo bookmarkVo, @AuthUser UserVo authUser) {
		bookmarkVo.setUserNo(authUser.getUserNo());
		boolean result = bookmarkService.delete(bookmarkVo);
		
		if(result) {
			bmkListService.updateWordCount(authUser.getUserNo());
		}
		return JsonResult.success(result);
	}
	
	@RequestMapping(value="/insertBmkList", method=RequestMethod.GET)
	@ResponseBody
	public JsonResult insertBmkList(BookmarkListVo bmkListVo, @AuthUser UserVo authUser) {
		bmkListVo.setUserNo(authUser.getUserNo());
		
		if(bmkListService.existTitle(bmkListVo)) {
			return JsonResult.fail("이미 존재하는 이름입니다. 다른 이름을 입력해주세요.");
		}
		boolean result = bmkListService.addBmkList(bmkListVo);
		if(result) {
			return JsonResult.success(bmkListVo);
		}
		return JsonResult.fail("새로운 즐겨찾기 카테고리 생성에 실패하였습니다. 다시 시도해주세요. ");
	}
	
	
	@RequestMapping(value="/getBmkLists", method=RequestMethod.GET)
	@ResponseBody
	public JsonResult getBmkLists(@AuthUser UserVo authUser) {
		List<BookmarkListVo> list = bmkListService.getBmkList(authUser.getUserNo());
		return JsonResult.success(list);
	}
	
	@RequestMapping(value="/moveToOther", method=RequestMethod.POST)
	@ResponseBody
	public JsonResult moveToOther(@RequestParam(name = "wordNoList[]") List<Long> wordNoList,
			@RequestParam(name="bmkListNo") long bmkListNo,@AuthUser UserVo authUser) {
		Map<String, Object> map = new HashMap<>();
		map.put("wordNoList", wordNoList);
		map.put("bmkListNo", bmkListNo);
		map.put("userNo", authUser.getUserNo());
		map.put("addNumber", 1);
		boolean result = bookmarkService.moveToOther(map);
		return JsonResult.success(result);
	}
}
