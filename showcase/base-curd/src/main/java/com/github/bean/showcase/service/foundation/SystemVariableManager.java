package com.github.bean.showcase.service.foundation;

import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.bean.showcase.common.enumeration.SystemDictionaryCode;
import com.github.bean.showcase.dao.foundation.variable.DataDictionaryDao;
import com.github.bean.showcase.dao.foundation.variable.DictionaryCategoryDao;
import com.github.bean.showcase.entity.foundation.variable.DataDictionary;
import com.github.bean.showcase.entity.foundation.variable.DictionaryCategory;
import com.github.dactiv.orm.core.Page;
import com.github.dactiv.orm.core.PageRequest;
import com.github.dactiv.orm.core.PropertyFilter;

/**
 * 系统字典管理业务逻辑
 * 
 * @author maurice
 *
 */
@Service
@Transactional
public class SystemVariableManager {

	//数据字典数据访问
	@Autowired
	private DataDictionaryDao dataDictionaryDao;
	
	//字典类别数据访问
	@Autowired
	private DictionaryCategoryDao dictionaryCategoryDao;
	
	//---------------------------------------数据字典管理---------------------------------------//
	
	/**
	 * 获取数据字典实体
	 * 
	 * @param id 数据字典id
	 */
	public DataDictionary getDataDictionary(String id) {
		return dataDictionaryDao.load(id);
	}
	
	/**
	 * 保存数据字典
	 * 
	 * @param entity 数据字典实体
	 */
	public void saveDataDictionary(DataDictionary entity) {
		dataDictionaryDao.save(entity);
	}
	
	/**
	 * 删除数据字典
	 * 
	 * @param ids 数据字典id集合
	 */
	public void deleteDataDictionary(List<String> ids) {
		dataDictionaryDao.deleteAll(ids);
	}
	
	/**
	 * 获取数据字典分页对象
	 * 
	 * @param request 分页参数请求
	 * @param filters 属性过滤器集合
	 * 
	 * @return Page
	 */
	public Page<DataDictionary> searchDataDictionaryPage(PageRequest request,List<PropertyFilter> filters) {
		return dataDictionaryDao.findPage(request, filters);
	}
	
	/**
	 * 通过字典类别代码获取数据字典集合
	 * 
	 * @param code 字典列别
	 * @param ignoreValue 忽略字典的值
	 * 
	 * @return List
	 */
	@Cacheable(value=DataDictionary.FindByCategoryCode,key="#code.getCode()")
	public List<DataDictionary> getDataDictionariesByCategoryCode(SystemDictionaryCode code) {
		return dataDictionaryDao.getByCategoryCode(code);
	}
	
	//---------------------------------------字典类别管理---------------------------------------//
	
	/**
	 * 获取字典类别实体
	 * 
	 * @param id 数据字典id
	 */
	public DictionaryCategory getDictionaryCategory(String id) {
		return dictionaryCategoryDao.load(id);
	}
	
	/**
	 * 保存字典类别
	 * 
	 * @param entity 字典类别实体
	 */
	@CacheEvict(value=DataDictionary.FindByCategoryCode,key="#entity.getCode()")
	public void saveDictionaryCategory(DictionaryCategory entity) {
		dictionaryCategoryDao.save(entity);
	}
	
	/**
	 * 删除字典类别
	 * 
	 * @param ids 字典类别id
	 */
	public void deleteDictionaryCategory(List<String> ids) {
		List<DictionaryCategory> list = dictionaryCategoryDao.get(ids);
		for (DictionaryCategory entity : list) {
			deleteDictionaryCategory(entity);
		}
	}
	
	/**
	 * 通过实体删除字典类别
	 * 
	 * @param entity 字典类别实体
	 */
	//清楚cache key为当前实体的code值的缓存数据
	@CacheEvict(value=DataDictionary.FindByCategoryCode,key="#entity.getCode()")
	public void deleteDictionaryCategory(DictionaryCategory entity) {
		dictionaryCategoryDao.delete(entity);
	}
	
	/**
	 * 获取所有字典类别
	 * 
	 * @return List
	 */
	public List<DictionaryCategory> getDictionaryCategories() {
		return dictionaryCategoryDao.getAll();
	}
	
	/**
	 * 根据属性过滤器获取或有字典类别
	 * 
	 * @param filters 属性过滤器集合
	 * 
	 * @return List
	 */
	public List<DictionaryCategory> getDictionaryCategories(List<PropertyFilter> filters) {
		return dictionaryCategoryDao.findByPropertyFilter(filters);
	}

	/**
	 * 根据字典类别获取所有字典类型集合，但该集合已经将数据并合成树形
	 * 
	 * @return List
	 */
	public List<DictionaryCategory> getMergeDictionaryCategories() {
		List<DictionaryCategory> result = dictionaryCategoryDao.getAll(Order.desc("id"));
		return dictionaryCategoryDao.mergeToParent(result);
	}

}
