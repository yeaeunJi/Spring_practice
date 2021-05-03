package com.saltlux.mydictionary.exception;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saltlux.mydictionary.dto.JsonResult;

@ControllerAdvice 
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class) // 모든 예외를 여기로 받음  

	public void handleException(HttpServletRequest request, 	HttpServletResponse response,	Exception e) throws Exception{

		String accept = request.getHeader("accept"); 
		if ( accept.matches(".* application/json.*")) { 
			response.setStatus(HttpServletResponse.SC_OK);
			JsonResult jsonResult = JsonResult.fail(e.toString());
			String jsonString = new ObjectMapper().writeValueAsString(jsonResult); // jsonResult 객체 --> Json String
			
			OutputStream os = response.getOutputStream();
			os.write(jsonString.getBytes("utf-8")); // 인코딩된 바이트로 적음
			os.close();
			
		}else {
			// 2. 사과 페이지로 이동
			request.setAttribute("error",  e.getLocalizedMessage());
			request.getRequestDispatcher("/WEB-INF/views/error/exception.jsp").forward(request, response);
		}
	} 

}
