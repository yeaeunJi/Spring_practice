package com.saltlux.mydictionary.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.saltlux.mydictionary.vo.UserVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	private UserVo user1;
	private UserVo admin;
	
	@Before
	public void setUp() {
		this.user1 = new UserVo("name1", "user1", "user");
		this.admin = new UserVo("name3", "admin", "admin");
	}
	
	@Test 
	public void addAndGet() throws SQLException {
		userRepository.deleteAll();

		assertThat(userRepository.getCount(), is(0));

		userRepository.insert(user1);
		userRepository.insert(admin);
		assertThat(userRepository.getCount(), is(2));
		
		UserVo userget1 = userRepository.findByNo(user1.getUserNo());
		assertThat(user1.getName(), is(userget1.getName()));
		assertThat(user1.getId(), is(userget1.getId()));
		
		UserVo adminget1  = userRepository.findByNo(admin.getUserNo());
		assertThat(admin.getName(), is(adminget1.getName()));
		assertThat(admin.getId(), is(adminget1.getId()));
	}
	

	@Test
	public void getUserFailure() throws SQLException {
		userRepository.deleteAll();
		assertThat(userRepository.getCount(), is(0));
		UserVo vo = userRepository.findByNo(99L);
		assertThat(vo, nullValue(UserVo.class));
	}

	@Test 
	public void update() throws SQLException {		
		userRepository.deleteAll();
		assertThat(userRepository.getCount(), is(0));

		userRepository.insert(user1);	
		assertThat(userRepository.getCount(), is(1));
		
		UserVo testUser = new UserVo("updateNm", user1.getId(),"updatePw", user1.getUserNo());		
		assertThat(user1.getName(), not(testUser.getName()));
		assertThat(user1.getPassword(), not(testUser.getPassword()));
		
		userRepository.update(testUser);
		
		UserVo userget1 = (UserVo) userRepository.findByNo(testUser.getUserNo());
		assertThat(userget1.getName(), is(testUser.getName()));
		assertThat(userget1.getPassword(), is(testUser.getPassword()));
	}
}
