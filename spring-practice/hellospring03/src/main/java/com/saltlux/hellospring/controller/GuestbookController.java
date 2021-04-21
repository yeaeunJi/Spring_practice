package com.saltlux.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author BIT_R39
 *
 * @RequestMapping
 * Type(Class) 단독 매핑
 * 
 */

@Controller
@RequestMapping("/guestbook/*")  //핸들러 이름으로 사용 가능

public class GuestbookController {
	
	@ResponseBody
	@RequestMapping() // url로 /guestbook/list 호출가능하지만 private method 등 문제가 있으므로 사용 권장x
	public String list() {
		return "GuestBookController:list()";
	}
	

	
}
