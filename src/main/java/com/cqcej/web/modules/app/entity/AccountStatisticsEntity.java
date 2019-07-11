package com.cqcej.web.modules.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 账户统计
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-08-15 11:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountStatisticsEntity {
	/**
	 * 今日收入
	 */
	private BigDecimal todayIncome;
	/**
	 * 总收入
	 */
	private BigDecimal totalIncome;
	/**
	 * 余额
	 */
	private BigDecimal balance;
}
