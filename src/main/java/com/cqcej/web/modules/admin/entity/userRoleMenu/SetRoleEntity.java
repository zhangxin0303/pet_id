package com.cqcej.web.modules.admin.entity.userRoleMenu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@ApiModel("系统角色")
@Data
public class SetRoleEntity implements Serializable {
	
	/**
	 * 订单ID
	 */
	@ApiModelProperty("系统角色ID")
	protected Long roleId;
	
	/**
	 * 订单号
	 */
	@ApiModelProperty("角色名称")
	protected String roleName;
	
	/**
	 * 描述
	 */
	@ApiModelProperty("描述")
	protected String remark;
	
	/**
	 * 状态  0：禁用   1：正常
	 */
	@ApiModelProperty("状态  0：禁用   1：正常")
	protected Integer status;
	
	/**
	 * 角色菜单
	 */
	@ApiModelProperty("角色菜单")
	protected List<Long> menuIds;
}
