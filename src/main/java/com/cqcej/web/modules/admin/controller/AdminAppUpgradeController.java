package com.cqcej.web.modules.admin.controller;

import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.R;
import com.cqcej.web.modules.admin.entity.AppUpgradeEntity;
import com.cqcej.web.modules.admin.service.AppUpgradeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-06 16:05:40
 */
@RestController
@RequestMapping("admin/appupgrade")
public class AdminAppUpgradeController {
	@Autowired
	private AppUpgradeService appUpgradeService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("admin:appupgrade:list")
	public R list(@RequestParam Map<String, Object> params) {
		PageUtils page = appUpgradeService.queryPage(params);
		
		return R.ok(page);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{upgradeId}")
	@RequiresPermissions("admin:appupgrade:info")
	public R info(@PathVariable("upgradeId") Long upgradeId) {
		AppUpgradeEntity appUpgrade = appUpgradeService.selectById(upgradeId);
		
		return R.ok(appUpgrade);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("admin:appupgrade:save")
	public R save(@RequestBody AppUpgradeEntity appUpgrade) {
		appUpgradeService.insert(appUpgrade);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("admin:appupgrade:update")
	public R update(@RequestBody AppUpgradeEntity appUpgrade) {
		appUpgradeService.updateById(appUpgrade);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("admin:appupgrade:delete")
	public R delete(@RequestBody Long[] upgradeIds) {
		appUpgradeService.deleteBatchIds(Arrays.asList(upgradeIds));
		
		return R.ok();
	}
	
}
