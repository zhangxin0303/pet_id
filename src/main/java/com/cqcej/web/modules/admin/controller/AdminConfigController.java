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

package com.cqcej.web.modules.admin.controller;


import com.cqcej.web.common.annotation.SysLog;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.R;
import com.cqcej.web.common.validator.ValidatorUtils;
import com.cqcej.web.modules.admin.entity.SysConfigEntity;
import com.cqcej.web.modules.admin.service.ConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 系统配置信息
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2016年12月4日 下午6:55:53
 */
@RestController
@RequestMapping("/admin/config")
public class AdminConfigController extends AbstractController {
	@Autowired
	private ConfigService configService;
	
	/**
	 * 所有配置列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("admin:config:list")
	public R<SysConfigEntity> list(@RequestParam Map<String, Object> params) {
		PageUtils page = configService.queryPage(params);
		
		return R.ok(page);
	}
	
	
	/**
	 * 配置信息
	 */
	@GetMapping("/info/{id}")
	@RequiresPermissions("admin:config:info")
	public R info(@PathVariable("id") Long id) {
		SysConfigEntity config = configService.selectById(id);
		
		return R.ok(config);
	}
	
	/**
	 * 保存配置
	 */
	@SysLog("保存配置")
	@PostMapping("/save")
	@RequiresPermissions("admin:config:save")
	public R save(@RequestBody SysConfigEntity config) {
		ValidatorUtils.validateAdminEntity(config);
		
		configService.save(config);
		
		return R.ok();
	}
	
	/**
	 * 修改配置
	 */
	@SysLog("修改配置")
	@PostMapping("/update")
	@RequiresPermissions("admin:config:update")
	public R update(@RequestBody(required = true) SysConfigEntity config) {
		ValidatorUtils.validateAdminEntity(config);
		
		configService.update(config);
		
		return R.ok();
	}
	
	/**
	 * 删除配置
	 */
	@SysLog("删除配置")
	@PostMapping("/delete")
	@RequiresPermissions("admin:config:delete")
	public R delete(@RequestBody Long[] ids) {
		configService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
