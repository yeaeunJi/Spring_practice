package com.saltlux.mydictionary.vo;

public class ReplyVo {
	private int rid;

	private int bid;

	private String content;

	private String reg_id;

	private String reg_dt;

	private String edit_db;

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReg_id() {
		return reg_id;
	}

	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}

	public String getReg_dt() {
		return reg_dt;
	}

	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}

	public String getEdit_db() {
		return edit_db;
	}

	public void setEdit_db(String edit_db) {
		this.edit_db = edit_db;
	}

	@Override
	public String toString() {
		return "ReplyVo [rid=" + rid + ", bid=" + bid + ", content=" + content + ", reg_id=" + reg_id + ", reg_dt="
				+ reg_dt + ", edit_db=" + edit_db + "]";
	}
}
