package com.saltlux.mydictionary.vo;

public class OneToOneVo {
	
	private int no;
	private String title;
	private String content;
	private String writer;
	private String regdate;
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	
	@Override
	public String toString() {
		return "OneToOneVo [no=" + no + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", regdate=" + regdate + "]";
	}
	
}