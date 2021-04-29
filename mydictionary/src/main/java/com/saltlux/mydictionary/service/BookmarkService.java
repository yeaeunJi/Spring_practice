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

	public boolean delete(BookmarkVo bookmarkVo) {
		return bookmarkrepository.delete(bookmarkVo);
	}

	public int getCountByUserNo(long userNo) {
		return bookmarkrepository.getCountByUserNo(userNo);
	}

	public List<BookmarkVo> findAll(PageVo pagevo, UserVo authUser) {
		return bookmarkrepository.findAll(pagevo, authUser);
	}
	
	public int getCountByUserNoAndKeyword(Map<String, Object> map) {
		return bookmarkrepository.getCountByUserNoAndKeyword(map);
	}

	public List<BookmarkVo> findAllByKeyword(PageVo pagevo, UserVo authUser) {
		return bookmarkrepository.findAllByKeyword(pagevo, authUser);
	}

	public List<String> findLinkByUserNoAndKeyword(String keyword, UserVo authUser) {
		return bookmarkrepository.findLinkByUserNoAndKeyword(keyword,  authUser);
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