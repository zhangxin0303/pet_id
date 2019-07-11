package com.cqcej.web.modules.admin.entity.order;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


@ApiModel("平台(医疗、美容、健康)分成统计")
@Data
public class PlatformStats implements Serializable {
	
	/**
	 * 平台收入
	 */
	@ApiModelProperty("平台收入")
	protected BigDecimal platFormStats;
	
	/**
	 * 宠物医疗
	 */
	@ApiModelProperty("宠物医疗分成")
	protected BigDecimal medicalStats;
	
	/**
	 * 宠物美容
	 */
	@ApiModelProperty("宠物美容分成")
	protected BigDecimal beautyStats;
	
	/**
	 * 宠物健康
	 */
	@ApiModelProperty("宠物健康分成")
	protected BigDecimal healthStats;
	
}
