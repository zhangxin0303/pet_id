package com.cqcej.web.modules.app.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.app.entity.AppServiceOrderEntity;
import com.cqcej.web.modules.app.entity.AppWorkerOrderEntity;
import com.cqcej.web.modules.common.entity.ServiceOrderEntity;
import com.cqcej.web.modules.common.entity.ServiceOrderStatisticsDataEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 服务订单
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-06-04 11:08:08
 */
@Mapper
public interface AppServiceOrderDao extends BaseMapper<AppServiceOrderEntity> {
	
	List<AppServiceOrderEntity> getUserReservation(@Param("userId") long userId, @Param("type") Integer type,
	                                               @Param("start") Integer start, @Param("size") Integer size);
	
	List<AppServiceOrderEntity> getUserServiceOrder(@Param("userId") long userId, @Param("status") Integer status,
	                                                @Param("start") Integer start, @Param("size") Integer size);
	
	List<ServiceOrderEntity> getWorkerTransServiceOrder(@Param("workerId") long workerId);
	
	ServiceOrderEntity getUserServiceOrderById(long orderId);
	
	List<AppWorkerOrderEntity> getServiceOrderByType(@Param("userId") long userId, @Param("type") Integer type, @Param("status") String status,
	                                                 @Param("app") String app, @Param("start") int start, @Param("size") Integer size);
	
	AppWorkerOrderEntity getServiceOrderDetail(@Param("type") String type, @Param("obj") String obj);
	
	Integer updateServiceOrderStatus(@Param("orderId") Long orderId, @Param("status") Integer status);
	
	ServiceOrderStatisticsDataEntity getServiceOrderStatisticsDate(@Param("workerId") long workerId,
	                                                               @Param("firstDateTime") String firstDateTime,
	                                                               @Param("lastDateTime") String lastDateTime);
	
	Integer refund(@Param("orderId") Long orderId, @Param("refundReason") Integer refundReason, @Param("note") String note);
	
	List<AppServiceOrderEntity> getTodayOrder(@Param("today")String today,@Param("orderStatus") Integer orderStatus, @Param("mechId") Long mechId, @Param("start") Integer start, @Param("size") Integer size);
	
	AppServiceOrderEntity getMechOrderDetail(Long orderId);
	
	List<AppServiceOrderEntity> getMechRefundOrderList(@Param("start") Integer start, @Param("size") Integer size, @Param("mechId") Long mechId);
	
	
	AppServiceOrderEntity getMechRefundOrderDetail(Long orderId);
	
	int updateOrder(@Param("orderId") Long orderId, @Param("mechId") Long mechId);
	
	int refuse(@Param("orderId") Long orderId, @Param("mechId") Long mechId);
	
}
