package com.cqcej.web.modules.admin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 角色
 * 
 * @author Jia Min
 * @email Empty
 * @date 2018-08-01 10:26:17
 */
@TableName("ct_sys_role")
@ApiModel("角色")
public class AdminSysRoleEntity implements Serializable {

	/**
	 *
	 */
	@TableId
	@ApiModelProperty("用户角色中间表ID")
	protected Long id;

	@ApiModelProperty("用户ID")
	protected Long userId;

	@ApiModelProperty("角色ID")
	protected Long roleId;

	/**
	 * 账号(手机号)
	 */
	@ApiModelProperty("账号(手机号)")
	protected String mobile;

	/**
	 * 名称
	 */
	@ApiModelProperty("角色名称")
	protected String userName;

	/**
	 * 类型
	 */
	@ApiModelProperty("角色类型")
	protected String roleName;

	/**
	 * 状态
	 */
	@ApiModelProperty("状态")
	protected Long status;

	/**
	 * 密码
	 */
	@ApiModelProperty("密码")
	protected String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

