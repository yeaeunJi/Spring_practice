package com.saltlux.mysite.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saltlux.mysite.repository.GuestbookRepository;
import com.saltlux.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {

	
	@Autowired
	private GuestbookRepository guestbookRepository;
	
	public List<GuestbookVo> findAll() {
		return guestbookRepository.findAll();
	}

	public void insert(GuestbookVo vo) {
		 guestbookRepository.insert(vo);
	}

	public void delete(GuestbookVo vo) {
		 guestbookRepository.delete(vo);
	}

}
