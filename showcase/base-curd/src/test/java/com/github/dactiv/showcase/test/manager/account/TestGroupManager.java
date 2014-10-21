package com.github.dactiv.showcase.test.manager.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.github.bean.showcase.common.enumeration.entity.GroupType;
import com.github.bean.showcase.common.enumeration.entity.State;
import com.github.bean.showcase.entity.account.Group;
import com.github.bean.showcase.service.account.AccountManager;
import com.github.dactiv.showcase.test.manager.ManagerTestCaseSupport;
import com.google.common.collect.Lists;

/**
 * 测试组管理所有方法
 * 
 * @author maurice
 *
 */
public class TestGroupManager extends ManagerTestCaseSupport{

	@Autowired
	private AccountManager accountManager;
	
	@Test
	@Transactional(readOnly=true)
	public void testGetGroup() {
		Group group = accountManager.getGroup("SJDK3849CKMS3849DJCK2039ZMSK0002");
		assertEquals(group.getName(), "超级管理员");
	}

	@Test
	public void testGetGroupsListOfString() {
		List<String> ids = Lists.newArrayList("402881c4408c7d2301408c86b7a80001",
						   					  "402881c4408c7d2301408c870ed10002",
						   					  "SJDK3849CKMS3849DJCK2039ZMSK0002");
		
		List<Group> result = accountManager.getGroups(ids);
		
		assertEquals(result.size(), 3);
	}

	@Test
	@Transactional
	public void testSaveGroup() {
		Group entity = new Group();
		entity.setName("test");
		entity.setRemark("...");
		entity.setType(GroupType.RoleGorup.getValue());
		entity.setState(State.Enable.getValue());
		
		int before = countRowsInTable("tb_group");
		accountManager.saveGroup(entity);
		int after = countRowsInTable("tb_group");
		
		assertEquals(before + 1, after);
		assertFalse(entity.getLeaf());
		
		Group parent = accountManager.getGroup("SJDK3849CKMS3849DJCK2039ZMSK0002");
		entity.setParent(parent);
		accountManager.saveGroup(entity);
		assertTrue(parent.getLeaf());
		entity.setParent(null);
		accountManager.saveGroup(entity);
		assertFalse(entity.getLeaf());
		
	}

	@Test
	public void testDeleteGroups() {
		int before = countRowsInTable("tb_group");
		accountManager.deleteGroups(Lists.newArrayList("402881c4408c7d2301408c870ed10002"));
		int after = countRowsInTable("tb_group");
		
		assertEquals(before - 1, after);
	}

	@Test
	public void testGetAllGroupGroupType() {
		List<Group> result = accountManager.getGroups(GroupType.RoleGorup);
		assertEquals(result.size(), 3);
		
		result = accountManager.getGroups(GroupType.RoleGorup,"402881c4408c7d2301408c870ed10002","SJDK3849CKMS3849DJCK2039ZMSK0002");
		assertEquals(result.size(), 1);
	}
	
}
