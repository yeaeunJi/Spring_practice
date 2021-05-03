package com.saltlux.mydictionary.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.saltlux.mydictionary.common.Search;
import com.saltlux.mydictionary.vo.BoardVo;
import com.saltlux.mydictionary.vo.ReplyVo;


@Repository
public interface BoardDao {

	public List<BoardVo> getBoardList(Search search) throws Exception;

	public BoardVo getBoardContent(int bid) throws Exception;

	public int insertBoard(BoardVo boardVO) throws Exception;

	public int updateBoard(BoardVo boardVO) throws Exception;

	public int deleteBoard(int bid) throws Exception;

	public int updateViewCnt(int bid) throws Exception;

	public int getBoardListCnt(Search search) throws Exception;

	//
	public List<ReplyVo> getReplyList(int bid) throws Exception;

	public int saveReply(ReplyVo replyVO) throws Exception;

	public int updateReply(ReplyVo replyVO) throws Exception;

	public int deleteReply(int rid) throws Exception;

}