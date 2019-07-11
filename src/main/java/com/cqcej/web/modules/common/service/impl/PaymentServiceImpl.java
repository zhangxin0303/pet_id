package com.cqcej.web.modules.common.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.Query;
import com.cqcej.web.modules.common.dao.PaymentDao;
import com.cqcej.web.modules.common.entity.PaymentEntity;
import com.cqcej.web.modules.common.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("paymentService")
public class PaymentServiceImpl extends ServiceImpl<PaymentDao, PaymentEntity> implements PaymentService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<PaymentEntity> page = this.selectPage(
				new Query<PaymentEntity>(params).getPage(),
				new EntityWrapper<>()
		);

		return new PageUtils(page);
	}
	
	@Override
	public List<PaymentEntity> getPayments(Integer[] clientType) {
		EntityWrapper<PaymentEntity> wrapper = new EntityWrapper<>();
		wrapper.where("payment_status={0}", PaymentEntity.PAYMENT_STATUS_OPEN);
		wrapper.in("client_type", clientType);
		return selectList(wrapper);
	}
	
}
