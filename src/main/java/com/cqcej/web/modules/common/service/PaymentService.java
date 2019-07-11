package com.cqcej.web.modules.common.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.common.entity.PaymentEntity;

import java.util.List;
import java.util.Map;

/**
 * 支付方式
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-08 10:47:09
 */
public interface PaymentService extends IService<PaymentEntity> {

	PageUtils queryPage(Map<String, Object> params);
	
	List<PaymentEntity> getPayments(Integer[] clientType);
}

