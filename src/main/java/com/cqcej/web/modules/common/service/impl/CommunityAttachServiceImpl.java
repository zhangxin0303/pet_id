package com.cqcej.web.modules.common.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.Query;
import com.cqcej.web.modules.common.dao.CommunityAttachDao;
import com.cqcej.web.modules.common.entity.CommunityAttachEntity;
import com.cqcej.web.modules.common.service.CommunityAttachService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("communityAttachService")
public class CommunityAttachServiceImpl extends ServiceImpl<CommunityAttachDao, CommunityAttachEntity> implements CommunityAttachService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<CommunityAttachEntity> page = this.selectPage(
				new Query<CommunityAttachEntity>(params).getPage(),
				new EntityWrapper<CommunityAttachEntity>()
		);

		return new PageUtils(page);
	}

}
