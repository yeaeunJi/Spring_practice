package com.saltlux.mydictionary.vo;

public class PageVo {
	private int total = 1; 	// 전체 페이지 수
	private int curPage = 1;  	// 현재 페이지
	private int start = 1; // start row
	private int showNum = 10; // 한 페이지에 보여줄 게시물 수
	private int startPage = 1; // 시작 페이지 (1, 1+showNum, ... )
	private int endPage = 1; // 마지막 페이징 (showNum, showNum*2, ... )
	private int totalCount = 0; // 조회된 전체 행 수
	private int  pageShowNum = 5; 
	
	
	public PageVo() {}
	
	public PageVo(int curPage, int start, int showNum, int startPage, int endPage,	int pageShowNum) {
		this(start, showNum);
		this.curPage = curPage;
		this.startPage = startPage;
		this.endPage = endPage;
		this.pageShowNum = pageShowNum;
	}
	
	public PageVo(int start, int showNum) {
		this.start = start;
		this.showNum = showNum;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getShowNum() {
		return showNum;
	}

	public void setShowNum(int showNum) {
		this.showNum = showNum;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageShowNum() {
		return pageShowNum;
	}

	public void setPageShowNum(int pageShowNum) {
		this.pageShowNum = pageShowNum;
	}

	@Override
	public String toString() {
		return "PageVo [total=" + total + ", curPage=" + curPage + ", start=" + start + ", showNum=" + showNum
				+ ", startPage=" + startPage + ", endPage=" + endPage + ", totalCount=" + totalCount + ", pageShowNum="
				+ pageShowNum + "]";
	}
	
}
