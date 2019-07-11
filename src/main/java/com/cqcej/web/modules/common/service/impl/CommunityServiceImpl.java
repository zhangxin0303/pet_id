package com.cqcej.web.modules.common.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.modules.common.dao.CommunityDao;
import com.cqcej.web.modules.common.entity.CommunityEntity;
import com.cqcej.web.modules.common.service.CommunityService;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service("communityService")
public class CommunityServiceImpl extends ServiceImpl<CommunityDao, CommunityEntity> implements CommunityService {
	
	@Override
	public boolean publishCommunity(long userId, String title, Integer type, Integer petClassId, String context) {
		CommunityEntity community = new CommunityEntity();
		community.setUserId(userId);
		community.setClassId(type);
		community.setPetClassId(petClassId);
		community.setTitle(title);
		community.setContext(context);
		community.setCreateAt(new Date());
		return baseMapper.insert(community) > 0;
	}
	
	@Override
	public boolean addViewCount(Long communityId) {
		baseMapper.addViewCount(communityId);
		return true;
	}
}
