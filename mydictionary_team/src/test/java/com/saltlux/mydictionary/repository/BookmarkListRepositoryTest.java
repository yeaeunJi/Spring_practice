package com.saltlux.mydictionary.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

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
public class BookmarkListRepositoryTest {

	@Autowired
	private BookmarkListRepository bookmarkListRepository;
	
	@Autowired
	private BookmarkRepository bookmarkRepository; 

	@Autowired
	private UserRepository userRepository;

	private UserVo user1;
	private UserVo user2;

	private BookmarkListVo bmkList1;
	private BookmarkListVo bmkList2;
	private BookmarkListVo bmkList3;
	
	@Before
	public void setUp() {
		this.user1 = new UserVo("name1", "user1", "user"); 
		this.user2 = new UserVo("name2", "user2", "user");
		bookmarkRepository.deleteAll();
		bookmarkListRepository.deleteAll();
		userRepository.deleteAll();
		
		userRepository.insert(user1);
		userRepository.insert(user2);

		this.bmkList1 = new BookmarkListVo(user1.getUserNo(), "기본");
		this.bmkList2 = new BookmarkListVo(user2.getUserNo(), "test2");
		this.bmkList3 = new BookmarkListVo(user1.getUserNo(), "test3");
		
		this.user1 = userRepository.findByNo(user1.getUserNo());
		this.user2 = userRepository.findByNo(user2.getUserNo());

	}
	
	@Test
	public void deleteAll()	{
		bookmarkRepository.deleteAll();
		bookmarkListRepository.deleteAll();
		assertThat(bookmarkListRepository.getCount(), is(0));
	}

	@Test
	public void getCount()	{
		bookmarkRepository.deleteAll();
		bookmarkListRepository.deleteAll();
		assertThat(bookmarkListRepository.getCount(), is(0));
		
		bookmarkListRepository.insertBmkList(bmkList1);
		assertThat(bookmarkListRepository.getCount(), is(1));
	}
	
	@Test
	public void insertBmkList() {
		bookmarkRepository.deleteAll();
		bookmarkListRepository.deleteAll();
		assertThat(bookmarkListRepository.getCount(), is(0));
		
		bookmarkListRepository.insertBmkList(bmkList1);
		assertThat(bookmarkListRepository.getCount(), is(1));
		
		BookmarkListVo getBmkList = bookmarkListRepository.selectByUserNo(user1.getUserNo()).get(0);
		assertThat(getBmkList.getTitle(), is(bmkList1.getTitle()));
		
	}
	
	@Test
	public void selectByUserNo() {
		bookmarkRepository.deleteAll();
		bookmarkListRepository.deleteAll();
		assertThat(bookmarkListRepository.getCount(), is(0));
		
		bookmarkListRepository.insertBmkList(bmkList1);
		bookmarkListRepository.insertBmkList(bmkList2);
		bookmarkListRepository.insertBmkList(bmkList3);
		assertThat(bookmarkListRepository.getCount(), is(3));
		
		List<BookmarkListVo> getBmkList = bookmarkListRepository.selectByUserNo(user1.getUserNo());
		assertThat(getBmkList.size(), is(2));
		
		assertThat(getBmkList.get(0).getTitle(), is(bmkList3.getTitle()));
		assertThat(getBmkList.get(1).getTitle(), is(bmkList1.getTitle()));
	}
	
	@Test
	public void selectByTitleAndUserNo() {
		bookmarkRepository.deleteAll();
		bookmarkListRepository.deleteAll();
		assertThat(bookmarkListRepository.getCount(), is(0));
		
		bookmarkListRepository.insertBmkList(bmkList1);
		bookmarkListRepository.insertBmkList(bmkList2);
		bookmarkListRepository.insertBmkList(bmkList3);
		assertThat(bookmarkListRepository.getCount(), is(3));
		
		int count = bookmarkListRepository.selectByTitleAndUserNo(bmkList3);
		assertThat(count, is(1));
		
	}
	
	@Test
	public void updateWordCount() {
		bookmarkRepository.deleteAll();
		bookmarkListRepository.deleteAll();
		assertThat(bookmarkListRepository.getCount(), is(0));
		
		bookmarkListRepository.insertBmkList(bmkList1);
		assertThat(bookmarkListRepository.getCount(), is(1));

		List<BookmarkListVo>  getBmkList = bookmarkListRepository.selectByUserNo(bmkList1.getUserNo());
		assertThat(getBmkList.get(0).getWordCount(), is(0));
		
		BookmarkVo board1 = new BookmarkVo("title1", this.user1.getUserNo(), "link1", "keyword1", "thumbnail1", "description1", getBmkList.get(0).getBmkListNo() );
		bookmarkRepository.insert(board1);
		
		bookmarkListRepository.updateWordCount(this.user1.getUserNo());
		
		getBmkList = bookmarkListRepository.selectByUserNo(bmkList1.getUserNo());
		assertThat(getBmkList.get(0).getWordCount(), is(1));
		
		bookmarkRepository.delete(board1);
		assertThat(bookmarkRepository.getCount(), is(0));
		
		bookmarkListRepository.updateWordCount(this.user1.getUserNo());
		getBmkList = bookmarkListRepository.selectByUserNo(bmkList1.getUserNo());
		assertThat(getBmkList.get(0).getWordCount(), is(0));
	}
}
