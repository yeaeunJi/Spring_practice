package com.saltlux.mydictionary.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saltlux.mydictionary.repository.BookmarkRepository;
import com.saltlux.mydictionary.vo.BookmarkVo;
import com.saltlux.mydictionary.vo.PageVo;
import com.saltlux.mydictionary.vo.UserVo;

@Service
public class BookmarkService {

	@Autowired
	private BookmarkRepository bookmarkrepository;
	
	public void deleteAll() {
		bookmarkrepository.deleteAll();
	}
	
	public void delete(long wordNo) {
		bookmarkrepository.delete(wordNo);
	}
	
	public List<BookmarkVo> findAll(String keyword, PageVo pagevo, UserVo authUser) {
		return bookmarkrepository.findAll(keyword, pagevo, authUser);
	}

	public boolean insert(BookmarkVo bookmarkVo) {
		return bookmarkrepository.insert(bookmarkVo);
	}
	
	public boolean existBookmark(String link) {
		return bookmarkrepository.existBookmark(link);
	}

	public boolean deleteByLinkAndUserNo(BookmarkVo bookmarkVo) {
		return bookmarkrepository.deleteByLinkAndUserNo(bookmarkVo);
	}
	
}