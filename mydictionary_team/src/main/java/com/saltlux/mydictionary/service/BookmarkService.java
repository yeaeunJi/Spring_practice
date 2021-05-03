package com.saltlux.mydictionary.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saltlux.mydictionary.repository.BookmarkRepository;
import com.saltlux.mydictionary.vo.BookmarkVo;

@Service
public class BookmarkService {

	@Autowired
	private BookmarkRepository bookmarkrepository;

	public void deleteAll() {
		bookmarkrepository.deleteAll();
	}

	public boolean delete(BookmarkVo bookmarkVo) {
		return bookmarkrepository.delete(bookmarkVo);
	}

	public int getCountByUserNo(long userNo) {
		return bookmarkrepository.getCountByUserNo(userNo);
	}

	public List<BookmarkVo> findAll(Map<String, Object> map) {

		return bookmarkrepository.findAll(map);
	}
	
	public int getCountByUserNoAndKeyword(Map<String, Object> map) {
		return bookmarkrepository.getCountByUserNoAndKeyword(map);
	}
	
	public int getCountBySelectCondition(Map<String, Object> map) {
		return bookmarkrepository.getCountBySelectCondition(map);
	}
	
	public List<BookmarkVo> findBySelectCondition(Map<String, Object> map){
		return bookmarkrepository.findBySelectCondition(map);
	}

	public List<String> findLinkByUserNoAndKeyword(Map<String, Object> map) {
		return bookmarkrepository.findLinkByUserNoAndKeyword(map);
	}

	public boolean insert(BookmarkVo bookmarkVo) {
		return bookmarkrepository.insert(bookmarkVo);
	}

	public boolean existBookmark(BookmarkVo bookmarkVo) {
		return bookmarkrepository.existBookmark(bookmarkVo);
	}

	public boolean deleteByLinkAndUserNo(BookmarkVo bookmarkVo) {
		return bookmarkrepository.deleteByLinkAndUserNo(bookmarkVo);
	}

}