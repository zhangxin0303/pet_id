package com.cqcej.web.modules.app.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DESCRIPTION
 *
 * @author Jia Min
 * @email 510573309@qq.com
 * @date 2018-11-13 14:13
 */
@ApiModel("医师明细列表")
@Data
public class MechDoctorStatisticsDTO implements Serializable {
	
	/**
	 * 日期
	 */
	@ApiModelProperty("日期")
	protected String date;
	
	/**
	 * 订单
	 */
	@ApiModelProperty("订单")
	protected Integer count;
	
	/**
	 * 金额
	 */
	@ApiModelProperty("金额")
	protected BigDecimal amount;
}
