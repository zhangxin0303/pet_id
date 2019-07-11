package com.cqcej.web.modules.common.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.Query;
import com.cqcej.web.modules.common.dao.ServiceImagesDao;
import com.cqcej.web.modules.common.entity.ServiceImagesEntity;
import com.cqcej.web.modules.common.service.ServiceImagesService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("serviceImagesService")
public class ServiceImagesServiceImpl extends ServiceImpl<ServiceImagesDao, ServiceImagesEntity> implements ServiceImagesService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<ServiceImagesEntity> page = this.selectPage(
				new Query<ServiceImagesEntity>(params).getPage(),
				new EntityWrapper<ServiceImagesEntity>()
		);

		return new PageUtils(page);
	}

}
