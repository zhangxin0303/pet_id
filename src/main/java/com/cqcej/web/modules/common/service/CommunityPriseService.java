package com.cqcej.web.modules.common.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.modules.common.entity.CommunityPriseEntity;

/**
 * 帖子点赞记录
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-06-14 16:16:54
 */
public interface CommunityPriseService extends IService<CommunityPriseEntity> {

	boolean priseCommunity(long userId, Long communityId);
}

