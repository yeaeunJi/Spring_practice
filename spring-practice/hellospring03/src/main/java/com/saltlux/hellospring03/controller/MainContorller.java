package com.saltlux.hellospring03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class MainContorller {
	@RequestMapping({"", "/main"})
	@ResponseBody
	public String main() {
		return "MainContorller:main()";
	}
}
