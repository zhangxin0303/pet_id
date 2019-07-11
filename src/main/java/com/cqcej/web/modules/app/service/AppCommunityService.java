package com.cqcej.web.modules.app.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.modules.app.entity.AppCommunityEntity;
import com.cqcej.web.modules.app.utils.AppPage;
import com.cqcej.web.modules.common.entity.CommunityEntity;

/**
 * 社区
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-06-12 17:30
 */
public interface AppCommunityService extends IService<AppCommunityEntity> {
	
	AppPage<CommunityEntity> getCommunity(int classId, int petClassId, int provinceId, long userId, int page, int size);
	
	AppPage<AppCommunityEntity> getUserCommunity(Long userId, Long targetId, Integer page, Integer size);
}

