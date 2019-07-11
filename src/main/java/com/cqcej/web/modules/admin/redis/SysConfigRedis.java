package com.cqcej.web.modules.admin.redis;

import com.cqcej.web.common.utils.RedisKeys;
import com.cqcej.web.common.utils.RedisUtils;
import com.cqcej.web.modules.admin.entity.SysConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 系统配置Redis
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2017/7/18 21:08
 */
@Component
public class SysConfigRedis {
	@Autowired
	private RedisUtils redisUtils;
	
	public void saveOrUpdate(SysConfigEntity config) {
		if (config == null) {
			return;
		}
		String key = RedisKeys.getSysConfigKey(config.getKey());
		redisUtils.set(key, config);
	}
	
	public void delete(String configKey) {
		String key = RedisKeys.getSysConfigKey(configKey);
		redisUtils.delete(key);
	}
	
	public SysConfigEntity get(String configKey) {
		String key = RedisKeys.getSysConfigKey(configKey);
		return redisUtils.get(key, SysConfigEntity.class);
	}
}
