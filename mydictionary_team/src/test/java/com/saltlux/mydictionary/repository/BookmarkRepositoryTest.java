package com.saltlux.mydictionary.repository;

import static org.hamcrest.CoreMatchers.is;
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

import com.saltlux.mydictionary.vo.BookmarkListVo;
import com.saltlux.mydictionary.vo.BookmarkVo;
import com.saltlux.mydictionary.vo.UserVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
public class BookmarkRepositoryTest {
	
	@Autowired
	private BookmarkListRepository bookmarkListRepository;
	
	
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

	private BookmarkListVo bmkList1;
	private BookmarkListVo bmkList2;
	
	@Before
	public void setUp() {
		this.user1 = new UserVo("name1", "user1", "user");
		this.user2 = new UserVo("name2", "user2", "user");
		this.admin = new UserVo("name3", "admin", "admin");
		
		bookmarkRepository.deleteAll();
		bookmarkListRepository.deleteAll();
		userRepository.deleteAll();
		
		
		userRepository.insert(user1);
		userRepository.insert(user2);
		userRepository.insert(admin);

		this.user1 = userRepository.findByNo(user1.getUserNo());
		this.user2 = userRepository.findByNo(user2.getUserNo());
		this.admin = userRepository.findByNo(admin.getUserNo());

		this.bmkList1 = new BookmarkListVo(user1.getUserNo(), "test1");
		this.bmkList2 = new BookmarkListVo(user2.getUserNo(), "test2");
		
		bookmarkListRepository.insertBmkList(bmkList1);
		bookmarkListRepository.insertBmkList(bmkList2);
		
		this.board1 = new BookmarkVo("title1", this.user1.getUserNo(), "link1", "keyword1", "thumbnail1", "description1", bmkList1.getBmkListNo() );
		this.board2 =  new BookmarkVo("title2", this.user2.getUserNo(), "link2", "keyword2", "thumbnail2", "description2", bmkList2.getBmkListNo());
		this.board3 = new BookmarkVo("title3keyword", this.user1.getUserNo(),  "link3", "keyword3", "thumbnail3", "description3keyword", bmkList1.getBmkListNo());
	}


	@Test 
	public void findByLink() throws SQLException {
		bookmarkRepository.deleteAll();
		assertThat(bookmarkRepository.getCount(), is(0));

		bookmarkRepository.insert(board1);
		bookmarkRepository.insert(board2);
		assertThat(bookmarkRepository.getCount(), is(2));

		boolean result = bookmarkRepository.existBookmark(board1);
		assertThat(result, is(true));
	}



	@Test 
	public void findLinkByUserNoAndKeyword() throws SQLException {
		bookmarkRepository.deleteAll();
		assertThat(bookmarkRepository.getCount(), is(0));

		bookmarkRepository.insert(board1);
		bookmarkRepository.insert(board2);			
		assertThat(bookmarkRepository.getCount(), is(2));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", "keyword");
		map.put("userNo", user1.getUserNo());
		List<String> list = bookmarkRepository.findLinkByUserNoAndKeyword(map);
		assertThat(list.size(), is(1));
	}
	
	@Test 
	public void getCountByUserNo() throws SQLException {
		bookmarkRepository.deleteAll();
		assertThat(bookmarkRepository.getCount(), is(0));

		bookmarkRepository.insert(board1);
		bookmarkRepository.insert(board2);			
		bookmarkRepository.insert(board3);
		assertThat(bookmarkRepository.getCount(), is(3));

		int total = bookmarkRepository.getCountByUserNo(user1.getUserNo());
		assertThat(total, is(2));
	}
	

}
