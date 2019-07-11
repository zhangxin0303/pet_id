package com.cqcej.web.modules.app.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.modules.app.dao.AppCommunityDao;
import com.cqcej.web.modules.app.entity.AppCommunityEntity;
import com.cqcej.web.modules.app.service.AppCommunityService;
import com.cqcej.web.modules.app.utils.AppPage;
import com.cqcej.web.modules.common.entity.CommunityEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("appCommunityService")
public class AppCommunityServiceImpl extends ServiceImpl<AppCommunityDao, AppCommunityEntity> implements AppCommunityService {
	
	@Override
	public AppPage<CommunityEntity> getCommunity(int classId, int petClassId, int provinceId, long userId, int page, int size) {
		int count = baseMapper.getCommunityCount(classId, petClassId, provinceId);
		
		int start = (page - 1) * size;
		List<CommunityEntity> list = baseMapper.getCommunityList(classId, petClassId, provinceId, userId, start, size);
		
		return new AppPage<>(count, size, list);
	}
	
	@Override
	public AppPage<AppCommunityEntity> getUserCommunity(Long userId, Long targetId, Integer page, Integer size) {
		int count = baseMapper.getUserCommunityCount(userId);
		
		int start = (page - 1) * size;
		List<AppCommunityEntity> list = baseMapper.getUserCommunityList(userId, targetId, start, size);
		return new AppPage<>(count, size, list);
	}
}
