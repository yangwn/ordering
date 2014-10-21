package com.github.dactiv.showcase.test.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.bean.showcase.dao.account.AreaDao;
import com.github.bean.showcase.dao.account.UserDao;
import com.github.bean.showcase.entity.account.Area;
import com.github.bean.showcase.entity.account.User;

public class UserDaoTest extends HibernateTestBase {

	@Autowired
	private UserDao userDao;

	@Autowired
	private AreaDao areaDao;

	@Test
	public void testSaveUser() {

		Area area = areaDao.getAreaByAreaCode("10000");
		area.setName("rrr");

		User user = new User();
		user.setArea(area);
		user.setCell("13998475865");
		user.setCreateBy("wunyang");
		user.setLevel(0);
		user.setState(1);
		user.setType(1);
		user.setUsername("wunyang");
		user.setPassword("wunyang");
		user.setRealname("yangwunan");
		// userDao.save(user);
		// System.out.println("sssss:" + user.getId());
		
		userDao.delete("ff8080814931ba08014931ba0e870000");
	}
}
