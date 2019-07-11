package com.cqcej.web.modules.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.Query;
import com.cqcej.web.modules.admin.dao.AdminShopOrderDao;
import com.cqcej.web.modules.admin.entity.AdminShopOrderEntity;
import com.cqcej.web.modules.admin.service.AdminShopOrderService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("adminShopOrderService")
public class AdminShopOrderServiceImpl extends ServiceImpl<AdminShopOrderDao, AdminShopOrderEntity> implements AdminShopOrderService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<AdminShopOrderEntity> page = this.selectPage(
				new Query<AdminShopOrderEntity>(params).getPage(),
				new EntityWrapper<AdminShopOrderEntity>()
		);

		return new PageUtils(page);
	}

}
