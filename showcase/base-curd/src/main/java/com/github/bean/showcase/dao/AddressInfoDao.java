package com.github.bean.showcase.dao;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.github.bean.showcase.entity.AddressInfo;
import com.github.dactiv.orm.core.hibernate.support.HibernateSupportDao;

@Repository
public class AddressInfoDao extends HibernateSupportDao<AddressInfo, String> {

	public void insertAddressInfo(AddressInfo entity) {
		this.save(entity);
	}

	public AddressInfo getAddressInfo() {
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("addrinfo", "info");
		Query query = getSession().getNamedQuery("GET_ADDR_BY_ID")
				.setProperties(mapData);
		return (AddressInfo) query.uniqueResult();
	}

}
