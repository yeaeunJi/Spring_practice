package com.saltlux.mydictionary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saltlux.mydictionary.repository.UserRepository;
import com.saltlux.mydictionary.vo.UserVo;

@Service
public class UserService { 

	@Autowired
	private UserRepository userRepository; 
	
	public void join(UserVo vo ) {
		userRepository.insert(vo)	;		
	}

	public UserVo getUser(UserVo vo) {
		return userRepository.findByIdAndPassword(vo);
	}
	
	public UserVo getUser(long userNo) {
		return userRepository.findByNo(userNo);
	}

	public boolean update(UserVo vo) {
		return userRepository.update(vo);
	}

	public boolean existUser(String id) {
		return userRepository.findById(id)!=null;
	}
	
	
}
