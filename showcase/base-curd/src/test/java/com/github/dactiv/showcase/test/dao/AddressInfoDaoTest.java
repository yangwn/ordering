package com.github.dactiv.showcase.test.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.bean.showcase.dao.AddressInfoDao;
import com.github.bean.showcase.entity.AddressInfo;

public class AddressInfoDaoTest extends HibernateTestBase {

	@Autowired
	private AddressInfoDao addressInfoDao;

	@Test
	public void testAddressInfo() {
		AddressInfo addr = new AddressInfo();
		addr.setAddrInfo("info");
		addr.setCustomerId("18199");
		addr.setReceiver("wunyang");
		addr.setReceiverCell("1288e994444");
		addressInfoDao.insertAddressInfo(addr);
	}

	//@Test
	public void getAddressInfo() {
		AddressInfo addr = addressInfoDao.getAddressInfo();
		System.out.println(addr.getAddrInfo());
	}
}
