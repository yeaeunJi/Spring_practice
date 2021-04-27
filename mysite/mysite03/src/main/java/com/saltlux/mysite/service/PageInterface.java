package com.saltlux.mysite.service;

import com.saltlux.mysite.vo.PageVo;

public interface PageInterface {
	/* 다음 페이지 정보 
	 * int step : 1이면 바로 다음 페이지로 이동, 3이면 3페이지 뒤로 이동
	 * */
	public PageVo nextPageVo(int step);
	
	/* 이전 페이지 정보
	 * int step : 1이면 바로 이전 페이지로 이동, 3이면 3페이지 이전으로 이동
	 * */
	public PageVo prevPageVo(int step);

}
