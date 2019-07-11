package com.cqcej.web.modules.app.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.modules.app.entity.home.AppWorkerCommentEntity;
import com.cqcej.web.modules.app.utils.AppPage;

/**
 * 评论工作人员
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-19 15:05:48
 */
public interface AppWorkerCommentService extends IService<AppWorkerCommentEntity> {
	
	AppPage<AppWorkerCommentEntity> getWorkerComments(long workerId, Integer page, Integer size);
}

