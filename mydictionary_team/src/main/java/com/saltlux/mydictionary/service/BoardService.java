package com.saltlux.mydictionary.service;

import java.util.List;

import com.saltlux.mydictionary.common.Search;
import com.saltlux.mydictionary.vo.BoardVo;
import com.saltlux.mydictionary.vo.ReplyVo;

public interface BoardService {

	public List<BoardVo> getBoardList(Search search) throws Exception;

	public void insertBoard(BoardVo boardVO) throws Exception;

	public BoardVo getBoardContent(int bid) throws Exception;

	public void updateBoard(BoardVo boardVO) throws Exception;

	public void deleteBoard(int bid) throws Exception;

	public int getBoardListCnt(Search search) throws Exception;

	//
	public List<ReplyVo> getReplyList(int bid) throws Exception;

	public int saveReply(ReplyVo replyVO) throws Exception;

	public int updateReply(ReplyVo replyVO) throws Exception;

	public int deleteReply(int rid) throws Exception;

}