package com.saltlux.mydictionary.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.saltlux.mydictionary.dto.JsonResult;

@RequestMapping("/api/dictionary")
public class DictionaryController {
	
	private String searchUrl = "https://openapi.naver.com/v1/search/encyc.json";
	private String naverSearchClientId = "FwGUdeuCXwzcKsGaoB09";
	private String naverSearchClientSecret = "Jf1c8FRU9b";
	
	@RequestMapping("")
	@ResponseBody
	public JsonResult index(){		
		return JsonResult.success("===== 원하는 키워드를 입력하신 후 검색 버튼을 눌러주세요. =====");
	}
	
	
}
