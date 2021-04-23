package com.saltlux.mysite.exception;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saltlux.mysite.dto.JsonResult;

@ControllerAdvice //Contorller 메서드에 대한  AOP advice란 의미
// spring-servlet.xml에 추가
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class) // 모든 예외를 여기로 받음  

	public void handleException(HttpServletRequest request, 	HttpServletResponse response,	Exception e) throws Exception{
		// 1. 로깅 작업
		System.out.println(e);

		// + 요청 구분 : 화면 vs api
		String accept = request.getHeader("accept"); // 클라이언트 브라우저가 어떤 것을 받을 수 있는지를 보내줌
		// web 요청에서는 json이 빠져있을 것. 
		// api는 json이 가능   accept 내용은 브라우저마다 다름
		if ( accept.matches(".* application/json.*")) { // 모든 문자 + application/json + 모든 문자
			/* JSON 응답 :: ajax 요청 */
			response.setStatus(HttpServletResponse.SC_OK); // 통신은 정상적임
			JsonResult jsonResult = JsonResult.fail(e.toString());
			String jsonString = new ObjectMapper().writeValueAsString(jsonResult); // jsonResult 객체 --> Json String
			
			OutputStream os = response.getOutputStream();
			os.write(jsonString.getBytes("utf-8")); // 인코딩된 바이트로 적음
			os.close();
			
		}else {
			// 2. 사과 페이지로 이동
			//		return "error/exception"; ----> 서블릿에서 호출해서 사용하는 것이 아니므로 이동x
			// 직접 보내야함
			request.setAttribute("error", e.toString());
			request.getRequestDispatcher("/WEB-INF/views/error/exception.jsp").forward(request, response);
		}
	} 

	// 특정 Exception이 문제가 되어 따로 모니터링하거나 파일로 남기고 싶은 경우 그것만 처리하는 핸들러 만들면 됨

}
