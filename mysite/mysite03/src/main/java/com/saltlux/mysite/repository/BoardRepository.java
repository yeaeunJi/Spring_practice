package com.saltlux.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.saltlux.mysite.vo.BoardVo;
//import com.saltlux.mysite.vo.PageVo;

@Repository
public class BoardRepository {
	@Autowired
	private SqlSession sqlSession;
	public void deleteAll() {
		sqlSession.delete("board.deleteAll");
	}
	public int getCount() {
		return sqlSession.selectOne("board.getCount");
	}
	public boolean insert(BoardVo vo) {
		return sqlSession.insert("board.insert", vo)==1;
	}
	public BoardVo getBoard(Long no) {
		return sqlSession.selectOne("board.findOne", no);
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