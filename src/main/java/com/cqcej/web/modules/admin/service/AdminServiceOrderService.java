package com.cqcej.web.modules.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.admin.entity.AdminServiceOrderEntity;
import com.cqcej.web.modules.admin.entity.order.OrderStatisticsEntity;
import com.cqcej.web.modules.admin.entity.order.PlatformStats;

import java.util.Map;

/**
 * 服务订单
 *
 * @author Jia Min
 * @email empty
 * @date 2018-07-24 14:09:29
 */
public interface AdminServiceOrderService extends IService<AdminServiceOrderEntity> {

	PageUtils queryPage(Map<String, Object> params);

	//订单列表
	PageUtils<AdminServiceOrderEntity> getServiceOrderList(Map<String, Object> params);

	//订单详情
	Map<String,Object> selectOrderById(Long orderId);

	//订单统计
	PageUtils<OrderStatisticsEntity> statistics(Map<String, Object> params);
	
	//平台(医疗、美容、健康)分成统计
	PlatformStats platformStats();
}

