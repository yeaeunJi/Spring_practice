package com.saltlux.mydictionary.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.saltlux.mydictionary.dto.JsonResult;
import com.saltlux.mydictionary.service.UserService;

@Controller("userApiController") 
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/existid")
	@ResponseBody
	public JsonResult existid(String id){
		boolean result = userService.existUser(id);
		return JsonResult.success(result);
	}
}
