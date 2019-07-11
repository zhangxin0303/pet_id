package com.cqcej.web.modules.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.admin.entity.AdminPaymentEntity;

/**
 * 支付方式
 *
 * @author Jia Min
 * @email Empty
 * @date 2018-09-28 13:50:38
 */
public interface AdminPaymentService extends IService<AdminPaymentEntity> {


	PageUtils getList(Integer page,Integer size);
	
	int forbidden(Integer paymentId,Integer paymentStatus);
	
	AdminPaymentEntity info(Integer paymentId);
	
	int updatePay(AdminPaymentEntity p);
	
	int deletePay(Integer payId);
}

