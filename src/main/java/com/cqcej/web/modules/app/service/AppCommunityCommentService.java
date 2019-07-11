package com.cqcej.web.modules.app.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.modules.app.entity.AppCommunityCommentEntity;
import com.cqcej.web.modules.app.utils.AppPage;

/**
 * 社区评论
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-30 16:30:26
 */
public interface AppCommunityCommentService extends IService<AppCommunityCommentEntity> {
	
	AppPage<AppCommunityCommentEntity> getComments(Long communityId, Integer page, Integer size);
}

