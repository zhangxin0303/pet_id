package com.cqcej.web.modules.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 商家端首页统计数据
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-11-06 16:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MechanismStatisticsEntity {
	/**
	 * 今日收入
	 */
	private BigDecimal todayIncome;
	
	/**
	 * 历史收入
	 */
	private BigDecimal historyIncome;
	
	/**
	 * 今日订单
	 */
	private Integer todayOrderCount;
	
	/**
	 * 历史订单
	 */
	private Integer historyOrderCount;
	
	/**
	 * 退款订单
	 */
	private Integer refundOrderCount;
}
