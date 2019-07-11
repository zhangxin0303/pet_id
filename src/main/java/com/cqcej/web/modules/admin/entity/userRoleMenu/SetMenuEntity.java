package com.cqcej.web.modules.admin.entity.userRoleMenu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("系统菜单")
@Data
public class SetMenuEntity {
	/**
	 * 订单ID
	 */
	@ApiModelProperty("系统菜单ID")
	protected Long menuId;
	
	/**
	 * 订单号
	 */
	@ApiModelProperty("菜单")
	protected String name;
	
	/**
	 *  菜单的子菜单
	 */
	List<SetMenuEntity> menus;
	
}
