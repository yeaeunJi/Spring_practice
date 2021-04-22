package com.saltlux.mysite.repository;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.saltlux.mysite.vo.GuestbookVo;

@Repository
public class GuestbookRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(GuestbookVo vo) {
		return sqlSession.insert("guestbook.insert", vo)==1;
	}

	public List<GuestbookVo> findAll(){
		List<GuestbookVo> list = sqlSession.selectList("guestbook.findAll");
		return list;
	}

	public boolean delete(GuestbookVo vo) {
		return sqlSession.delete("guestbook.delete", vo)==1;
	}

}
