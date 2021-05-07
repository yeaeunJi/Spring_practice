package com.saltlux.mydictionary.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.saltlux.mydictionary.vo.BookmarkListVo;

@Repository
public class BookmarkListRepository {

	
	@Autowired
	private SqlSession sqlSession;

	public int insertBmkList(BookmarkListVo bmkListVo) {
		return sqlSession.insert("bookmarkList.insertBmkList", bmkListVo);
	}

	public List<BookmarkListVo> selectByUserNo(long userNo) {
		return sqlSession.selectList("bookmarkList.selectByUserNo", userNo);
	}

	public int selectByTitleAndUserNo(BookmarkListVo bmkListVo) {
		return sqlSession.selectOne("bookmarkList.selectByTitleAndUserNo", bmkListVo);
	}

	public long getBasicBookmarkListNo(long userNo) {
		return sqlSession.selectOne("bookmarkList.getBasicBookmarkListNo", userNo);
	}

	public int updateWordCount(long userNo) {
		return sqlSession.update("bookmarkList.updateWordCount", userNo );
	}
	
	/*테스트용*/
	public int deleteAll() {
		return sqlSession.delete("bookmarkList.deleteAll");
	}
	
	/*테스트용*/
	public int getCount() {
		return sqlSession.selectOne("bookmarkList.getCount");
	}
	
}
