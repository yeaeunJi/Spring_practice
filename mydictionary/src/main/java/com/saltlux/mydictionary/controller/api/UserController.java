package com.saltlux.mydictionary.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.saltlux.mydictionary.dto.JsonResult;
import com.saltlux.mydictionary.service.UserService;

@Controller("userApiController") // 어노테이션으로 빈 설정 시 기본적으로 클래스명으로 id를 설정.
// 다른 패키지에 있는 UserController와 이름 충돌이 일어날 때는 위와 같이 이름을 바꿔줄 수 있음
@RequestMapping("/api/user")
//@RestController("userApiController") // rest 전용 컨트롤러란 의미로 @ResponseBody 생략 가능
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
