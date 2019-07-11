package com.cqcej.web.modules.common.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单基础数据统计
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-09-10 16:43
 */
@Data
public class ServiceOrderStatisticsDataEntity {
	
	/**
	 * 总收入
	 */
	@ApiModelProperty("总收入")
	private BigDecimal totalIncome = new BigDecimal(0f);
	
	/**
	 * 订单数量
	 */
	@ApiModelProperty("月订单数量")
	private Integer monthOrderCount = 0;
	
	/**
	 * 月收入
	 */
	@ApiModelProperty("月收入")
	private BigDecimal monthIncome = new BigDecimal(0f);
}
