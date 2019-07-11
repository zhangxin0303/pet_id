package com.cqcej.web.modules.admin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 设置表
 * 
 * @author Jia Min
 * @date 2018-08-14 16:08:29
 */
@TableName("ct_settings")
@ApiModel("设置表")
@Data
public class AdminSettingsEntity implements Serializable {

	/**
	 * 
	 */
	@TableId
	@ApiModelProperty("")
	protected String settingKey;

	/**
	 * 
	 */
	@ApiModelProperty("")
	protected String settingValue;

	/**
	 * 描述
	 */
	@ApiModelProperty("描述")
	protected String description;
}
