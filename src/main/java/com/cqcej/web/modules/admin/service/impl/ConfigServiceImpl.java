/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.cqcej.web.modules.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.exception.CTException;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.Query;
import com.cqcej.web.modules.admin.dao.SysConfigDao;
import com.cqcej.web.modules.admin.entity.SysConfigEntity;
import com.cqcej.web.modules.admin.redis.SysConfigRedis;
import com.cqcej.web.modules.admin.service.ConfigService;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Map;

@Service("sysConfigService")
public class ConfigServiceImpl extends ServiceImpl<SysConfigDao, SysConfigEntity> implements ConfigService {
	@Autowired
	private SysConfigRedis sysConfigRedis;
	
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		String key = (String) params.get("key");
		
		Page<SysConfigEntity> page = this.selectPage(
				new Query<SysConfigEntity>(params).getPage(),
				new EntityWrapper<SysConfigEntity>()
						.like(StringUtils.isNotBlank(key), "`key`", key)//(JiaMin)微调了一下给key加上了 ` 符号，因为key是数据库关键字
						.eq("status", 1)
		);
		
		return new PageUtils(page);
	}
	
	@Override
	public void save(SysConfigEntity config) {
		this.insert(config);
		sysConfigRedis.saveOrUpdate(config);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SysConfigEntity config) {
		this.updateById(config);
		sysConfigRedis.saveOrUpdate(config);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateValueByKey(String key, String value) {
		baseMapper.updateValueByKey(key, value);
		sysConfigRedis.delete(key);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteBatch(Long[] ids) {
		for (Long id : ids) {
			SysConfigEntity config = this.selectById(id);
			sysConfigRedis.delete(config.getKey());
		}
		
		this.deleteBatchIds(Arrays.asList(ids));
	}
	
	@Override
	public String getValue(String key) {
		SysConfigEntity config = sysConfigRedis.get(key);
		if (config == null) {
			config = baseMapper.queryByKey(key);
			sysConfigRedis.saveOrUpdate(config);
		}
		
		return config == null ? null : config.getValue();
	}
	
	@Override
	public <T> T getConfigObject(String key, Class<T> clazz) {
		String value = getValue(key);
		if (StringUtils.isNotBlank(value)) {
			return new Gson().fromJson(value, clazz);
		}
		
		try {
			return clazz.newInstance();
		} catch (Exception e) {
			throw new CTException("获取参数失败");
		}
	}
}
