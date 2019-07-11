package com.cqcej.web.modules.app.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.modules.app.dao.home.AppMechanismCommentDao;
import com.cqcej.web.modules.app.entity.AppServiceOrderEntity;
import com.cqcej.web.modules.app.entity.home.AppMechanismCommentEntity;
import com.cqcej.web.modules.app.form.ServiceCommentForm;
import com.cqcej.web.modules.app.service.AppMechanismCommentService;
import com.cqcej.web.modules.app.service.AppMechanismService;
import com.cqcej.web.modules.app.service.AppServiceOrderService;
import com.cqcej.web.modules.app.utils.AppPage;
import com.cqcej.web.modules.common.entity.MechanismCommentEntity;
import com.cqcej.web.modules.common.entity.ServiceOrderEntity;
import com.cqcej.web.modules.common.entity.WorkerCommentEntity;
import com.cqcej.web.modules.common.service.ServiceOrderService;
import com.cqcej.web.modules.common.service.WorkerCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service("appMechanismCommentService")
public class AppMechanismCommentServiceImpl extends ServiceImpl<AppMechanismCommentDao, MechanismCommentEntity> implements AppMechanismCommentService {
	
	@Autowired
	private AppServiceOrderService appServiceOrderService;
	
	@Autowired
	private ServiceOrderService serviceOrderService;
	
	@Autowired
	private AppMechanismService mechanismService;
	
	@Autowired
	private WorkerCommentService workerCommentService;
	
    @Override
    public AppPage<AppMechanismCommentEntity> getClinicComments(long userId, long clinicId, int page, int size) {
	    EntityWrapper<MechanismCommentEntity> wrapper = new EntityWrapper<>();
	    wrapper.where("mechanism_id={0}", clinicId);
	    int count = selectCount(wrapper);
	
	    int start = (page - 1) * size;
	    List<AppMechanismCommentEntity> list = baseMapper.getClinicComments(userId, clinicId, start, size);
	    return new AppPage<>(count, size, list);
    }
	
	@Override
	public void addPriseCount(long objectId) {
		baseMapper.addPriseCount(objectId);
	}
	
	@Override
	public void reducePriseCount(long objectId) {
		baseMapper.reducePriseCount(objectId);
	}
	
	@Override
	public boolean comment(long userId, ServiceCommentForm form, long orderId) {
		ServiceOrderEntity order = serviceOrderService.selectById(orderId);
		if (order.getOrderStatus() != ServiceOrderEntity.ORDER_STATUS_COMMENT) {
			// 订单不是待评价状态
			return false;
		}
		
		// 机构评价
		MechanismCommentEntity comment = new MechanismCommentEntity();
		comment.setUserId(userId);
		comment.setMechanismId(order.getMechanismId());
		comment.setServiceId(order.getServiceId());
		comment.setCommentLevel(form.getStore_number());
		comment.setCommentContent(form.getContent());
		comment.setCreateAt(new Date());
		baseMapper.insert(comment);
		
		// 更新机构信息
		mechanismService.commentUpdate(form.getStore_number(), order.getMechanismId());
		
		if (form.getIs_beauty() != 0) {
			// 有美容评价
			WorkerCommentEntity workerComment = new WorkerCommentEntity();
			workerComment.setCommentContent("");
			workerComment.setWorkerId(order.getWorkerId());
			workerComment.setUserId(userId);
			workerComment.setCommentLevel(form.getBeauty_number());
			workerComment.setCreateAt(new Date());
			workerCommentService.insert(workerComment);
		}
		
		if (form.getIs_pickup() != 0) {
			// 有接送评价
			WorkerCommentEntity workerComment = new WorkerCommentEntity();
			workerComment.setCommentContent("");
			workerComment.setWorkerId(order.getWorkerId());
			workerComment.setUserId(userId);
			workerComment.setCommentLevel(form.getPickup_number());
			workerComment.setCreateAt(new Date());
			workerCommentService.insert(workerComment);
		}
		
		// 更新订单已完成
		AppServiceOrderEntity orderFinish = new AppServiceOrderEntity();
		orderFinish.setOrderId(orderId);
		orderFinish.setOrderStatus(ServiceOrderEntity.ORDER_STATUS_COMPLETE);
		appServiceOrderService.updateById(orderFinish);
		
		return true;
	}
}
