package com.saltlux.mydictionary.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.saltlux.mydictionary.vo.UserVo;

@Repository
public class UserRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public UserVo findByNo(long userNo ) {
		return sqlSession.selectOne("user.findByNo", userNo);
	}

	public UserVo findByIdAndPassword(UserVo vo ) {
		return sqlSession.selectOne("user.findByIdAndPassword", vo);
	}

	public boolean update(UserVo vo) {
		int count = sqlSession.insert("user.update", vo); // 다이나믹 쿼리
		return count==1;
	}

	public boolean insert(UserVo vo) {
		int count =  sqlSession.insert("user.insert",vo); // 삽입 후 no를 받아오기
		return count==1;
	}

	public String findById(String id) {
		return sqlSession.selectOne("user.findById", id);
	}
	
	public void deleteAll() {
		sqlSession.delete("user.deleteAll");
	}

	public Object getCount() {
		return sqlSession.selectOne("user.getCount");
	}
}
