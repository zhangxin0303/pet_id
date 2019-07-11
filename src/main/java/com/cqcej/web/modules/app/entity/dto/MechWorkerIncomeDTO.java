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
 * @date 2018-11-08 14:45
 */
@ApiModel("工作者收入")
@Data
public class MechWorkerIncomeDTO implements Serializable {
	
	/**
	 * 订单数量
	 */
	@ApiModelProperty("订单数量")
	protected Integer orderCount;
	
	/**
	 * 收入总额
	 */
	@ApiModelProperty("收入总额")
	protected BigDecimal sumMoney  = new BigDecimal(0.00);
	
	/**
	 * 平均收入
	 */
	@ApiModelProperty("平均收入")
	protected BigDecimal average = new BigDecimal(0.00);
	
}
