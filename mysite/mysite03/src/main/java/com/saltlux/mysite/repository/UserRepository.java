package com.saltlux.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.saltlux.mysite.vo.UserVo;

@Repository
public class UserRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public UserVo findByNo(Long no ) {
		return sqlSession.selectOne("user.findByNo", no);
	}

	public UserVo findByEmailAndPassword(UserVo vo ) {
		return sqlSession.selectOne("user.findByEmailAndPassword", vo);
	}

	public boolean update(UserVo vo) {
		int count = sqlSession.insert("user.update", vo); // 다이나믹 쿼리
		return count==1;
	}

	public boolean insert(UserVo vo) {
		System.out.println(vo.getNo());
		int count =  sqlSession.insert("user.insert",vo); // 삽입 후 no를 받아오기
		System.out.println(vo.getNo());
		return count==1;
	}
}
