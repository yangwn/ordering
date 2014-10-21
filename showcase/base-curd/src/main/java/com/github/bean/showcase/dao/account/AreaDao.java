package com.github.bean.showcase.dao.account;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.bean.showcase.entity.account.Area;
import com.github.dactiv.orm.core.hibernate.support.HibernateSupportDao;

@Repository
@Transactional
public class AreaDao extends HibernateSupportDao<Area, String> {

	public Area getAreaByAreaCode(String areaCode) {
		return this.findUniqueByProperty("code", areaCode);
	}

	/**
	 * 合并子类到父类中，返回一个新的集合
	 * 
	 * @param list
	 *            数据集合
	 */
	public List<Area> mergeToParent(List<Area> list) {

		List<Area> result = new ArrayList<Area>();

		for (Area r : list) {
			if (r.getParent() == null) {
				Area temp = new Area();
				BeanUtils.copyProperties(r, temp);
				mergeToParent(list, temp);
				result.add(temp);
			}
		}
		return result;
	}

	/**
	 * 遍历list中的数据,如果数据的父类与parent相等，将数据加入到parent的children中
	 * 
	 * @param list
	 *            资源集合
	 * @param parent
	 *            父类对象
	 */
	private void mergeToParent(List<Area> list, Area parent) {
		if (!parent.getLeaf()) {
			return;
		}

		parent.setChildren(new ArrayList<Area>());
		parent.setLeaf(false);

		for (Area r : list) {
			if (StringUtils.equals(r.getParentId(), parent.getId())) {
				Area temp = new Area();
				BeanUtils.copyProperties(r, temp);
				temp.setChildren(null);
				mergeToParent(list, temp);
				parent.getChildren().add(temp);
				parent.setLeaf(true);
			}
		}
	}
}
