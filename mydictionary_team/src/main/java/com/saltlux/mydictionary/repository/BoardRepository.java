package com.saltlux.mydictionary.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.saltlux.mydictionary.common.Search;
import com.saltlux.mydictionary.vo.BoardVo;
import com.saltlux.mydictionary.vo.ReplyVo;

@Repository
public class BoardRepository implements BoardDao{

	@Autowired
	private SqlSession sqlSession;

	@Override

	public List<BoardVo> getBoardList(Search search) throws Exception {
		return sqlSession.selectList("board.getBoardList", search);
	}

	@Override

	public BoardVo getBoardContent(int bid) throws Exception {

		return sqlSession.selectOne("board.getBoardContent", bid);

	}

	@Override

	public int insertBoard(BoardVo boardVO) throws Exception {

		return sqlSession.insert("board.insertBoard", boardVO);

	}

	@Override

	public int updateBoard(BoardVo boardVO) throws Exception {

		return sqlSession.update("board.updateBoard", boardVO);

	}

	@Override

	public int deleteBoard(int bid) throws Exception {

		return sqlSession.insert("board.deleteBoard", bid);

	}

	@Override

	public int updateViewCnt(int bid) throws Exception {

		return sqlSession.update("board.updateViewCnt", bid);

	}

	@Override

	public int getBoardListCnt(Search search) throws Exception {

		return sqlSession.selectOne("board.getBoardListCnt", search);

	}

	// 댓글 리스트

	@Override

	public List<ReplyVo> getReplyList(int bid) throws Exception {

		return sqlSession.selectList("boardgetReplyList", bid);

	}

	@Override

	public int saveReply(ReplyVo replyVO) throws Exception {

		return sqlSession.insert("board.saveReply", replyVO);

	}

	@Override

	public int updateReply(ReplyVo replyVO) throws Exception {

		return sqlSession.update("board.updateReply", replyVO);

	}

	@Override

	public int deleteReply(int rid) throws Exception {

		return sqlSession.delete("board.deleteReply", rid);

	}
}
