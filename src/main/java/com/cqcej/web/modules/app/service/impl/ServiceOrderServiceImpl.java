package com.cqcej.web.modules.app.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.AlipayRefundUtils;
import com.cqcej.web.common.utils.AppConstants;
import com.cqcej.web.common.utils.WeChatRefoundUtils;
import com.cqcej.web.modules.app.dao.AppServiceOrderDao;
import com.cqcej.web.modules.app.entity.AppServiceOrderEntity;
import com.cqcej.web.modules.app.entity.AppServiceOrderStatisticsEntity;
import com.cqcej.web.modules.app.entity.AppWorkerOrderEntity;
import com.cqcej.web.modules.app.form.RefundForm;
import com.cqcej.web.modules.app.service.AppServiceOrderService;
import com.cqcej.web.modules.app.utils.AppConfig;
import com.cqcej.web.modules.app.utils.AppConstant;
import com.cqcej.web.modules.app.utils.AppPage;
import com.cqcej.web.modules.common.entity.ServiceOrderEntity;
import com.cqcej.web.modules.common.entity.WorkerEntity;
import com.cqcej.web.modules.common.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@Service("appServiceOrderService")
public class ServiceOrderServiceImpl extends ServiceImpl<AppServiceOrderDao, AppServiceOrderEntity> implements AppServiceOrderService {
	
	@Autowired
	private WorkerService workerService;
	
	@Autowired
	private AppConfig config;
	
	@Override
	public AppPage<AppServiceOrderEntity> getUserReservation(long userId, Integer type, Integer page, Integer size) {
		EntityWrapper<AppServiceOrderEntity> where = new EntityWrapper<>();
		where.where("order_type={0} and user_id={1}", type, userId);
		int count = selectCount(where);
		
		int start = (page - 1) * size;
		
		List<AppServiceOrderEntity> pages = baseMapper.getUserReservation(userId, type, start, size);
		
		return new AppPage<>(count, size, pages);
	}
	
	@Override
	public AppPage<AppServiceOrderEntity> getUserServiceOrder(long userId, Integer status, Integer page, Integer size) {
		EntityWrapper<AppServiceOrderEntity> where = new EntityWrapper<>();
		if (status == -1) {
			where.where("user_id={0}", userId);
		} else {
			where.where("order_status={0} and user_id={1}", status, userId);
		}
		int count = selectCount(where);
		
		int start = (page - 1) * size;
		
		List<AppServiceOrderEntity> pages = baseMapper.getUserServiceOrder(userId, status, start, size);
		
		return new AppPage<>(count, size, pages);
	}
	
	@Override
	public AppPage<AppWorkerOrderEntity> getServiceOrderByType(long userId, Integer type, String status, String app, Integer page, Integer size) {
		EntityWrapper<AppServiceOrderEntity> where = new EntityWrapper<>();
		if (app.equals(AppConstant.ClientMain)) {
			where.where("user_id={0} and order_subtype={1}", userId, type);
		} else {
			WorkerEntity worker = getWorkerInfoByUserId(userId);
			userId = worker.getWorkerId();
			if (app.equals(AppConstant.ClientService)) {
				if (type != 0) {
					where.where("order_subtype={0} and worker_id={1}", type, userId);
				} else {
					where.where("worker_id={0}", userId);
				}
			} else if (app.equals(AppConstant.ClientServiceExtend)) {
				if (type != 0) {
					where.where("order_subtype={0} and (pickup_id={1} or giveback_id={2})", type, userId, userId);
				} else {
					where.where("(pickup_id={0} or giveback_id={1})", userId, userId);
				}
			}
		}
		
		if (!"-1".equals(status)) {
			where.where("order_status in ({0})", status);
		}
		
		int count = selectCount(where), start = (page - 1) * size;
		// List<String> statusList = Arrays.asList(status.split(","));
		List<AppWorkerOrderEntity> data = baseMapper.getServiceOrderByType(userId, type, status, app, start, size);
		
		return new AppPage<>(count, size, data);
	}
	
	private WorkerEntity getWorkerInfoByUserId(long userId) {
		EntityWrapper<WorkerEntity> wrap = new EntityWrapper<>();
		wrap.where("user_id={0}", userId);
		return workerService.selectOne(wrap);
	}
	
	@Override
	public AppServiceOrderStatisticsEntity getServiceOrderStatistics(Long workerId) {
		int pending = 0, doing = 0, done = 0;
		
		// 获取接送/遛狗订单数据
		List<ServiceOrderEntity> list = baseMapper.getWorkerTransServiceOrder(workerId);
		for (ServiceOrderEntity order : list) {
			if (order.getOrderStatus() == ServiceOrderEntity.ORDER_STATUS_PAID) {
				// 已付款，未开始
				pending++;
			} else if (order.getOrderStatus() > ServiceOrderEntity.ORDER_STATUS_PAID && order.getOrderStatus() <= ServiceOrderEntity.ORDER_STATUS_COMMENT) {
				doing++;
			} else if (order.getOrderStatus() == ServiceOrderEntity.ORDER_STATUS_COMPLETE) {
				done++;
			}
		}
		
		return new AppServiceOrderStatisticsEntity(pending, doing, done);
	}
	
