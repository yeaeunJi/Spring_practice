package com.saltlux.mysite.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //Contorller 메서드에 대한  AOP advice란 의미
// spring-servlet.xml에 추가
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class) // 모든 예외를 여기로 받음  
	
	public void handleException(HttpServletRequest request, 
												HttpServletResponse response, 
												Exception e) throws Exception{
		// 1. 로깅 작업
		System.out.println(e);
		// 2. 사과 페이지로 이동
//		return "error/exception"; ----> 서블릿에서 호출해서 사용하는 것이 아니므로 이동x
		// 직접 보내야함
		request.setAttribute("error", e.toString());
		request.getRequestDispatcher("/WEB-INF/views/error/exception.jsp").forward(request, response);;
	} 
	
	// 특정 Exception이 문제가 되어 따로 모니터링하거나 파일로 남기고 싶은 경우 그것만 처리하는 핸들러 만들면 됨
}
