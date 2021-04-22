package com.saltlux.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saltlux.mysite.repository.UserRepository;
import com.saltlux.mysite.vo.UserVo;

@Service
public class UserService { // 비즈니스(서비스) 관련 용어로 메서드명 만들기 

	@Autowired
	private UserRepository userRepository; 
	
	public void join(UserVo vo ) {
		userRepository.insert(vo)	;		// id 중복체크 후 가입완료 --- db에 입력
	}

	public UserVo getUser(UserVo vo) {
		return userRepository.findByEmailAndPassword(vo);
	}
	
	public UserVo getUser(Long no) {
		return userRepository.findByNo(no);
	}

	public boolean update(UserVo vo) {
		return userRepository.updateAll(vo);
	}
	
	
	
}
