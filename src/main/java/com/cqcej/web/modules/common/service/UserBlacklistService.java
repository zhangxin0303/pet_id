package com.cqcej.web.modules.common.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.common.entity.UserBlacklistEntity;

import java.util.List;
import java.util.Map;

/**
 * 黑名单
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-24 11:55:55
 */
public interface UserBlacklistService extends IService<UserBlacklistEntity> {

	PageUtils queryPage(Map<String, Object> params);
	
	List<UserBlacklistEntity> getUserBlacklist(long userId);
	
	boolean deleteUserBlacklist(long userId, String ids);
}

