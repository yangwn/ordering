package com.github.dactiv.showcase.test.manager.foundation.audit;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import com.github.bean.showcase.common.enumeration.entity.OperatingState;
import com.github.bean.showcase.entity.foundation.audit.OperatingRecord;
import com.github.bean.showcase.service.foundation.SystemAuditManager;
import com.github.dactiv.showcase.test.manager.ManagerTestCaseSupport;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * 测试操作记录业务逻辑
 * 
 * @author maurice
 *
 */
public class TestOperatingRecordManager extends ManagerTestCaseSupport{

	@Autowired
	private SystemAuditManager systemAuditManager;
	
	@Test
	@Transactional
	public void testInsertOperatingRecord() {
		OperatingRecord or = new OperatingRecord();
		
		or.setStartDate(new Date());
		or.setIp("127.0.0.1");
		or.setMethod("com.github.dactiv.showcase.test.manager.foundation.audit.OperatingRecordManager.testSaveOperatingRecord()");
		or.setOperatingTarget("account/user/view");
		or.setState(OperatingState.Success.getValue());
		
		or.setFkUserId("SJDK3849CKMS3849DJCK2039ZMSK0026");
		or.setUsername("admin");
		or.setEndDate(new Date());
		
		int beforeRow = countRowsInTable("TB_OPERATING_RECORD");
		systemAuditManager.insertOperatingRecord(or);
		getSessionFactory().getCurrentSession().flush();
		int afterRow = countRowsInTable("TB_OPERATING_RECORD");
		
		assertEquals(afterRow, beforeRow + 1);
	}
	
}
