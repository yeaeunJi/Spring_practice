package com.saltlux.mydictionary.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saltlux.mydictionary.repository.BookmarkListRepository;
import com.saltlux.mydictionary.repository.BookmarkRepository;
import com.saltlux.mydictionary.vo.BookmarkVo;

@Service
public class BookmarkService {

	@Autowired
	private BookmarkRepository bookmarkrepository;

	@Autowired
	private BookmarkListRepository bookmarkListRepository ;

	/*test용*/
	public void deleteAll() {
		bookmarkrepository.deleteAll();
	}

	@Transactional
	public boolean delete(BookmarkVo bookmarkVo) {
		return bookmarkrepository.delete(bookmarkVo);
		
	}

	public int getCountByUserNo(long userNo) {
		return bookmarkrepository.getCountByUserNo(userNo);
	}


	public int getCountBySelectConditionAndBmkListNo(Map<String, Object> map) {
		return bookmarkrepository.getCountBySelectConditionAndBmkListNo(map);
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


	@Transactional
	public boolean moveToOther(Map<String, Object> map) {
		boolean result1 = bookmarkrepository.updateBmkListNo(map);
		
		System.out.println("result 1 : "+result1);
		
		bookmarkListRepository.updateWordCount((long)map.get("userNo"));

		return result1;
	}

	public List<BookmarkVo> findBySelectConditionAndBmkListNo(Map<String, Object> map) {
		return bookmarkrepository.findBySelectConditionAndBmkListNo(map);
	}


}