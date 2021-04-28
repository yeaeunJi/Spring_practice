package com.saltlux.mydictionary.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.saltlux.mydictionary.dto.JsonResult;
import com.saltlux.mydictionary.security.Auth;
import com.saltlux.mydictionary.security.AuthUser;
import com.saltlux.mydictionary.service.BookmarkService;
import com.saltlux.mydictionary.vo.BookmarkVo;
import com.saltlux.mydictionary.vo.UserVo;

@Controller("bookmarkApiController")
@RequestMapping("/api/bookmark")
@Auth
public class BookmarkController  {

	@Autowired
	private BookmarkService bookmarkService;
		
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	public JsonResult insert(BookmarkVo bookmarkVo, @AuthUser UserVo authUser) {
		bookmarkVo.setUserNo(authUser.getUserNo());
		if(bookmarkService.existBookmark(bookmarkVo.getLink())) {
			return JsonResult.success(true);
		}
		boolean result = bookmarkService.insert(bookmarkVo);
		return JsonResult.success(result);
	}

	@RequestMapping(value="/update", method=RequestMethod.DELETE)
	@ResponseBody
	public JsonResult delete(BookmarkVo bookmarkVo, @AuthUser UserVo authUser) {
		bookmarkVo.setUserNo(authUser.getUserNo());
		boolean result = bookmarkService.deleteByLinkAndUserNo(bookmarkVo);
		return JsonResult.success(result);
	}

}
