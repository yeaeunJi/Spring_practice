package com.saltlux.mydictionary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saltlux.mydictionary.repository.BookmarkListRepository;
import com.saltlux.mydictionary.vo.BookmarkListVo;

@Service
public class BookmarkListService {

	@Autowired
	private BookmarkListRepository bmkListRepository;
	
	
	public boolean addBmkList(BookmarkListVo bmkListVo) {
		return bmkListRepository.insertBmkList(bmkListVo)==1;
	}
	
	public List<BookmarkListVo> getBmkList(long userNo) {
		return bmkListRepository.selectByUserNo(userNo);
	}

	public boolean existTitle(BookmarkListVo bmkListVo) {
		return bmkListRepository.selectByTitleAndUserNo(bmkListVo)==1;
	}

	public long getBasicBookmarkListNo(long userNo) {
		return bmkListRepository.getBasicBookmarkListNo(userNo);
	}

	public int updateWordCount(long userNo) {
		return bmkListRepository.updateWordCount(userNo);
	}


}
