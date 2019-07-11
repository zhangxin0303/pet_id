package com.cqcej.web.modules.app.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.modules.common.entity.UserPriseEntity;

/**
 * 社区点赞记录，包括帖子和评论
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-30 19:26:37
 */
public interface AppUserPriseService extends IService<UserPriseEntity> {
    
    boolean isPrised(long userId, long objectId, int type);
}

