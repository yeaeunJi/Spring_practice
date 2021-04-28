package com.saltlux.mydictionary.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.saltlux.mydictionary.vo.BookmarkVo;
import com.saltlux.mydictionary.vo.PageVo;
import com.saltlux.mydictionary.vo.UserVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
public class BookmarkRepositoryTest {
	@Autowired
	private BookmarkRepository bookmarkRepository; 

	@Autowired
	private UserRepository userRepository;

	private UserVo user1;
	private UserVo user2;
	private UserVo admin;

	private BookmarkVo board1;
	private BookmarkVo board2;
	private BookmarkVo board3;
	private BookmarkVo board4;

	@Before
	public void setUp() {
		this.user1 = new UserVo("name1", "user1", "user");
		this.user2 = new UserVo("name2", "user2", "user");
		this.admin = new UserVo("name3", "admin", "admin");
		bookmarkRepository.deleteAll();
		userRepository.deleteAll();
		userRepository.insert(user1);
		userRepository.insert(user2);
		userRepository.insert(admin);

		this.user1 = userRepository.findByNo(user1.getUserNo());
		this.user2 = userRepository.findByNo(user2.getUserNo());
		this.admin = userRepository.findByNo(admin.getUserNo());

		// String title, long userNo, String contents, String link, String keyword, String thumbnail, String despcription
		this.board1 = new BookmarkVo("title1", this.user1.getUserNo(), "link1", "keyword1", "thumbnail1", "description1");
		this.board2 =  new BookmarkVo("title2", this.user2.getUserNo(), "link2", "keyword2", "thumbnail2", "description2");
		this.board3 = new BookmarkVo("title3", this.user1.getUserNo(),  "link3", "keyword3", "thumbnail3", "description3");
		this.board4 =  new BookmarkVo("title4", this.user2.getUserNo(),  "link4", "keyword4", "thumbnail4", "description4");
	}

	@Test 
	public void addAndGet() throws SQLException {
		bookmarkRepository.deleteAll();
		assertThat(bookmarkRepository.getCount(), is(0));

		bookmarkRepository.insert(board1);
		bookmarkRepository.insert(board2);
		assertThat(bookmarkRepository.getCount(), is(2));

		BookmarkVo boardget1 = bookmarkRepository.getBoard(board1.getWordNo());

		assertThat(boardget1.getTitle(), is(board1.getTitle()));
		assertThat(boardget1.getLink(), is(board1.getLink()));

		BookmarkVo boardget2 = bookmarkRepository.getBoard(board2.getWordNo());
		assertThat(boardget2.getTitle(), is(board2.getTitle()));
		assertThat(boardget2.getLink(), is(board2.getLink()));
	}	
	
	@Test 
	public void findByLink() throws SQLException {
		bookmarkRepository.deleteAll();
		assertThat(bookmarkRepository.getCount(), is(0));

		bookmarkRepository.insert(board1);
		bookmarkRepository.insert(board2);
		assertThat(bookmarkRepository.getCount(), is(2));

		boolean result = bookmarkRepository.existBookmark(board1.getLink());
		assertThat(result, is(true));
	}

	@Test 
	public void findAll() throws SQLException {
		bookmarkRepository.deleteAll();
		assertThat(bookmarkRepository.getCount(), is(0));

		bookmarkRepository.insert(board1);
		bookmarkRepository.insert(board2);			
		bookmarkRepository.insert(board3);
		bookmarkRepository.insert(board4);
		assertThat(bookmarkRepository.getCount(), is(4));

		PageVo pagevo = new PageVo(0, 5);

		List<BookmarkVo> list = bookmarkRepository.findAll("", pagevo, user1);
		assertThat(list.size(), is(2));
	}

	@Test
	public void getBoardFailure() throws SQLException {
		bookmarkRepository.deleteAll();
		assertThat(bookmarkRepository.getCount(), is(0));

		BookmarkVo vo = bookmarkRepository.getBoard(999L);
		assertThat(vo, nullValue(BookmarkVo.class));
	}

	@Test
	public void deleteByWordNo() throws SQLException {
		bookmarkRepository.deleteAll();
		assertThat(bookmarkRepository.getCount(), is(0));

		bookmarkRepository.insert(board1);	
		assertThat(bookmarkRepository.getCount(), is(1));

		BookmarkVo boardget1 = bookmarkRepository.getBoard(board1.getWordNo());
		assertThat(boardget1.getTitle(), is(board1.getTitle()));
		assertThat(boardget1.getLink(), is(board1.getLink()));

		bookmarkRepository.delete(board1.getWordNo());		
		BookmarkVo vo =bookmarkRepository.getBoard(board1.getWordNo());
		assertThat(vo, nullValue(BookmarkVo.class));
	}

	@Test
	public void deleteByLinkAndUserNo() throws SQLException {
		bookmarkRepository.deleteAll();
		assertThat(bookmarkRepository.getCount(), is(0));

		bookmarkRepository.insert(board1);	
		assertThat(bookmarkRepository.getCount(), is(1));

		BookmarkVo boardget1 = bookmarkRepository.getBoard(board1.getWordNo());
		assertThat(boardget1.getTitle(), is(board1.getTitle()));
		assertThat(boardget1.getLink(), is(board1.getLink()));

		bookmarkRepository.deleteByLinkAndUserNo(boardget1);		
		BookmarkVo vo =bookmarkRepository.getBoard(board1.getWordNo());
		assertThat(vo, nullValue(BookmarkVo.class));
	}

}
