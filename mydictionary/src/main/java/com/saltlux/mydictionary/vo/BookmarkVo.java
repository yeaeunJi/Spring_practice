package com.saltlux.mydictionary.vo;

public class BookmarkVo {
	private long wordNo;
	private long userNo;
	private String title;
	private String contents;
	private String regDate;
	private String link;
	private String keyword;
	private String thumbnail;
	private String description;

	public BookmarkVo() {}
	
	public BookmarkVo(String title, long userNo, String contents) {
		this.title = title;
		this.userNo = userNo;
		this.contents = contents;
	}
	
	public BookmarkVo(String title, long userNo, String contents, String link, String keyword, String thumbnail, String despcription) {
		this.title = title;
		this.userNo = userNo;
		this.contents = contents;
		this.link = link;
		this.keyword = keyword;
		this.thumbnail = thumbnail;
		this.description = despcription;
	}
	
	public long getWordNo() {
		return wordNo;
	}
	public void setWordNo(long wordNo) {
		this.wordNo = wordNo;
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
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "BookmarkVo [wordNo=" + wordNo + ", userNo=" + userNo + ", title=" + title + ", contents=" + contents
				+ ", regDate=" + regDate + ", link=" + link + ", keyword=" + keyword + ", thumbnail=" + thumbnail
				+ ", description=" + description + "]";
	}
}
