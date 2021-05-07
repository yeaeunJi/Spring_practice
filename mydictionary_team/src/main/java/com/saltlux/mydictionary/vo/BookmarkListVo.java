package com.saltlux.mydictionary.vo;

public class BookmarkListVo {
	private long bmkListNo;
	private long userNo;
	private String title ;
	private int wordCount;
	
	public BookmarkListVo() {
		this.title = "기본";
		this.wordCount = 0;
	}
	
	
	public BookmarkListVo(long userNo, String title) {
		this.wordCount = 0;
		this.userNo = userNo;
		this.title = title;
	}

	public BookmarkListVo(long userNo) {
		this();
		this.userNo = userNo; 
	}

	public long getBmkListNo() {
		return bmkListNo;
	}
	public void setBmkListNo(long bmkListNo) {
		this.bmkListNo = bmkListNo;
	}
	public long getUserNo() {
		return userNo;
	}
	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getWordCount() {
		return wordCount;
	}
	public void setWordCount(int wordCount) {
		this.wordCount = wordCount;
	}
	
	@Override
	public String toString() {
		return "BookmarkListVo [bmkListNo=" + bmkListNo + ", userNo=" + userNo + ", title=" + title + ", wordCount=" + wordCount + "]";
	}
	
}
