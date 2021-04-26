package com.saltlux.mysite.repository;

import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.saltlux.mysite.vo.BoardVo;
import com.saltlux.mysite.vo.UserVo;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
public class BoardRepositoryTest {
		@Autowired
		private BoardRepository boardRepository; 
		
		@Autowired
		private UserRepository userRepository;
		
		private UserVo user1;
		private UserVo user2;
		private UserVo admin;
		
		private BoardVo board1;
		private BoardVo board2;
		
		@Before
		public void setUp() {
			this.user1 = new UserVo("user1", "user1@gmail.com", "user1","female", "user", "F");
			this.user2 = new UserVo("user2", "user2@gmail.com", "user2","male", "user", "T");
			this.admin = new UserVo("admin", "admin@gmail.com", "admin", "male", "admin", "T");
			boardRepository.deleteAll();
			userRepository.deleteAll();
			userRepository.insert(user1);
			userRepository.insert(user2);
			userRepository.insert(admin);
			
			this.user1 = userRepository.findByNo(user1.getNo());
			this.user2 = userRepository.findByNo(user2.getNo());
			this.admin = userRepository.findByNo(admin.getNo());
			
			this.board1 = new BoardVo("title1", this.user1.getNo(), "contents1",1L, 1L, 0L );
			this.board2 =  new BoardVo("title2", this.user2.getNo(), "contents2",1L, 2L, 1L );
			
		}
		
		@Test 
		public void addAndGet() throws SQLException {
			boardRepository.deleteAll();
			assertThat(boardRepository.getCount(), is(0));
	
			boardRepository.insert(board1);
			boardRepository.insert(board2);
			assertThat(boardRepository.getCount(), is(2));
			
			BoardVo boardget1 = boardRepository.getBoard(board1.getNo());
			assertThat(boardget1.getTitle(), is(board1.getTitle()));
			assertThat(boardget1.getContents(), is(board1.getContents()));
			
			BoardVo boardget2 = boardRepository.getBoard(board2.getNo());
			assertThat(boardget2.getTitle(), is(board2.getTitle()));
			assertThat(boardget2.getContents(), is(board2.getContents()));
		}

//		@Test(expected=EmptyResultDataAccessException.class)
//		public void getUserFailure() throws SQLException {
//			dao.deleteAll();
//			assertThat(dao.getCount(), is(0));
//			
//			dao.get("unknown_id");
//		}
	//	
//		@Test 
//		public void update() throws SQLException {		
//			dao.deleteAll();
//			assertThat(dao.getCount(), is(0));
	//
//			dao.add(user1);	
//			assertThat(dao.getCount(), is(1));
//			
//			User testUser = new User(user1.getId(), "updateNm", "updatePw");		
//			assertThat(user1.getName(), not(testUser.getName()));
//			assertThat(user1.getPassword(),not(testUser.getPassword()));
//			
//			dao.Update(testUser);
//			
//			User userget1 = dao.get(user1.getId());
//			assertThat(userget1.getName(), is(testUser.getName()));
//			assertThat(userget1.getPassword(), is(testUser.getPassword()));
//		}
	//
//		@Test(expected=EmptyResultDataAccessException.class)
//		public void delete() throws SQLException {
//			dao.deleteAll();
//			assertThat(dao.getCount(), is(0));
	//
//			dao.add(user1);	
//			assertThat(dao.getCount(), is(1));
//			
//			User userget1 = dao.get(user1.getId());
//			assertThat(userget1.getName(), is(user1.getName()));
//			assertThat(userget1.getPassword(), is(user1.getPassword()));
//			
//			dao.delete(user1.getId());		
//			dao.get(user1.getId());	
//		}
	//	
//		@Test
//		public void count() throws SQLException {
//			dao.deleteAll();
//			assertThat(dao.getCount(), is(0));
//					
//			dao.add(user1);
//			assertThat(dao.getCount(), is(1));
//			
//			dao.add(user2);
//			assertThat(dao.getCount(), is(2));
//			
//			dao.add(user3);
//			assertThat(dao.getCount(), is(3));
//		}
}
