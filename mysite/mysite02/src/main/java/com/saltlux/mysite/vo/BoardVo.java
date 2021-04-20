package com.saltlux.mysite.vo;

import java.util.HashMap;
import java.util.Map;

public class BoardVo {
	private String _id = "";
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}

	private Long no;
	private String title;
	private String writer;
	private Long  userNo;
	private String contents;
	private Long count;
	private String regDate;
	private Long gNo;
	private Long oNo;
	private Long depth;
	private char delFlag = 'F'; // 삭제 여부
	
	public BoardVo() {
		
	}
	
	public BoardVo(Long no, String title, String contents) {
		this(no, title, null, contents, -1L, 0L, 0L, 0L);
	}
	
	public BoardVo(String title, String writer, String contents, Long userNo, Long gNo, Long oNo, Long depth) {
		this(null, title, writer, contents, userNo, gNo, oNo, depth);
	}
	public BoardVo(Long no, String title, String writer, String contents,  Long userNo, Long gNo, 	Long oNo, Long depth) {
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.contents = contents;
		this.userNo = userNo;
		this.gNo = gNo;
		this.oNo = oNo;
		this.depth = depth;
	}
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
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
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public Long getgNo() {
		return gNo;
	}
	public void setgNo(Long gNo) {
		this.gNo = gNo;
	}
	public Long getoNo() {
		return oNo;
	}
	public void setoNo(Long oNo) {
		this.oNo = oNo;
	}
	public Long getDepth() {
		return depth;
	}
	public void setDepth(Long depth) {
		this.depth = depth;
	}
	public char getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(char delFlag) {
		this.delFlag = delFlag;
	}
	
	

	@Override
	public String toString() {
		return "BoardVo [_id=" + _id + ", no=" + no + ", title=" + title + ", writer=" + writer + ", userNo=" + userNo
				+ ", contents=" + contents + ", count=" + count + ", regDate=" + regDate + ", gNo=" + gNo + ", oNo="
				+ oNo + ", depth=" + depth + ", delFlag=" + delFlag + "]";
	}
	public Map<String, Object> voToMap(){
		Object[] values = {no, title, userNo, contents, count, regDate, gNo, oNo, depth};
		String[] lists = {"no", "title", "userNo", "contents", "count", "regDtae", "gNo", "oNo", "depth"};
		Map<String, Object> map = new HashMap<String, Object>();
		
		for(int i =0; i<lists.length; i++) {
			map.put(lists[i], values[i]);
		}
		return map;
	}
	
	public BoardVo mapToVo(HashMap<String, Object> map){
		BoardVo vo = new BoardVo();
		return vo;
	}
	
//	public Map<String, Object> voToMap(){
//	Map<String, Object> map = new HashMap<String, Object>();
//	int length = this.getClass().getDeclaredFields().length;
//
//	Field[] fields = this.getClass().getDeclaredFields() ;
//	for(int i=1; i < length; i++){
//		try {
//			fields[i].setAccessible(true);
//			String name = fields[i].getName();
//			Object obj =  fields[i].get(name);
//			map.put(name, obj);
//		} catch (IllegalArgumentException | IllegalAccessException e) {
//			System.out.println("BoardVo :: voToMap error : "+e.getMessage());
//		}		
//	}
//	return map;
//}
	
	
}
