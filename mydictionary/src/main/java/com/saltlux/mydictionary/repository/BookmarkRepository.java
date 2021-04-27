package com.saltlux.mydictionary.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.saltlux.mydictionary.vo.BookmarkVo;
import com.saltlux.mydictionary.vo.PageVo;

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
	
	public List<BookmarkVo> findAll(String keyword, PageVo pagevo) {
		Map<String, Object> map = new HashMap<>();
		map.put("keyword", keyword);
		map.put("start", pagevo.getStart());
		map.put("showNum", pagevo.getShowNum());
		return sqlSession.selectList("bookmark.findAll", map);
	}


//
//	private  Long  getNewGNo() {
//		return sqlSession.selectOne("board.getNewGNo");
//	}
//
//	public boolean insert(BoardVo vo) {
//		vo.setgNo(getNewGNo()); 
//		return sqlSession.insert("board.insert", vo)==1;
//	}
//
//	public boolean replyInsert(BoardVo vo) {
//		return sqlSession.insert("board.insert", vo)==1;
//	}
//
//
//	public boolean updateOrderNo(Long gNo, Long oNo) {
//		Map<String, Object> map = new HashMap<>();
//		map.put("gNo", gNo);
//		map.put("oNo", oNo);
//		return sqlSession.update("board.updateOrderNo", map)>=0;
//	}
//
//	public List<BoardVo> findAll(PageVo pageVo, String keyword){
//		Map<String, Object> map = new HashMap<>();
//		map.put("start", pageVo.getStart());
//		map.put("showNum", pageVo.getShowNum());
//		map.put("keyword", keyword);
//		return sqlSession.selectList("board.fidnAll", map);
//	}
//
//	public PageVo paging(Long shownum, String keyword){
//		Map<String, Object> map = new HashMap<>();
//		map.put("showNum", shownum);
//		map.put("keyword", keyword);
//		return sqlSession.selectOne("board.paging", map);
//	}
//
//
//	public BoardVo findOne(Long no){
//		return sqlSession.selectOne("board.findOne", no);
//	}
//
//	public boolean update(BoardVo vo) {
//		return sqlSession.update("board.update", vo)==1;
//	}
//
//	public boolean updateCount(BoardVo vo) {
//		return sqlSession.update("board.updateCount", vo)==1;
//	}
//
//	public boolean delete(BoardVo vo) {
//		return sqlSession.delete("board.delete", vo)==1;
//	}
//
//	public boolean getChildCount(BoardVo vo) {
//		int count = sqlSession.selectOne("board.getChildCount", vo);
//		return count>0 ;
//	}
//
//	public BoardVo getParentInfo(Long no) {
//		return sqlSession.selectOne("board.getParentInfo", no);
//	}
//
//	public Long getMaxONo(Long gNo) {
//		return sqlSession.selectOne("board.getMaxONo", gNo);
//	}
}