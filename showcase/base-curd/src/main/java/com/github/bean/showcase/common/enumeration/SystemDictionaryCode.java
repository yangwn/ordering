package com.github.bean.showcase.common.enumeration;

/**
 * 字典类型枚举
 * 
 * @author maurice
 *
 */
public enum SystemDictionaryCode {

	/**
	 * 状态类型
	 */
	State("state"),

	/**
	 * 资源类型
	 */
	ResourceType("resource-type"),

	/**
	 * 组类型
	 */
	GroupType("group-type"),

	/**
	 * 属性值类型
	 */
	ValueType("value-type"),

	/**
	 * 操作状态类型
	 */
	OperatingState("operating-state"),

	/**
	 * 用户类型
	 */
	UserType("user-type"),

	/**
	 * 用户级别
	 */
	AreaLevel("area-level"),
	;

	private String code;

	private SystemDictionaryCode(String code) {
		this.code = code;
	}

	/**
	 * 获取类型代码
	 * 
	 * @return String
	 */
	public String getCode() {
		return this.code;
	}
}
