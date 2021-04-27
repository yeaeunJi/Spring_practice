package com.saltlux.mydictionary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.saltlux.mydictionary.security.Auth;

@Controller
//	@Auth : AuthInterceptor에서 TYPE에 @Auth체크하는 메서드 잇음
public class MainController {
	@RequestMapping({"", "/main"})
	public String index() {
		return "main/index";
	}
}