package com.saltlux.hellospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author BIT_R39
 * @RequestMapping
 * Method(Handler) + Type(class) 매핑
 * **/

@Controller
@RequestMapping("/user") 
public class UserController {	
	@RequestMapping(value="/join", method=RequestMethod.POST) // post일때
	//public String join(String name, String email, String password) {
	public String join(UserVo vo) { // 객체로 묶어서 받을 수 있음. 단 조건이 필요(vo가 setter를 가지고 있어야함)
		System.out.println(vo);
		
		return "redirect:/"; // 리다이렉트
	}
	

	@RequestMapping(value="/join", method=RequestMethod.GET) // get방식일때에만 이 메서드 호출 
	public String joinform() {
		return "/WEB-INF/views/join.jsp";
	}

	@ResponseBody
	@RequestMapping("/update") 
	//@RequestParam을 달면 해당 파라미터가 없으면 400 bad request 오류 발생
	public String update(@RequestParam("n") String name) { 
		System.out.println(name);
		return "UserController:update()"; // 리다이렉트
	}
	
	@ResponseBody
	@RequestMapping("/update2") 
	//400 에러 방지하는 방법1 : @RequestParam에 required=false(필수값이 아님)을 명시하면 됨
	// 왠만하면 이름 맞춰서 사용
	// 기본값이 true임
	public String update2(@RequestParam(value="n", required=false) String name) { 
		System.out.println(name);
		return "UserController:update2()"; // 리다이렉트
	}
	
	@ResponseBody
	@RequestMapping("/update3") 
	//400 에러 방지하는 방법2 : @RequestParam에 디폴트값 설정(추천)
	// 기본값이 true임
	public String update3(@RequestParam(value="n", required=true, defaultValue="") String name,
								  @RequestParam(value="a", required=true, defaultValue="0") int age)  { 
		System.out.println("===="+name+"==== : "+age);
		return "UserController:update3()"; // 리다이렉트
	}
}
