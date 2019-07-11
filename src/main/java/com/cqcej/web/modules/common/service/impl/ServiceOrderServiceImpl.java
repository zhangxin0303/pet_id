package com.cqcej.web.modules.common.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.DateUtils;
import com.cqcej.web.modules.common.dao.ServiceOrderDao;
import com.cqcej.web.modules.common.entity.ServiceOrderEntity;
import com.cqcej.web.modules.common.service.ServiceOrderService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service("serviceOrderService")
public class ServiceOrderServiceImpl extends ServiceImpl<ServiceOrderDao, ServiceOrderEntity> implements ServiceOrderService {
	
	@Override
	public ServiceOrderEntity getOrder(long userId, Long orderId) {
		EntityWrapper<ServiceOrderEntity> where = new EntityWrapper<>();
		where.where("user_id={0} and order_id={1}", userId, orderId);
		
		return selectOne(where);
	}
	
	@Override
	public void updateField(Long orderId, String field, String value) {
		EntityWrapper<ServiceOrderEntity> where = new EntityWrapper<>();
		where.where("order_id={0}", orderId);
		
		ServiceOrderEntity update = new ServiceOrderEntity();
		try {
			Field declaredField = update.getClass().getDeclaredField(field);
			declaredField.setAccessible(true);
			
			switch (declaredField.getType().getSimpleName()) {
				case "Integer":
					declaredField.set(update, Integer.parseInt(value));
					break;
				
				case "Long":
					declaredField.set(update, Long.parseLong(value));
					break;
				
				case "Short":
					declaredField.set(update, Short.parseShort(value));
					break;
				
				default:
					declaredField.set(update, value);
					break;
			}
		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		update(update, where);
	}
	
	@Override
	public ServiceOrderEntity getOrderByNo(String orderNo) {
		EntityWrapper<ServiceOrderEntity> where = new EntityWrapper<>();
		where.where("order_no={0}", orderNo);
		
		return selectOne(where);
	}
	
	@Override
	public void paySuccess(Long orderId, String tradeNo) {
		EntityWrapper<ServiceOrderEntity> where = new EntityWrapper<>();
		where.where("order_id={0}", orderId);
		
		ServiceOrderEntity update = new ServiceOrderEntity();
		// 修改订单状态
		update.setOrderStatus(ServiceOrderEntity.ORDER_STATUS_PAID);
		update.setIsPay(1);
		update.setPayAt(new Date());
		update.setTradeNo(tradeNo);
		
		update(update, where);
	}
	
	@Override
	public Map<String, Integer> getMechanismStatistics(long mechanismId) {
		Date date = new Date();
		EntityWrapper<ServiceOrderEntity> where = new EntityWrapper<>();
		where.where("order_status <= 30 and mechanism_id={0}", mechanismId);
		
		// 历史订单
		Integer history = selectCount(where);
		
		// 今日订单
		where.where("create_at>{0} and create_at<{1}", DateUtils.getDateZero(date), DateUtils.format(date, DateUtils.DATE_TIME_PATTERN));
		Integer today = selectCount(where);
		
		// 退款订单
		EntityWrapper<ServiceOrderEntity> whereRefund = new EntityWrapper<>();
		whereRefund.where("order_status > 30 and mechanism_id={0}", mechanismId);
		Integer refund = selectCount(whereRefund);
		
		Map<String, Integer> result = new HashMap<>();
		result.put("history", history);
		result.put("today", today);
		result.put("refund", refund);
		
		return result;
	}
}
