package com.cqcej.web.modules.admin.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.admin.dao.AdminPaymentDao;
import com.cqcej.web.modules.admin.entity.AdminPaymentEntity;
import com.cqcej.web.modules.admin.service.AdminPaymentService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("adminPaymentService")
public class AdminPaymentServiceImpl extends ServiceImpl<AdminPaymentDao, AdminPaymentEntity> implements AdminPaymentService {
	
	@Override
	public PageUtils getList(Integer page, Integer size) {
		int start = (page - 1) * size;
		List<AdminPaymentEntity> list = baseMapper.getList(start,size);
		int count = baseMapper.getListCount();
		PageUtils data = new PageUtils(list,count,page,size);
		return data;
	}
	
	@Override
	public int forbidden(Integer paymentId, Integer paymentStatus) {
		return baseMapper.forbidden(paymentId,paymentStatus);
	}
	
	@Override
	public AdminPaymentEntity info(Integer paymentId) {
		return baseMapper.info(paymentId);
	}
	
	@Override
	public int updatePay(AdminPaymentEntity p) {
		return baseMapper.updatePay(p);
	}
	
	@Override
	public int deletePay(Integer payId) {
		return baseMapper.deletePay(payId);
	}
}



