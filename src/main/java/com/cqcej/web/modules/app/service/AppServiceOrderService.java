package com.cqcej.web.modules.app.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.modules.app.entity.AppServiceOrderEntity;
import com.cqcej.web.modules.app.entity.AppServiceOrderStatisticsEntity;
import com.cqcej.web.modules.app.entity.AppWorkerOrderEntity;
import com.cqcej.web.modules.app.form.RefundForm;
import com.cqcej.web.modules.app.utils.AppPage;


/**
 * 服务订单
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-06-04 11:08:08
 */
public interface AppServiceOrderService extends IService<AppServiceOrderEntity> {
	
	AppPage<AppServiceOrderEntity> getUserReservation(long userId, Integer type, Integer page, Integer size);
	
	AppPage<AppServiceOrderEntity> getUserServiceOrder(long userId, Integer status, Integer page, Integer size);
	
	AppPage<AppWorkerOrderEntity> getServiceOrderByType(long userId, Integer type, String app, String status, Integer page, Integer size);
	
	AppServiceOrderStatisticsEntity getServiceOrderStatistics(Long workerId);
	
	Boolean updateStatus(Long userId, String app, Long orderId, Integer status);
	
	AppWorkerOrderEntity getServiceOrderDetail(Long orderId);
	
	AppWorkerOrderEntity getServiceOrderDetail(String obj, String type);
	
	boolean refund(Long userId, RefundForm form);
	
	AppPage<AppServiceOrderEntity> getTodayOrder(String today,Integer orderStatus,Long mechId,Integer page,Integer size);
	
	AppServiceOrderEntity getMechOrderDetail(Long orderId);
	
	AppPage<AppServiceOrderEntity> getMechRefundOrderList(Integer page,Integer size,Long mechId);
	
	AppServiceOrderEntity getMechRefundOrderDetail(Long orderId);
	
	Boolean agreeRefund(Long orderId,Long mechId);
	
	Boolean refuse(Long orderId,Long mechId);
}

