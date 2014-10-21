package com.github.bean.showcase.dao.foundation.variable;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.bean.showcase.common.enumeration.SystemDictionaryCode;
import com.github.bean.showcase.entity.foundation.variable.DataDictionary;
import com.github.dactiv.orm.core.hibernate.support.HibernateSupportDao;

/**
 * 数据字典数据访问
 * 
 * @author maurice
 *
 */
@Repository
public class DataDictionaryDao extends HibernateSupportDao<DataDictionary, String>{
	
	/**
	 * 通过字典类别代码获取数据字典集合
	 * 
	 * @param code 字典列别
	 * @param ignoreValue 忽略字典的值
	 * 
	 * @return List
	 */
	public List<DataDictionary> getByCategoryCode(SystemDictionaryCode code) {
		
		return findByQuery(DataDictionary.FindByCategoryCode, code.getCode());
	}
	
}
