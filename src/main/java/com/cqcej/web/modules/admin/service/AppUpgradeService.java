package com.cqcej.web.modules.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.admin.entity.AppUpgradeEntity;

import java.util.Map;

/**
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-06 16:05:40
 */
public interface AppUpgradeService extends IService<AppUpgradeEntity> {
	
	PageUtils queryPage(Map<String, Object> params);
	
	AppUpgradeEntity findNewVersion(String currentVersion);
}

