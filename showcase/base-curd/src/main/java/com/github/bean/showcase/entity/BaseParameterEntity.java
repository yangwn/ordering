package com.github.bean.showcase.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseParameterEntity extends IdEntity {

	private static final long serialVersionUID = 1L;

	// 创建者
	private String createBy;
	// 创建者
	private Date createTime;
	// 修改者
	private String updateBy;
	// 修改时间
	private Date updateDate;
	// 是否已被删除
	private Boolean delFlag = Boolean.FALSE;

	/**
	 * 获取创建者
	 * 
	 * @return String
	 */
	@Column(name = "create_by")
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	/**
	 * 获取创建时间
	 * 
	 * @return String
	 */
	@Column(name = "create_date")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取修改者
	 * 
	 * @return
	 */
	@Column(name = "update_by")
	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	/**
	 * 获取修改时间
	 * 
	 * @param updateBy
	 */
	@Column(name = "update_date")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 获取是否删除标记
	 * 
	 * @return
	 */
	@Column(name = "del_flag")
	public Boolean getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}

}
