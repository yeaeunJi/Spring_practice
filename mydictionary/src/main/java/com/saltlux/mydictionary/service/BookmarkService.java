package com.saltlux.mydictionary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saltlux.mydictionary.repository.BookmarkRepository;
import com.saltlux.mydictionary.vo.BookmarkVo;
import com.saltlux.mydictionary.vo.PageVo;

@Service
public class BookmarkService {

	@Autowired
	private BookmarkRepository repository;
	
	public void deleteAll() {
		repository.deleteAll();
	}

	public List<BookmarkVo> findAll(String keyword, PageVo pagevo) {
		return repository.findAll(keyword, pagevo);
	}
	
}