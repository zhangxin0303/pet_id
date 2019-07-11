package com.cqcej.web.modules.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 订单统计
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-08-14 15:11
 */
@Data
@AllArgsConstructor
public class AppServiceOrderStatisticsEntity {
	private Integer pending;
	private Integer doing;
	private Integer done;
}
