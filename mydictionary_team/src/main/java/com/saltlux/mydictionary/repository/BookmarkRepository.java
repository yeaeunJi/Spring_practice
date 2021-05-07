package com.saltlux.mydictionary.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.saltlux.mydictionary.vo.BookmarkVo;

@Repository
public class BookmarkRepository {
	@Autowired
	private SqlSession sqlSession;


	public boolean insert(BookmarkVo vo) {
		return sqlSession.insert("bookmark.insert", vo)==1;
	}

	public int getCountByUserNo(long userNo) {
		return sqlSession.selectOne("bookmark.getCountByUserNo", userNo);
	}

	public boolean existBookmark(BookmarkVo bookmarkVo) {
		return (int)sqlSession.selectOne("bookmark.findByLink", bookmarkVo)==1;
	}

	public List<String> findLinkByUserNoAndKeyword(Map<String, Object> map) {
		return sqlSession.selectList("bookmark.findLinkByUserNoAndKeyword", map);
	}

	public List<BookmarkVo> findBySelectConditionAndBmkListNo(Map<String, Object> map) {
		return sqlSession.selectList("bookmark.findBySelectConditionAndBmkListNo", map);
	}

	public int getCountBySelectConditionAndBmkListNo(Map<String, Object> map) {
		return sqlSession.selectOne("bookmark.getCountBySelectConditionAndBmkListNo", map);
	}

	public boolean updateBmkListNo(Map<String, Object> map) {
		@SuppressWarnings("unchecked")
		int count = ((List<Long>) map.get("wordNoList")).size();
		return sqlSession.update("bookmark.updateBmkListNo", map)==count ;
	}

	public boolean delete(BookmarkVo bookmarkVo) {
		return sqlSession.delete("bookmark.delete", bookmarkVo)==1;
	}

	/*테스트용*/
	public void deleteAll() {
		sqlSession.delete("bookmark.deleteAll");
	}

	/*테스트용*/
	public int getCount() {
		return sqlSession.selectOne("bookmark.getCount");
	}

}