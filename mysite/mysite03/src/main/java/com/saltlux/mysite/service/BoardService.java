package com.saltlux.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saltlux.mysite.repository.BoardRepository;
import com.saltlux.mysite.vo.BoardVo;
import com.saltlux.mysite.vo.PageVo;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	public void deleteAll() {
		boardRepository.deleteAll();
	}

	public List<BoardVo> findAll(String keyword, PageVo pagevo) {
		return boardRepository.findAll(keyword, pagevo);
	}
	
}