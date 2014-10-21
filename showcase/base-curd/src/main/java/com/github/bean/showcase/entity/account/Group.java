package com.github.bean.showcase.entity.account;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.github.bean.showcase.common.SystemVariableUtils;
import com.github.bean.showcase.common.enumeration.entity.GroupType;
import com.github.bean.showcase.common.enumeration.entity.State;
import com.github.bean.showcase.entity.BaseParameterEntity;
import com.github.dactiv.common.utils.CollectionUtils;
import com.github.dactiv.orm.annotation.TreeEntity;
/**
 * 组实体
 * 
 * @author maurice
 *
 */
@Entity
@TreeEntity
@Table(name = "SYS_GROUP")
public class Group extends BaseParameterEntity {

	private static final long serialVersionUID = 1L;

	// 所属地市
	private Area area;
	// 名称
	private String name;
	// 成员
	private List<User> membersList = new ArrayList<User>();
	// 上级组
	private Group parent;
	// 下级组集合
	private List<Group> children = new ArrayList<Group>();
	// 类型
	private String type;
	// 备注
	private String remark;
	// 状态: 1,有效;0,无效;
	private Integer state = 1;
	// 是否包含叶子节点
	private Boolean leaf = Boolean.FALSE;
	// 拥有资源
	private List<Resource> resourcesList = new ArrayList<Resource>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_code", nullable = true, referencedColumnName = "code")
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	/**
	 * 获取组名称
	 * 
	 * @return String
	 */
	@NotEmpty
	@Length(max = 32)
	@Column(length = 32, nullable = false, unique = true)
	public String getName() {
		return name;
	}

	/**
	 * 设置组名称
	 * 
	 * @param name
	 *            组名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取上级组
	 * 
	 * @return {@link Group}
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_PARENT_ID")
	public Group getParent() {
		return parent;
	}

	/**
	 * 设置上级组
	 * 
	 * @param parent
	 *            组实体
	 */
	public void setParent(Group parent) {
		this.parent = parent;
	}

	/**
	 * 获取下级组集合
	 * 
	 * @return List
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = {CascadeType.ALL})
	public List<Group> getChildren() {
		return children;
	}

	/**
	 * 设置下级组集合
	 * 
	 * @param children
	 *            下级组集合
	 */
	public void setChildren(List<Group> children) {
		this.children = children;
	}

	/**
	 * 获取组成员
	 * 
	 * @return List
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SYS_GROUP_USER", joinColumns = {@JoinColumn(name = "FK_GROUP_ID")}, inverseJoinColumns = {@JoinColumn(name = "FK_USER_ID")})
	public List<User> getMembersList() {
		return membersList;
	}

	/**
	 * 设置组成员
	 * 
	 * @param membersList
	 *            用户集合
	 */
	public void setMembersList(List<User> membersList) {
		this.membersList = membersList;
	}

	/**
	 * 获取拥有资源
	 * 
	 * @return List
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SYS_GROUP_RESOURCE", joinColumns = {@JoinColumn(name = "FK_GROUP_ID")}, inverseJoinColumns = {@JoinColumn(name = "FK_RESOURCE_ID")})
	public List<Resource> getResourcesList() {
		return resourcesList;
	}

	/**
	 * 设置该组的操作资源
	 * 
	 * @param resourcesList
	 *            资源集合
	 */
	public void setResourcesList(List<Resource> resourcesList) {
		this.resourcesList = resourcesList;
	}

	/**
	 * 获取组类型
	 * 
	 * @return String
	 */
	@NotEmpty
	@Length(min = 2, max = 2)
	@Column(nullable = false, length = 2)
	public String getType() {
		return type;
	}

	/**
	 * 设置组类型
	 * 
	 * @param type
	 *            类型
	 * 
	 * @see GroupType
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 获取备注
	 * 
	 * @return String
	 */
	@Length(max = 512)
	@Column(length = 512)
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置备注
	 * 
	 * @param remark
	 *            备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取组状态
	 * 
	 * @return
	 */
	@Min(1)
	@Max(3)
	@NotNull
	@Column(nullable = false, length = 1)
	public Integer getState() {
		return state;
	}

	/**
	 * 设置组状态
	 * 
	 * @param state
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * 获取所有成员的id
	 * 
	 * @return List
	 */
	@Transient
	public List<String> getMemberIds() {
		return CollectionUtils.extractToList(this.membersList, "id");
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

	/**
	 * 获取状态名称
	 * 
	 * @return String
	 */
	@Transient
	public String getStateName() {
		return SystemVariableUtils.getName(State.class, this.state);
	}

}
