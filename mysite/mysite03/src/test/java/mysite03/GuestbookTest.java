package mysite03;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.saltlux.mysite.service.GuestbookService;

public class GuestbookTest {

	@Autowired
	private GuestbookService guestbookService;
	
	@Test
	public void findAll() {
		
	}
}
