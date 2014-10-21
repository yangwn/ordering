package com.github.dactiv.showcase.test.manager.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.github.bean.showcase.common.enumeration.entity.ResourceType;
import com.github.bean.showcase.entity.account.Resource;
import com.github.bean.showcase.service.account.AccountManager;
import com.github.dactiv.showcase.test.manager.ManagerTestCaseSupport;
import com.google.common.collect.Lists;

/**
 * 测试资源管理所有方法
 * 
 * @author maurice
 *
 */
public class TestResourceManager extends ManagerTestCaseSupport{
	
	@Autowired
	private AccountManager accountManager;
	
	@Test
	@Transactional(readOnly=true)
	public void testGetResource() {
		Resource resource = accountManager.getResource("SJDK3849CKMS3849DJCK2039ZMSK0003");
		assertEquals(resource.getName(), "权限管理");
		assertEquals(resource.getChildren().size(), 3);
	}

	@Test
	public void testGetResources() {
		List<String> ids = Lists.newArrayList(
				"SJDK3849CKMS3849DJCK2039ZMSK0007",
				"SJDK3849CKMS3849DJCK2039ZMSK0008",
				"SJDK3849CKMS3849DJCK2039ZMSK0009",
				"SJDK3849CKMS3849DJCK2039ZMSK0010"
		);
		
		List<Resource> result = accountManager.getResources(ids);
		
		assertEquals(result.size(), 4);
	}

	@Test
	@Transactional
	public void testSaveResource() {
		Resource entity = new Resource();
		entity.setName("test");
		entity.setPermission("prem[test:test]");
		entity.setRemark("...");
		entity.setType(ResourceType.Security.getValue());
		entity.setValue("/test/**");
		
		int before = countRowsInTable("tb_resource");
		accountManager.saveResource(entity);
		int after = countRowsInTable("tb_resource");
		
		assertEquals(before + 1, after);
		assertFalse(entity.getLeaf());
		
		Resource parent = accountManager.getResource("SJDK3849CKMS3849DJCK2039ZMSK0004");
		entity.setParent(parent);
		accountManager.saveResource(entity);
		assertTrue(parent.getLeaf());
		entity.setParent(null);
		accountManager.saveResource(entity);
		assertFalse(entity.getLeaf());
	}

	@Test
	@Transactional
	public void testDeleteResources() {
		
		int before = countRowsInTable("tb_resource");
		List<String> ids = Lists.newArrayList("SJDK3849CKMS3849DJCK2039ZMSK0022","SJDK3849CKMS3849DJCK2039ZMSK0023","SJDK3849CKMS3849DJCK2039ZMSK0024");
		accountManager.deleteResources(ids);
		int after = countRowsInTable("tb_resource");
		
		assertEquals(before - 3, after);
		Resource r = accountManager.getResource("SJDK3849CKMS3849DJCK2039ZMSK0018");
		assertFalse(r.getLeaf());
	}

	@Test
	public void testGetAllResources() {
		List<Resource> result = accountManager.getResources();
		assertEquals(result.size(), 25);
		
		result = accountManager.getResources("SJDK3849CKMS3849DJCK2039ZMSK0006","SJDK3849CKMS3849DJCK2039ZMSK0007");
		assertEquals(result.size(), 23);
	}
	
	@Test
	public void testGetUserResources() {
		List<Resource> result = accountManager.getUserResources("SJDK3849CKMS3849DJCK2039ZMSK0001");
		assertEquals(result.size(), 25);
	}
	
	@Test
    public void testMergeResourcesToParent() {
            List<Resource> result = accountManager.getUserResources("SJDK3849CKMS3849DJCK2039ZMSK0001");
            result = accountManager.mergeResourcesToParent(result, ResourceType.Security);
            assertEquals(result.size(), 2);
            assertEquals(result.get(0).getChildren().size(),3);
            assertEquals(result.get(1).getChildren().size(),3);
    }
}
