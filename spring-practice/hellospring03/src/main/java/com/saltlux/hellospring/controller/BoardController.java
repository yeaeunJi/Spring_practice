package com.saltlux.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author BIT_R39
 *
 * @RequestMapping
 * Method(Handler) 단독 매핑
 * 
 */

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
		//따라서 값을 요청받을 때 벨리데이션 필요(contorller에서 처리 불가. )
        //String으로 받아서 여기서 처리한 코드로 처리 가능하지만 스프링의 벨리데이터를 사용하면 처리 가능
        // 여기는 비즈니스 로직 호출 정도만 깔끔하게 작성하는 것이 좋음
       return "BoardController:view("+no+")";
	}


	@ResponseBody
	@RequestMapping("/board/view/{no}") // ﻿ /board/view?no=10을 /board/view/10으로 사용하는 방법
	public String view2(@RequestParam("no") int boardNo) {
		return "BoardController:view2"+boardNo+")";
	}

	
}
