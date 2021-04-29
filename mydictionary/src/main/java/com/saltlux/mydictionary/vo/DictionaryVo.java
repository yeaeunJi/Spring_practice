package com.saltlux.mydictionary.vo;

public class DictionaryVo {
	private int total;
	private int start;
	private int display;
	private String title;
	private String link;
	private String description;
	private String thumbnail;
	private boolean bookmarkFlag;
	
	public DictionaryVo() {
	}

	public DictionaryVo(int total, int start, int display, String title, String link, String description, String thumbnail) {
		this(total, start, display);
		this.title = title;
		this.link = link;
		this.description = description;
		this.thumbnail = thumbnail;
	}
	
	public DictionaryVo(int total, int start, int display) {
		this.total = total;
		this.start = start;
		this.display = display;
	}

	
	
	public boolean isBookmarkFlag() {
		return bookmarkFlag;
	}

	public void setBookmarkFlag(boolean bookmarkFlag) {
		this.bookmarkFlag = bookmarkFlag;
	}

	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getDisplay() {
		return display;
	}
	public void setDisplay(int display) {
		this.display = display;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	@Override
	public String toString() {
		return "DictionaryVo [total=" + total + ", start=" + start + ", display=" + display + ", title=" + title
				+ ", link=" + link + ", description=" + description + ", thumbnail=" + thumbnail + ", bookmarkFlag="
				+ bookmarkFlag + "]";
	}


	
}
