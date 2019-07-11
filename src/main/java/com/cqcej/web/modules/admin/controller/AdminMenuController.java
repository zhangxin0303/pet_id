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
import com.cqcej.web.common.exception.CTException;
import com.cqcej.web.common.utils.AdminConstant;
import com.cqcej.web.common.utils.R;
import com.cqcej.web.modules.admin.entity.SysMenuEntity;
import com.cqcej.web.modules.admin.service.MenuService;
import com.cqcej.web.modules.admin.service.ShiroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 系统菜单
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2016年10月27日 下午9:58:15
 */
@RestController
@RequestMapping("/admin/menu")
@Api(description = "Admin导航菜单")
public class AdminMenuController extends AbstractController {
	@Autowired
	private MenuService menuService;
	@Autowired
	private ShiroService shiroService;
	
	/**
	 * 导航菜单
	 */
	@GetMapping("/nav")
	@ApiOperation("导航菜单")
	public R nav() {
		List<SysMenuEntity> menuList = menuService.getUserMenuList(getUserId());
		Set<String> permissions = shiroService.getUserPermissions(getUserId());
//		return R.ok().put("menuList", menuList).put("permissions", permissions);
		return R.ok(menuList).ok(permissions);
	}
	
	/**
	 * 所有菜单列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("admin:menu:list")
	public List<SysMenuEntity> list() {
		List<SysMenuEntity> menuList = menuService.selectList(null);
		for (SysMenuEntity sysMenuEntity : menuList) {
			SysMenuEntity parentMenuEntity = menuService.selectById(sysMenuEntity.getParentId());
			if (parentMenuEntity != null) {
				sysMenuEntity.setParentName(parentMenuEntity.getName());
			}
		}
		
		return menuList;
	}
	
	/**
	 * 选择菜单(添加、修改菜单)
	 */
	@GetMapping("/select")
	@RequiresPermissions("admin:menu:select")
	public R select() {
		//查询列表数据
		List<SysMenuEntity> menuList = menuService.queryNotButtonList();
		
		//添加顶级菜单
		SysMenuEntity root = new SysMenuEntity();
		root.setMenuId(0L);
		root.setName("一级菜单");
		root.setParentId(-1L);
		root.setOpen(true);
		menuList.add(root);
		
		return R.ok(menuList);
	}
	
	/**
	 * 菜单信息
	 */
	@GetMapping("/info/{menuId}")
	@RequiresPermissions("admin:menu:info")
	public R info(@PathVariable("menuId") Long menuId) {
		SysMenuEntity menu = menuService.selectById(menuId);
		return R.ok(menu);
	}
	
	/**
	 * 保存
	 */
	@SysLog("保存菜单")
	@PostMapping("/save")
	@RequiresPermissions("admin:menu:save")
	public R save(@RequestBody SysMenuEntity menu) {
		//数据校验
		verifyForm(menu);
		
		menuService.insert(menu);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@SysLog("修改菜单")
	@PostMapping("/update")
	@RequiresPermissions("admin:menu:update")
	public R update(@RequestBody SysMenuEntity menu) {
		//数据校验
		verifyForm(menu);
		
		menuService.updateById(menu);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@SysLog("删除菜单")
	@PostMapping("/delete/{menuId}")
	@RequiresPermissions("admin:menu:delete")
	public R delete(@PathVariable("menuId") long menuId) {
		if (menuId <= 31) {
			return R.error("系统菜单，不能删除");
		}
		
		//判断是否有子菜单或按钮
		List<SysMenuEntity> menuList = menuService.queryListParentId(menuId);
		if (menuList.size() > 0) {
			return R.error("请先删除子菜单或按钮");
		}
		
		menuService.delete(menuId);
		
		return R.ok();
	}
	
	/**
	 * 验证参数是否正确
	 */
	private void verifyForm(SysMenuEntity menu) {
		if (StringUtils.isBlank(menu.getName())) {
			throw new CTException("菜单名称不能为空");
		}
		
		if (menu.getParentId() == null) {
			throw new CTException("上级菜单不能为空");
		}
		
		//菜单
		if (menu.getType() == AdminConstant.MenuType.MENU.getValue()) {
			if (StringUtils.isBlank(menu.getUrl())) {
				throw new CTException("菜单URL不能为空");
			}
		}
		
		//上级菜单类型
		int parentType = AdminConstant.MenuType.CATALOG.getValue();
		if (menu.getParentId() != 0) {
			SysMenuEntity parentMenu = menuService.selectById(menu.getParentId());
			parentType = parentMenu.getType();
		}
		
		//目录、菜单
		if (menu.getType() == AdminConstant.MenuType.CATALOG.getValue() ||
				menu.getType() == AdminConstant.MenuType.MENU.getValue()) {
			if (parentType != AdminConstant.MenuType.CATALOG.getValue()) {
				throw new CTException("上级菜单只能为目录类型");
			}
			return;
		}
		
		//按钮
		if (menu.getType() == AdminConstant.MenuType.BUTTON.getValue()) {
			if (parentType != AdminConstant.MenuType.MENU.getValue()) {
				throw new CTException("上级菜单只能为菜单类型");
			}
			return;
		}
	}
}
