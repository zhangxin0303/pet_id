package com.cqcej.web.modules.app.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * DESCRIPTION
 *
 * @author Jia Min
 * @email 510573309@qq.com
 * @date 2018-11-18 10:26
 */
@ApiModel("用户每日收入信息")
@Data
public class MechWorkerDayIncomeDTO {
	
	/**
	 * 日
	 */
	@ApiModelProperty("日")
	protected String day;
	
	/**
	 * 收入总额
	 */
	@ApiModelProperty("收入总额")
	protected BigDecimal sumMoney  = new BigDecimal(0.00);
}
