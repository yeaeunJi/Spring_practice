package com.saltlux.mydictionary.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.saltlux.mydictionary.dto.JsonResult;
import com.saltlux.mydictionary.service.DictionaryService;

@RequestMapping("/api/dictionary")
@Controller("dictionaryApiController")
public class DictionaryController {

	@Autowired
	private DictionaryService dictionaryService;

	@RequestMapping("")
	@ResponseBody
	public JsonResult index(String keyword, int display, int start){
		String responseBody = dictionaryService.getResponseBody(keyword, display, start);
		return JsonResult.success(responseBody);
	}
	
	

}
