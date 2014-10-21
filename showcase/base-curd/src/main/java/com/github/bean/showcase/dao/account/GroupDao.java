package com.github.bean.showcase.dao.account;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.github.bean.showcase.entity.account.Group;
import com.github.dactiv.orm.core.hibernate.support.HibernateSupportDao;

/**
 * 组数据访问
 * 
 * @author maurice
 *
 */
@Repository
public class GroupDao extends HibernateSupportDao<Group, String> {

	/**
	 * 合并子类到父类中，返回一个新的集合
	 * 
	 * @param list
	 *            数据集合
	 */
	public List<Group> mergeToParent(List<Group> list) {

		List<Group> result = new ArrayList<Group>();

		for (Group r : list) {
			if (r.getParent() == null) {
				Group temp = new Group();
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
	private void mergeToParent(List<Group> list, Group parent) {
		if (!parent.getLeaf()) {
			return;
		}

		parent.setChildren(new ArrayList<Group>());
		parent.setLeaf(false);

		for (Group r : list) {

			if (StringUtils.equals(r.getParentId(), parent.getId())) {
				Group temp = new Group();
				BeanUtils.copyProperties(r, temp);
				temp.setChildren(null);
				mergeToParent(list, temp);
				parent.getChildren().add(temp);
				parent.setLeaf(true);
			}
		}
	}
}
