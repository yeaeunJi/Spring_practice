package com.saltlux.mysite.vo;

import java.util.HashMap;
import java.util.Map;

public class BoardVo2 {
	private String no;
	private String title;
	private String writer;
	private String contents;
	private String regDate;
	private int  userNo = -1;
	private int gNo = 0;
	private int oNo = 0;
	private int depth = 0;
	
	public BoardVo2() {
		
	}
	
	public BoardVo2(String no, String title, String contents) {
		this(no, title, null, contents, -1, 0, 0, 0);
	}
	
	public BoardVo2(String title, String writer, String contents, int userNo, int gNo, int oNo, int depth) {
		this(null, title, writer, contents, userNo, gNo, oNo, depth);
	}
	
	public BoardVo2(String no, String title, String writer, String contents,  int userNo, int gNo, 	int oNo, int depth) {
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.contents = contents;
		this.userNo = userNo;
		this.gNo = gNo;
		this.oNo = oNo;
		this.depth = depth;
	}

	public String getNo() {
		return no;
	}
	
	public void setNo(String no) {
		this.no = no;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getWriter() {
		return writer;
	}
	
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public String getContents() {
		return contents;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public String getRegDate() {
		return regDate;
	}
	
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	public int getUserNo() {
		return userNo;
	}
	
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	
	public int getgNo() {
		return gNo;
	}
	
	public void setgNo(int gNo) {
		this.gNo = gNo;
	}
	
	public int getoNo() {
		return oNo;
	}
	
	public void setoNo(int oNo) {
		this.oNo = oNo;
	}
	
	public int getDepth() {
		return depth;
	}
	
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	@Override
	public String toString() {
		return "BoardVo2 [no=" + no + ", title=" + title + ", writer=" + writer + ", contents=" + contents
				+ ", regDate=" + regDate + ", userNo=" + userNo + ", gNo=" + gNo + ", oNo=" + oNo
				+ ", depth=" + depth + "]";
	}

	public Map<String, Object> voToMap(){
		Object[] values = { title, writer, userNo, contents, regDate, gNo, oNo, depth};
		String[] lists = {"title", "writer", "userNo", "contents", "regDtae", "gNo", "oNo", "depth"};
		Map<String, Object> map = new HashMap<String, Object>();
		
		for(int i =0; i<lists.length; i++) {
			map.put(lists[i], values[i]);
		}
		return map;
	}
	
}
