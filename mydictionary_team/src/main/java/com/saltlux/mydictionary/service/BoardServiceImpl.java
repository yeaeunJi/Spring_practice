package com.saltlux.mydictionary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saltlux.mydictionary.common.Search;
import com.saltlux.mydictionary.repository.BoardDao;
import com.saltlux.mydictionary.vo.BoardVo;
import com.saltlux.mydictionary.vo.ReplyVo;

@Service

public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao boardDAO;

	public List<BoardVo> getBoardList(Search search) throws Exception {
		return boardDAO.getBoardList(search);

	}

	@Override
	public void insertBoard(BoardVo boardVO) throws Exception {
		boardDAO.insertBoard(boardVO);
	}

	@Transactional
	@Override
	public BoardVo getBoardContent(int bid) throws Exception {
		boardDAO.updateViewCnt(bid);
		BoardVo boardVO = new BoardVo();
		boardVO = boardDAO.getBoardContent(bid);
		return boardVO;
	}

	@Override
	public void updateBoard(BoardVo boardVO) throws Exception {
		boardDAO.updateBoard(boardVO);
	}

	@Override
	public void deleteBoard(int bid) throws Exception {
		boardDAO.deleteBoard(bid);
	}

	@Override
	public int getBoardListCnt(Search search) throws Exception {
		return boardDAO.getBoardListCnt(search);
	}

	// 댓글 리스트

	@Override
	public List<ReplyVo> getReplyList(int bid) throws Exception {
		return boardDAO.getReplyList(bid);
	}

	@Override
	public int saveReply(ReplyVo replyVO) throws Exception {
		return boardDAO.saveReply(replyVO);
	}

	@Override
	public int updateReply(ReplyVo replyVO) throws Exception {
		return boardDAO.updateReply(replyVO);
	}

	@Override
	public int deleteReply(int rid) throws Exception {
		return boardDAO.deleteReply(rid);
	}

}