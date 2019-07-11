package com.cqcej.web.modules.common.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单明细数据统计
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-09-10 16:55
 */
@Data
public class ServiceOrderStatisticsDetailEntity {
	
	/**
	 * 日期
	 */
	@ApiModelProperty("日期")
	private Date date = new Date();
	
	/**
	 * 订单数量
	 */
	@ApiModelProperty("订单数量")
	private Integer orderCount = 0;
	
	/**
	 * 金额
	 */
	@ApiModelProperty("金额")
	private BigDecimal amount = new BigDecimal(0f);
}
