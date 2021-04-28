package com.saltlux.mydictionary.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.saltlux.mydictionary.security.Auth;
import com.saltlux.mydictionary.service.DictionaryService;
import com.saltlux.mydictionary.vo.DictionaryVo;
import com.saltlux.mydictionary.vo.PageVo;

@RequestMapping("/dictionary")
@Controller
@Auth
public class DictionaryController {
	
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@RequestMapping("/search")
	public String search(String keyword, int display, int start, Model model){		
		String response = dictionaryService.search(keyword, display, start);
		Map<String, Object> responseMap = dictionaryService.convertJSONstringToMap(response);
		List<DictionaryVo> list = dictionaryService.convertMapToDictionaryVo(responseMap);

		PageVo pagevo = new PageVo();
		
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
