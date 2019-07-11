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
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.Query;
import com.cqcej.web.modules.admin.dao.SysLogDao;
import com.cqcej.web.modules.admin.entity.SysLogEntity;
import com.cqcej.web.modules.admin.service.LogService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("sysLogService")
public class LogServiceImpl extends ServiceImpl<SysLogDao, SysLogEntity> implements LogService {
	
	@Override
	public PageUtils<SysLogEntity> queryPage(Map<String, Object> params) {
		String key = (String) params.get("key");
		
		Page<SysLogEntity> page = this.selectPage(
				new Query<SysLogEntity>(params).getPage(),
				new EntityWrapper<SysLogEntity>().like(StringUtils.isNotBlank(key), "username", key)
		);
		return new PageUtils(page);
	}
}
