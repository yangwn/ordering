package com.github.bean.showcase.entity.account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NamedQuery;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.github.bean.showcase.common.SystemVariableUtils;
import com.github.bean.showcase.common.enumeration.entity.State;
import com.github.bean.showcase.entity.BaseParameterEntity;
import com.github.dactiv.common.utils.CollectionUtils;

/**
 * 用户实体
 * 
 * @author maurice
 *
 */
@Entity
@Table(name = "SYS_USER")
@NamedQuery(name = User.UpdatePassword, query = "update User u set u.password = ?1 where u.id = ?2")
public class User extends BaseParameterEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 更新用户密码NamedQuery
	 */
	public static final String UpdatePassword = "updatePassword";

	private Area area;

	// 登录名称
	private String username;
	// 登录密码
	private String password;
	// 真实名称
	private String realname;
	// 状态
	private Integer state;
	// 等级
	private Integer level;
	// 类型: 1,外部用户;2,内部用户
	private Integer type;
	// 邮件
	private String email;
	// 手机
	private String cell;
	// QQ
	private String qq;
	// 关注方向
	private String focusOn;
	// 备注
	private String remark;
	// 最后登录时间
	private Date loginDate;

	// 用户所在的组
	private List<Group> groupsList = new ArrayList<Group>();
	// 用户头像
	private String portrait;

	/**
	 * 构造方法
	 */
	public User() {
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_code", nullable = true, referencedColumnName = "code")
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	/**
	 * 获取登录名称
	 * 
	 * @return String
	 */
	@NotEmpty
	@Length(min = 6, max = 32)
	@Column(length = 32, unique = true, nullable = false, updatable = false)
	public String getUsername() {
		return username;
	}

	/**
	 * 设置登录名称
	 * 
	 * @param username
	 *            登录名称
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 获取登录密码
	 * 
	 * @return String
	 */
	@NotEmpty
	@Length(min = 6, max = 32)
	@Column(nullable = false, length = 32, updatable = false)
	public String getPassword() {
		return password;
	}

	/**
	 * 设置登录密码
	 * 
	 * @param password
	 *            登录密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取真实姓名
	 * 
	 * @return String
	 */
	@NotEmpty
	@Length(max = 64)
	@Column(length = 64, nullable = false)
	public String getRealname() {
		return realname;
	}

	/**
	 * 设置真实名称
	 * 
	 * @param realname
	 *            真实姓名
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}

	/**
	 * 获取用户状态
	 * 
	 * @return {@link State}
	 */
	@Min(1)
	@Max(3)
	@NotNull
	@Column(nullable = false, length = 1)
	public Integer getState() {
		return state;
	}

	/**
	 * 设置用户状态
	 * 
	 * @param state
	 *            用户状态
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * 获取邮件
	 * 
	 * @return String
	 */
	@Email
	@Length(max = 128)
	@Column(length = 128)
	public String getEmail() {
		return email;
	}

	/**
	 * 设置邮件
	 * 
	 * @param email
	 *            邮件地址
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获取该用户所在的组
	 * 
	 * @return List
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SYS_GROUP_USER", joinColumns = {@JoinColumn(name = "FK_USER_ID")}, inverseJoinColumns = {@JoinColumn(name = "FK_GROUP_ID")})
	public List<Group> getGroupsList() {
		return groupsList;
	}

	/**
	 * 设置用户所在的组
	 * 
	 * @param groupsList
	 *            组集合
	 */
	public void setGroupsList(List<Group> groupsList) {
		this.groupsList = groupsList;
	}

	/**
	 * 获取用户头像
	 * 
	 * @return String
	 */
	@Length(max = 256)
	@Column(length = 256)
	public String getPortrait() {
		return portrait;
	}

	/**
	 * 设置用户头像
	 * 
	 * @param portrait
	 *            头像
	 */
	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	/**
	 * 外部用户等级
	 * @return
	 */
	@Column(nullable = false)
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * 用户类型： 外部与内部
	 * @return
	 */
	@Column(nullable = false)
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 获取手机号码
	 * @return
	 */
	@Column(nullable = true)
	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	/**
	 * 获取QQ
	 * @return
	 */
	@Column(nullable = true)
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	/**
	 * 获取关注方向
	 * @return
	 */
	@Column(name = "focus_on", nullable = true)
	public String getFocusOn() {
		return focusOn;
	}

	public void setFocusOn(String focusOn) {
		this.focusOn = focusOn;
	}

	/**
	 * 获取备注
	 * @return
	 */
	@Column(nullable = true)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 最后登录时间
	 * @return
	 */
	@Column(name = "login_date", nullable = true)
	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
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

	/**
	 * 获取所在组所用名称
	 * 
	 * @return String
	 */
	@Transient
	public String getGroupNames() {
		return CollectionUtils.extractToString(this.groupsList, "name", ",");
	}

}
