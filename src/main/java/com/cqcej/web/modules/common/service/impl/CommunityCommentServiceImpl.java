package com.cqcej.web.modules.common.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.Query;
import com.cqcej.web.modules.common.dao.CommunityCommentDao;
import com.cqcej.web.modules.common.entity.CommunityCommentEntity;
import com.cqcej.web.modules.common.service.CommunityCommentService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("communityCommentService")
public class CommunityCommentServiceImpl extends ServiceImpl<CommunityCommentDao, CommunityCommentEntity> implements CommunityCommentService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<CommunityCommentEntity> page = this.selectPage(
				new Query<CommunityCommentEntity>(params).getPage(),
				new EntityWrapper<CommunityCommentEntity>()
		);

		return new PageUtils(page);
	}

}
