package com.saltlux.mydictionary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saltlux.mydictionary.repository.OneToOneRepository;
import com.saltlux.mydictionary.vo.OneToOneVo;

@Service
public class OneToOneService {

	@Autowired
	private OneToOneRepository oneToOneRepository; 
	
	public List<OneToOneVo> findAll(int page, String name) {
		return oneToOneRepository.findAll(page, name);
		
	}
	
	public List<OneToOneVo> findAll(int page) {
		return oneToOneRepository.findAll(page);
		
	}
	
	public List<OneToOneVo> search(String searchOption, String keyword, int page) {
		return oneToOneRepository.search(searchOption, keyword, page);
		
	}

	public OneToOneVo findOne(String no) {

		return oneToOneRepository.findOne(no);
	}

	public boolean write(OneToOneVo vo) {
		return oneToOneRepository.write(vo);

	}

	public boolean update(String no, String title, String content) {
		return oneToOneRepository.update(no, title, content);
		
	}

	public int findAllCnt(String nickname) {
		
		return oneToOneRepository.findAllCnt(nickname);
	}

	public boolean insertReply(String no, String reply) {
		return oneToOneRepository.updateReply(no, reply);
	}
}