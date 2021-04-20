package com.saltlux.hellospring03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author BIT_R39
 * @RequestMapping
 * method(Hadnler) 단독 매핑
 * 
 * */
@Controller
public class BoardController {

	@ResponseBody
	@RequestMapping("/board/write")
	public String write() {
		return "BoardController:write()";
	}
	
	@ResponseBody
	@RequestMapping("/board/view")
	public String view(int no) { // 요청시 String으로 받는 것을 알아서 형변환해서 전달해줌. 형변환 불가한 데이터 시 404
		return "BoardController:view("+no+")";
	}
	
	@ResponseBody
	@RequestMapping("/board/view/{no}")
	public String view2(@RequestParam("no") int boardNo) { // 요청시 String으로 받는 것을 알아서 형변환해서 전달해줌. 형변환 불가한 데이터 시 404
		return "BoardController:view2"+boardNo+")";
	}
}
