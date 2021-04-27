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
	
	public boolean insert(BookmarkVo vo) {
		return sqlSession.insert("bookmark.insert", vo)==1;
	}
	
	public BookmarkVo getBoard(long wordNo) {
		return sqlSession.selectOne("bookmark.findOne", wordNo);
	}
	
//	public List<BookmarkVo> findAll(String keyword, PageVo pagevo) {
//		Map<String, Object> map = new HashMap<>();
//		map.put("keyword", keyword);
//		map.put("start", pagevo.getStart());
//		map.put("showNum", pagevo.getShowNum());
//		return sqlSession.selectList("bookmark.findAll", map);
//	}
//	
	public List<BookmarkVo> findAll(String keyword, PageVo pagevo, UserVo userVo) {
		Map<String, Object> map = new HashMap<>();
		map.put("keyword", keyword);
		map.put("start", pagevo.getStart());
		map.put("showNum", pagevo.getShowNum());
		map.put("userNo", userVo.getUserNo());
		return sqlSession.selectList("bookmark.findAll", map);
	}
	
	public boolean delete(long wordNo) {
		return sqlSession.delete("bookmark.delete", wordNo)==1;
	}
	
//	public PageVo paging(Long shownum, String keyword){
//		Map<String, Object> map = new HashMap<>();
//		map.put("showNum", shownum);
//		map.put("keyword", keyword);
//		return sqlSession.selectOne("board.paging", map);
//	}
//
//



}