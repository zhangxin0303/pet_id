package com.cqcej.web.modules.app.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.modules.app.dao.home.AppWorkerCommentDao;
import com.cqcej.web.modules.app.entity.home.AppWorkerCommentEntity;
import com.cqcej.web.modules.app.service.AppWorkerCommentService;
import com.cqcej.web.modules.app.utils.AppPage;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("appWorkerCommentService")
public class AppWorkerCommentServiceImpl extends ServiceImpl<AppWorkerCommentDao, AppWorkerCommentEntity> implements AppWorkerCommentService {
	
	@Override
	public AppPage<AppWorkerCommentEntity> getWorkerComments(long workerId, Integer page, Integer size) {
		int count = baseMapper.getWorkerCommentCount(workerId);
		
		int start = (page - 1) * size;
		List<AppWorkerCommentEntity> lists = baseMapper.getWorkerComments(workerId, start, size);
		
		return new AppPage<>(count, size, lists);
	}
}
