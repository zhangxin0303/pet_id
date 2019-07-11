package com.cqcej.web.modules.common.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.modules.common.entity.ServiceOrderEntity;

import java.util.Map;

/**
 * 服务订单
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-06-04 11:08:08
 */
public interface ServiceOrderService extends IService<ServiceOrderEntity> {
	
	ServiceOrderEntity getOrder(long userId, Long orderId);
	
	void updateField(Long orderId, String field, String value);
	
	ServiceOrderEntity getOrderByNo(String orderNo);
	
	/**
	 * 支付成功，设置is_pay和支付时间
	 * @param orderId 订单ID
	 * @param tradeNo 交易号
	 */
	void paySuccess(Long orderId, String tradeNo);
	
	Map<String, Integer> getMechanismStatistics(long mechanismId);
}

