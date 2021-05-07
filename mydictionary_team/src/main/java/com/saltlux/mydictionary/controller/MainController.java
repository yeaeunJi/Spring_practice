package com.saltlux.mydictionary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@RequestMapping({"", "/main"})
	public String index() {
		return "main/index";
	}
	
	@RequestMapping({"/main/introduce", "/introduce"})
	public String introduce() {
		return "main/introduce";
	}
}