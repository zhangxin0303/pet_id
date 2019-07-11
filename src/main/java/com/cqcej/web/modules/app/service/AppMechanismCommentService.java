package com.cqcej.web.modules.app.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.modules.app.entity.home.AppMechanismCommentEntity;
import com.cqcej.web.modules.app.form.ServiceCommentForm;
import com.cqcej.web.modules.app.utils.AppPage;
import com.cqcej.web.modules.common.entity.MechanismCommentEntity;

/**
 * 机构评价
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-20 11:10:15
 */
public interface AppMechanismCommentService extends IService<MechanismCommentEntity> {
	
	AppPage<AppMechanismCommentEntity> getClinicComments(long userId, long clinicId, int page, int size);
	
	/**
	 * 增加点赞次数
	 */
	void addPriseCount(long objectId);
	
	/**
	 * 减少点赞次数
	 * @param objectId
	 */
	void reducePriseCount(long objectId);
	
	boolean comment(long userId, ServiceCommentForm form, long orderId);
}

