package com.cqcej.web.modules.admin.entity.userRoleMenu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("系统用户")
@Data
public class SetUserEntity implements Serializable {
	
	/**
	 * 订单ID
	 */
	@ApiModelProperty("系统用户ID")
	protected Long userId;
	
	/**
	 * 订单号
	 */
	@ApiModelProperty("用户名称")
	protected String username;
	
//	/**
//	 * 用户角色
//	 */
//	@ApiModelProperty("用户角色")
//	protected List<SetRoleEntity> roles;
}