	@Override
	public Boolean updateStatus(Long userId, String app, Long orderId, Integer status) {
		ServiceOrderEntity entity = baseMapper.getUserServiceOrderById(orderId);
		if (entity == null) {
			return false;
		} else {
			if (app.equals(AppConstant.ClientService)) {
				if (!entity.getWorkerId().equals(userId) && !entity.getGivebackId().equals(userId)) {
					return false;
				}
			} else if (app.equals(AppConstant.ClientMain)) {
				if (!entity.getUserId().equals(userId)) {
					return false;
				}
			}
		}
		
		int result = baseMapper.updateServiceOrderStatus(orderId, status);
		
		return result > 0;
	}
	
	@Override
	public AppWorkerOrderEntity getServiceOrderDetail(Long orderId) {
		return baseMapper.getServiceOrderDetail("id", orderId.toString());
	}
	
	@Override
	public AppWorkerOrderEntity getServiceOrderDetail(String obj, String type) {
		return baseMapper.getServiceOrderDetail(type, obj);
	}
	
	@Override
	public boolean refund(Long userId, RefundForm form) {
		EntityWrapper<AppServiceOrderEntity> where = new EntityWrapper<>();
		where.where("order_id={0} and user_id={1}", form.getOrderId(), userId);
		int count = selectCount(where);
		if (count <= 0) {
			return false;
		}
		
		return baseMapper.refund(form.getOrderId(), form.getRefundReason(), form.getRefundExplain()) > 0;
	}
	
	//商户--今日订单
	@Override
	public AppPage<AppServiceOrderEntity> getTodayOrder(String today, Integer orderStatus, Long mechId, Integer page, Integer size) {
		EntityWrapper<AppServiceOrderEntity> where = new EntityWrapper<>();
		
		where.where("mechanism_id = {0} and service_id is not null", mechId);
		if (orderStatus != null) {
			where.where("order_status = {0}", orderStatus);
		}else{
			where.where("order_status > 0");
		}
		if (today != null && today.equals("yes")) {
			where.where("date(create_at) = date(now())");
		}else{
			where.where("date(create_at) <= date(now())");
		}
		int count = selectCount(where);
		int start = (page - 1) * size;
		List<AppServiceOrderEntity> pages = baseMapper.getTodayOrder(today, orderStatus, mechId, start, size);
		return new AppPage<>(count, size, pages);
	}
	
	//商户--订单详情
	@Override
	public AppServiceOrderEntity getMechOrderDetail(Long orderId) {
		return baseMapper.getMechOrderDetail(orderId);
	}
	
	@Override
	public AppPage<AppServiceOrderEntity> getMechRefundOrderList(Integer page, Integer size, Long mechId) {
		EntityWrapper<AppServiceOrderEntity> where = new EntityWrapper<>();
		//退款订单(待退款，已退款，退款失败)
		where.where("order_status in (42,43,44) and mechanism_id = {0}", mechId);
	
		int count = selectCount(where);
		int start = (page - 1) * size;
		List<AppServiceOrderEntity> pages = baseMapper.getMechRefundOrderList(start, size,mechId);
		return new AppPage<AppServiceOrderEntity>(count, size, pages);
	}
	
	@Override
	public AppServiceOrderEntity getMechRefundOrderDetail(Long orderId) {
		return baseMapper.getMechRefundOrderDetail(orderId);
	}
	
	/**
	 * 同意退款
	 * @param orderId
	 * @param mechId
	 * @return
	 */
	@Override
	public Boolean agreeRefund(Long orderId, Long mechId) {
		//获取订单信息
		AppServiceOrderEntity order = baseMapper.getMechOrderDetail(orderId);
		if (order != null && order.getOrderStatus() == ServiceOrderEntity.ORDER_STATUS_CANCEL_PENDING_REFUND) {//取消待退款状态的订单
			//支付宝支付
			if (order.getPaymentId() == AppConstants.PAYMENT_TYPE_ALIPAY) {
				try {
					String result = AlipayRefundUtils.refund(order.getTradeNo(), order.getPayAmount().toString(), config);
					if ("SUCCESS".equals(result)) {
						//退款申请成功,更改订单状态为43(取消-已经款)并且修改退款时间
						return baseMapper.updateOrder(orderId, mechId) > 0 ? true : false;
					}
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			//微信支付
			} else if (order.getPaymentId() == AppConstants.PAYMENT_TYPE_WECHAT) {
				//支付金额转化成分
				String payAmount = order.getPayAmount().multiply(new BigDecimal(100)).intValue() + "";
				//设置商户退款单号
				String out_refund_no = UUID.randomUUID().toString().replaceAll("-", "");
				//进入退款
				String result = WeChatRefoundUtils.refund(order.getTradeNo(), out_refund_no, payAmount, config);
				if ("SUCCESS".equals(result)) {
					//退款申请成功,更改订单状态为43(取消-已经款)
					return baseMapper.updateOrder(orderId, mechId) > 0 ? true : false;
				}
			//银联支付
			} else if (order.getPaymentId() == AppConstants.PAYMENT_TYPE_UNION) {
				//暂未开通
			}
		}
		return false;
	}
	
	//拒绝退款
	@Override
	public Boolean refuse(Long orderId, Long mechId) {
		//获取订单信息
		AppServiceOrderEntity order = baseMapper.getMechOrderDetail(orderId);
		if (order != null && order.getOrderStatus() == ServiceOrderEntity.ORDER_STATUS_CANCEL_PENDING_REFUND) {//取消待退款状态的订单
			return baseMapper.refuse(orderId, mechId) > 0 ? true : false;
		}
		return false;
	}
}
