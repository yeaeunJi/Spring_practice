package com.saltlux.mydictionary.vo;

public class PageVo {
	private Long total = 1L; 	// 전체 페이지 수
	private Long curPage = 1L;  	// 현재 페이지
	private Long start = 1L; // start row
	private Long showNum = 2L; // 한 페이지에 보여줄 게시물 수
	private Long startPage = 1L; // 시작 페이지 (1, 1+showNum, ... )
	private Long endPage = 1L; // 마지막 페이징 (showNum, showNum*2, ... )
	private Long totalCount = 0L; // 조회된 전체 행 수
	private Long  pageShowNum = 3L; 
	
	
	public PageVo() {}
	public PageVo(Long curPage, Long start, Long showNum, Long startPage, Long endPage,	Long pageShowNum) {
		this.curPage = curPage;
		this.start = start;
		this.showNum = showNum;
		this.startPage = startPage;
		this.endPage = endPage;
		this.pageShowNum = pageShowNum;
	}
	
	public PageVo(Long start, Long showNum) {
		this.start = start;
		this.showNum = showNum;
	}
	public Long getPageShowNum() {
		return pageShowNum;
	}
	public void setPageShowNum(Long pageShowNum) {
		this.pageShowNum = pageShowNum;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Long getCurPage() {
		return curPage;
	}
	public void setCurPage(Long curPage) {
		this.curPage = curPage;
	}
	public Long getStart() {
		return start;
	}
	public void setStart(Long start) {
		this.start = start;
	}
	public Long getShowNum() {
		return showNum;
	}
	public void setShowNum(Long showNum) {
		this.showNum = showNum;
	}
	public Long getStartPage() {
		return startPage;
	}
	public void setStartPage(Long startPage) {
		this.startPage = startPage;
	}
	public Long getEndPage() {
		return endPage;
	}
	public void setEndPage(Long endPage) {
		this.endPage = endPage;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	@Override
	public String toString() {
		return "PageVo [total=" + total + ", curPage=" + curPage + ", start=" + start + ", showNum=" + showNum
				+ ", startPage=" + startPage + ", endPage=" + endPage + ", totalCount=" + totalCount + ", pageShowNum="
				+ pageShowNum + "]";
	}

	
	
	
	
}
