package com.saltlux.mydictionary.vo;

public class PageVo {
	private int totalPage = 1; 	// 전체 페이지 수
	private int curPage = 1;  	// 현재 페이지
	private int startRow = 0; // start row. mysql은 limit 사용시 0부터 사용 
	private int showNum = 20; // 한 페이지에 보여줄 게시물 수
	private int startPage = 1; // 시작 페이지 (1, 1+showNum, ... )
	private int endPage = 1; // 마지막 페이징 (showNum, showNum*2, ... )
	private int totalRow = 0; // 조회된 전체 행 수
	private int pageShowNum = 10; 
	private String keyword = "";
	private String selectCondition = "total";


	public PageVo() {}

	public PageVo(int curPage, int startRow, int showNum, int startPage, int endPage,int pageShowNum) {
		this(startRow, showNum);
		this.curPage = curPage;
		this.startPage = startPage;
		this.endPage = endPage;
		this.pageShowNum = pageShowNum;
		this.totalPage = (int) Math.ceil(totalRow/(double)showNum);
	}

	public PageVo(int startRow, int showNum) {
		this.startRow = startRow;
		this.showNum = showNum;
	}
	public PageVo(int totalRow, int startRow, String keyword, String selectCondition) {
		this(totalRow, startRow, keyword);
		this.selectCondition = selectCondition;
	}
	public PageVo(int totalRow, int startRow, String keyword) {
		this.startRow = startRow;
		this.keyword = keyword;
		this.totalRow = totalRow;
		this.totalPage = (int) Math.ceil(totalRow/(double)showNum)==0?1:(int) Math.ceil(totalRow/(double)showNum);
		this.startPage = 1;
		this.endPage = pageShowNum;
		
		if (totalPage - startPage < pageShowNum)	{
			this.endPage = totalPage;
		}
		else if(curPage % pageShowNum == 0) {
			this.endPage += pageShowNum;
		}
	}

	public void nextPage(int startRowCalcNum) {
		if (curPage % pageShowNum == 0 && totalPage != curPage) {
			startPage += pageShowNum;
		}

		if (totalPage - startPage < pageShowNum)	{
			endPage = totalPage;
		}
		else if(curPage % pageShowNum == 0) {
			endPage += pageShowNum;
		}

		++curPage;			
		startRow = (curPage - 1)*showNum+startRowCalcNum;
	}


	public void prevPage(int startRowCalcNum) {
		if(curPage == 1) {
			return;
		}

		if(startPage - pageShowNum > 0) {
			startPage -= pageShowNum;
		}

		if (curPage % pageShowNum == 1) {
			endPage =curPage-1;
		}

		curPage --;
		startRow = (curPage - 1)*showNum+startRowCalcNum;
	}
	
	
	public void selectPage(int selectPage, int startRowCalcNum) {
		curPage = selectPage;
		startRow = (curPage - 1)*showNum+startRowCalcNum;	

		if(curPage != 1) {
			startPage= curPage - ((curPage-1)%pageShowNum);
		}

		if (totalPage- startPage < pageShowNum) {	
			endPage = totalPage;
		}
		else  endPage = startPage + pageShowNum -1;

	}
	
	
	public String getSelectCondition() {
		return selectCondition;
	}

	public void setSelectCondition(String selectCondition) {
		this.selectCondition = selectCondition;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
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

	public int getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}

	public int getPageShowNum() {
		return pageShowNum;
	}

	public void setPageShowNum(int pageShowNum) {
		this.pageShowNum = pageShowNum;
	}

	@Override
	public String toString() {
		return "PageVo [totalPage=" + totalPage + ", curPage=" + curPage + ", startRow=" + startRow + ", showNum="
				+ showNum + ", startPage=" + startPage + ", endPage=" + endPage + ", totalRow=" + totalRow
				+ ", pageShowNum=" + pageShowNum + ", keyword=" + keyword + ", selectCondition=" + selectCondition
				+ "]";
	}

	


}
