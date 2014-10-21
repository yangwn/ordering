package com.github.bean.showcase.dao.foundation.audit;

import com.github.bean.showcase.entity.foundation.audit.OperatingRecord;
import com.github.dactiv.orm.core.hibernate.support.HibernateSupportDao;

import org.springframework.stereotype.Repository;

/**
 * 操作记录数据访问
 * 
 * @author maurice
 *
 */
@Repository
public class OperatingRecordDao extends HibernateSupportDao<OperatingRecord, String>{
	
}
