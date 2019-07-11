package com.cqcej.web.modules.common.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.Query;
import com.cqcej.web.modules.common.dao.CommunityClassDao;
import com.cqcej.web.modules.common.entity.CommunityClassEntity;
import com.cqcej.web.modules.common.service.CommunityClassService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("communityClassService")
public class CommunityClassServiceImpl extends ServiceImpl<CommunityClassDao, CommunityClassEntity> implements CommunityClassService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<CommunityClassEntity> page = this.selectPage(
				new Query<CommunityClassEntity>(params).getPage(),
				new EntityWrapper<CommunityClassEntity>()
		);

		return new PageUtils(page);
	}

}
