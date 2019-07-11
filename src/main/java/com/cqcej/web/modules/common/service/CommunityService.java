package com.cqcej.web.modules.common.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.modules.common.entity.CommunityEntity;

/**
 * 社区
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-30 16:30:26
 */
public interface CommunityService extends IService<CommunityEntity> {

	boolean publishCommunity(long userId, String title, Integer type, Integer petClassId, String context);
	
	boolean addViewCount(Long communityId);
}

