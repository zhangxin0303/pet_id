package com.cqcej.web.modules.common.service;


import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.modules.common.entity.SysSettingEntity;

import java.util.List;

/**
 * 系统设置
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-24 10:18
 */
public interface SysSettingService extends IService<SysSettingEntity> {
	
	
	List<SysSettingEntity> getSetting();
	
	/**
	 *
	 * @param key 键名
	 * @return value
	 */
	String findByKey(String key);
}
