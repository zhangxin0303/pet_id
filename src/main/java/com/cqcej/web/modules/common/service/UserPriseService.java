package com.cqcej.web.modules.common.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.common.entity.UserPriseEntity;

import java.util.Map;

/**
 * 社区点赞记录，包括帖子和评论
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-30 19:26:37
 */
public interface UserPriseService extends IService<UserPriseEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

