package com.cqcej.web.modules.common.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.Query;
import com.cqcej.web.modules.common.dao.UserFootprintDao;
import com.cqcej.web.modules.common.entity.UserFootprintEntity;
import com.cqcej.web.modules.common.service.UserFootprintService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("userFootprintService")
public class UserFootprintServiceImpl extends ServiceImpl<UserFootprintDao, UserFootprintEntity> implements UserFootprintService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<UserFootprintEntity> page = this.selectPage(
				new Query<UserFootprintEntity>(params).getPage(),
				new EntityWrapper<>()
		);

		return new PageUtils(page);
	}

}
