package com.saltlux.mydictionary.vo;

public class UserVo {
	private long userNo;
	private String name;
	private String id;
	private String password;
	private String joinDate;
	
	public UserVo() {	}
	
	public UserVo(String name, String id, String password) {
		this.name = name;
		this.id = id;
		this.password = password;
	}

	public UserVo(String name, String id, String password, long userNo) {
		this(name, id, password);
		this.userNo = userNo;
	}

	public long getUserNo() {
		return userNo;
	}

	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	@Override
	public String toString() {
		return "UserVo [userNo=" + userNo + ", name=" + name + ", id=" + id + ", password=" + password + ", joinDate="
				+ joinDate + "]";
	}


}
