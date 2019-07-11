package com.cqcej.web.modules.app.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.modules.app.dao.AppCommunityCommentDao;
import com.cqcej.web.modules.app.entity.AppCommunityCommentEntity;
import com.cqcej.web.modules.app.service.AppCommunityCommentService;
import com.cqcej.web.modules.app.utils.AppPage;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("appCommunityCommentService")
public class AppCommunityCommentServiceImpl extends ServiceImpl<AppCommunityCommentDao, AppCommunityCommentEntity> implements AppCommunityCommentService {
	
	@Override
	public AppPage<AppCommunityCommentEntity> getComments(Long communityId, Integer page, Integer size) {
		int count = baseMapper.getCommentCount(communityId);
		
		int start = (page - 1) * size;
		List<AppCommunityCommentEntity> list = baseMapper.getCommentList(communityId, start, size);
		
		return new AppPage<>(count, size, list);
	}
}
