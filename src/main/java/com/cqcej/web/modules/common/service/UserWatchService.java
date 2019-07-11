package com.cqcej.web.modules.common.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.common.entity.UserWatchEntity;

import java.util.Map;

/**
 * 用户关注
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-06-22 10:47:54
 */
public interface UserWatchService extends IService<UserWatchEntity> {

	PageUtils queryPage(Map<String, Object> params);
	
	boolean watchUser(long userId, Long targetId);
}

