package com.saltlux.mydictionary.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.saltlux.mydictionary.vo.BookmarkVo;
import com.saltlux.mydictionary.vo.PageVo;
import com.saltlux.mydictionary.vo.UserVo;

@Repository
public class BookmarkRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public void deleteAll() {
		sqlSession.delete("bookmark.deleteAll");
	}
	
	public int getCount() {
		return sqlSession.selectOne("bookmark.getCount");
	}
	
	public int getCountByUserNo(long userNo) {
		return sqlSession.selectOne("bookmark.getCountByUserNo", userNo);
	}
	
	public int getCountByUserNoAndKeyword(Map<String, Object> map) {
		return sqlSession.selectOne("bookmark.getCountByUserNoAndKeyword", map);
	}
	
	public boolean insert(BookmarkVo vo) {
		return sqlSession.insert("bookmark.insert", vo)==1;
	}
	
	public BookmarkVo getBoard(long wordNo) {
		return sqlSession.selectOne("bookmark.findByWordNo", wordNo);
	}
	
	public boolean existBookmark(BookmarkVo bookmarkVo) {
		return (int)sqlSession.selectOne("bookmark.findByLink", bookmarkVo)==1;
	}
	
	public List<BookmarkVo> findAll(PageVo pagevo, UserVo userVo) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", pagevo.getStartRow());
		map.put("showNum", pagevo.getShowNum());
		map.put("userNo", userVo.getUserNo());
		return sqlSession.selectList("bookmark.findAll", map);
	}	
	
	
	public List<BookmarkVo> findAllByKeyword(PageVo pagevo, UserVo userVo) {
		Map<String, Object> map = new HashMap<>();
		map.put("keyword", pagevo.getKeyword());
		map.put("startRow", pagevo.getStartRow());
		map.put("showNum", pagevo.getShowNum());
		map.put("userNo", userVo.getUserNo());
		return sqlSession.selectList("bookmark.findAllByKeyword", map);
	}
	
	public boolean delete(BookmarkVo bookmarkVo) {
		return sqlSession.delete("bookmark.delete", bookmarkVo)==1;
	}

	public boolean deleteByLinkAndUserNo(BookmarkVo bookmarkVo) {
		return sqlSession.delete("bookmark.deleteByLinkAndUserNo", bookmarkVo)==1;
	}
	
	public List<String> findLinkByUserNoAndKeyword(String keyword, UserVo userVo) {
		Map<String, Object> map = new HashMap<>();
		map.put("keyword", keyword);
		map.put("userNo", userVo.getUserNo());
		return sqlSession.selectList("bookmark.findLinkByUserNoAndKeyword", map);
	}

	public List<BookmarkVo> findBySelectCondition(Map<String, Object> map) {
		return sqlSession.selectList("bookmark.findBySelectCondition", map);
	}
	
	public int getCountBySelectCondition(Map<String, Object> map) {
		return sqlSession.selectOne("bookmark.getCountBySelectCondition", map);
	}
}