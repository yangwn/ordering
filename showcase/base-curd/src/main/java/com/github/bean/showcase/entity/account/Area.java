package com.github.bean.showcase.entity.account;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;

import com.github.bean.showcase.entity.BaseParameterEntity;

@Entity
@Table(name = "SYS_AREA")
@DynamicUpdate(value = true)
public class Area extends BaseParameterEntity {

	private static final long serialVersionUID = -4246224622734748457L;

	// 父级区域
	private Area parent;
	// 区域编号
	private String code;
	// 区域名称
	private String name;
	// 区域类型:1,国家;2,省,直辖市;3,地市
	private String type;
	// 备注
	private String remarks;
	// 下级组集合
	private List<Area> children = new ArrayList<Area>();
	// 是否包含叶子节点
	private Boolean leaf = Boolean.FALSE;

	/**
	 * 获取上级
	 * 
	 * @return
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_PARENT_ID")
	public Area getParent() {
		return parent;
	}

	public void setParent(Area parent) {
		this.parent = parent;
	}

	/**
	 * 获取下级集合
	 * 
	 * @return List
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = {CascadeType.ALL})
	public List<Area> getChildren() {
		return children;
	}

	/**
	 * 设置下级组集合
	 * 
	 * @param children
	 *            下级组集合
	 */
	public void setChildren(List<Area> children) {
		this.children = children;
	}

	/**
	 * 获取区域编码
	 * 
	 * @return
	 */
	@Column(nullable = false)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 获取区域名称
	 * 
	 * @return
	 */
	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取区域类型
	 * 
	 * @return
	 */
	@Column(nullable = false)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 设置当前实体是否包含子节点
	 * 
	 * @param leaf
	 *            ture表示是,false表示不是
	 */
	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	/**
	 * 获取当前实体是否包含子节点,如果是返回ture，否则返回false
	 * 
	 * @return Boolean
	 */
	public Boolean getLeaf() {
		return leaf;

	}

	/**
	 * 获取备注
	 * 
	 * @return
	 */
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * 获取父类ID
	 * 
	 * @return String
	 */
	@Transient
	public String getParentId() {
		return this.parent == null ? "" : this.parent.getId();
	}
	
	/**
	 * 获取父类名称
	 * 
	 * @return String
	 */
	@Transient
	public String getParentName() {
		return this.parent == null ? "" : this.parent.getName();
	}

}
