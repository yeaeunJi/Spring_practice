package com.saltlux.hellospring03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller // 컨트롤러 어노테이션 잊지 말기
public class HelloController {
	
	@RequestMapping("/hello") // 요청과 핸들러 매핑에 대한 설정 정보  : 이 메서드가 핸들러 역할
	// dispatcher 서블릿에서 스프링 컨테이너에서 받은 핸들러 매핑 정보(컨테이너가 어노테이션을 스캐닝해서 정보를 가지고 있음)
	// 를 바탕으로 요청(url)과 맞는 것을 호출 
	// 현재의 servletContextPath를 알고 있으므로 예전처럼 /hellospring03/ 표시  x
	// ==> 서블릿컨테이너의 기술(톰캣)의 servletContextPath 투입 방지
	 // 추후에 설명. 바로 body로 응답 보냄
	//@ResponseBody : messageConverter로 감
	public String hello() {
		return "/WEB-INF/views/hello.jsp";
	}
	
	@RequestMapping("/hello2")
	//  받을 파라미터의 이름이 같은 경우 @RequestParam 생략 가능
	public String hello2(String name) {
		System.out.println(name);
		return  "/WEB-INF/views/hello.jsp";
	}
	
	// 화면(JSP)로 파라미터를 보내는 방법1. 쓰지마세용
	@RequestMapping("/hello3")
	public ModelAndView hello3(String name) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/WEB-INF/views/hello.jsp");
		mav.addObject("name", "옌");		
		return  mav;
	}
	
	// 화면에 파라미터를 보내는 방법 2
	// ModelAndView보다 코드가 간결하여 좋음
	@RequestMapping("/hello4")
	public String hello4(String name, Model model) {
		model.addAttribute("name", name);
		return  "/WEB-INF/views/hello.jsp";
	}
	

	@RequestMapping("/hello5")
	@ResponseBody
	public String hello5(String name) {
		return  "Hello "+name;
	}
}
