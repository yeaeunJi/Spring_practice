package com.saltlux.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.saltlux.mysite.security.Auth;
import com.saltlux.mysite.security.AuthUser;
import com.saltlux.mysite.service.UserService;
import com.saltlux.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService ;

	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "user/join";
	}

	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(UserVo vo) {
		// service를 불러서 회원가입 비즈니스 로직을 처리
		// service에서 repository 의존(사용)

		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping("/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}

	// 근데 mysite03에서는 스프링 시큐리티 안쓸 것이므로 아래의 방법을 사용
	//	@Auth(role="ADMIN") // 이거를 보고 체크해주는 식으로 사용할 수 있음 : 내가 만든 어노테이션으로 체크
	// 인터셉터 : 서블릿이 controller를 호출 할때 할지 말지를 가운데서 결정해주는 역할(스프링에서 제공.)
	//             --> 서블릿에서 확인한 세션 정보로 인터셉터가 결정해줌
//	@RequestMapping(value="/login", method=RequestMethod.POST)
//	public String login(UserVo vo, HttpSession session) {
//		// 보안과 관련된 내용이므로 애플리케이션 밖에서 보안처리하도록 하는 것이 좋음
//		// ACL(Access Control Layer)
//		// 시스템 접근 개념에서의 보안 = 인증 과 권한(Authorization, 관리자와 일반 유저) --> 스프링 시큐리티에서 기능 ACL(Access Control Layer) 제공
//		// 로그인은 인증(Authentication) :  로그인 정보를 확인해보는 것 --> 이렇게 들어와있는 코드를 밖으로 빼는 것이 좋음
//		// 인증 : 기존 mysite02에서 if authUser == null ~ 로 만들었던 코드
//		// 권한 : if (vo.getRole() == "ADMIN") ~ 
//		//		userService.login(vo); // <--- 서비스에게는 이 기능을 안주는 것이 좋음
//		UserVo authUser = userService.getUser(vo);		
//		if(authUser == null) {
//			return "redirect:/user/login?result=fail";
//		}
//
//		/* 로그인 처리*/
//		session.setAttribute("authUser",  authUser);
//		return "redirect:/";
//	}

//	@RequestMapping("/logout")
//	public String logout(HttpSession session) {
//		session.removeAttribute("authUser");
//		session.invalidate();
//		return "redirect:/";
//	}

	//@Auth(role=Role.ADMIN) // 접근제어 . 클래스 단위로도 붙일 수 있음 @Auth(role="ADMIN")
	@Auth
	@RequestMapping(value="/update", method=RequestMethod.GET)
	//public String update(HttpSession session, Model model) {
	public String update(@AuthUser UserVo authUser, Model model) { 
		Long no = authUser.getNo();
		// argument resolve 
		UserVo userVo = userService.getUser(no);
		System.out.println("update: "+authUser);
		model.addAttribute("userVo", userVo);
		return "user/update";
	}
	
	@Auth
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(UserVo vo, HttpSession session) {
		// 접근 제어
		UserVo authUser = (UserVo) session.getAttribute("authUser");		
		if(authUser == null) {
			return "redirect:/user/login?result=fail";
		}

		Long no = authUser.getNo();
		vo.setNo(no);
		userService.update(vo);
		UserVo userVo = userService.getUser(no);
		session.setAttribute("authUser", userVo);		

		return "redirect:/";
	}

//	@ExceptionHandler(Exception.class) // 모든 예외를 여기로 받음  
//	public String handleException() {
//		// 로그 남기는 작업
//		return "error/exception";
//	} --> 이거 사용하면 여기서 예외가 처리되어 GlobalExceptionHandler가 사용되지 않게되므로 주석처리 
}
