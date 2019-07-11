package com.cqcej.web.modules.admin.controller;

import com.cqcej.web.common.annotation.SysLog;
import com.cqcej.web.common.utils.AdminConstant;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.R;
import com.cqcej.web.modules.admin.entity.AdminSysRoleEntity;
import com.cqcej.web.modules.admin.entity.userRoleMenu.SetMenuEntity;
import com.cqcej.web.modules.admin.entity.userRoleMenu.SetRoleEntity;
import com.cqcej.web.modules.admin.service.AdminSysRoleService;
import com.cqcej.web.modules.admin.service.RoleMenuService;
import com.cqcej.web.modules.admin.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * 角色管理
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2016年11月8日 下午2:18:33
 */
@RestController
@RequestMapping("/admin/role")
@Api(description = "Admin角色管理接口")
public class AdminRoleController extends AbstractController {
	@Autowired
	private AdminSysRoleService adminSysRoleService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private RoleMenuService roleMenuService;

//	/**
//	 * 角色列表
//	 */
//	@GetMapping("/list")
//	@RequiresPermissions("admin:role:list")
//	public R list(@RequestParam Map<String, Object> params) {
//		//如果不是超级管理员，则只查询自己创建的角色列表
//		if (getUserId() != AdminConstant.SUPER_ADMIN) {
//			params.put("createUserId", getUserId());
//		}
//
//		PageUtils page = roleService.queryPage(params);
//
//		return R.ok().put("page", page);
//	}
	
	/**
	 * 管理员列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("admin:role:list")
	@ApiOperation("管理员列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "页码", paramType = "int"),
			@ApiImplicitParam(name = "size", value = "条数", paramType = "int"),
			@ApiImplicitParam(name = "roleType", value = "角色类型", paramType = "string"),
			@ApiImplicitParam(name = "userName", value = "搜索角色", paramType = "string")
	})
	public R<PageUtils<AdminSysRoleEntity>> list(@RequestParam(required = false) String roleType,
	                                             @RequestParam(required = false) String userName,
	                                             @RequestParam Integer page,
	                                             @RequestParam Integer size) {
		//如果不是超级管理员，则只查询自己创建的角色列表
		Long userId = getUserId() == AdminConstant.SUPER_ADMIN ? null : getUserId();
		PageUtils<AdminSysRoleEntity> data = adminSysRoleService.getRoleList(roleType, userName, userId, page, size);
		return R.ok(data);
	}


//	/**
//	 * 角色列表
//	 */
//	@GetMapping("/select")
//	@RequiresPermissions("admin:role:select")
//	public R select() {
//		Map<String, Object> map = new HashMap<>();
//
//		//如果不是超级管理员，则只查询自己所拥有的角色列表
//		if (getUserId() != AdminConstant.SUPER_ADMIN) {
//			map.put("createUserId", getUserId());
//		}
//		List<SysRoleEntity> list = roleService.selectByMap(map);
//
//		return R.ok().put("list", list);
//	}
	
	/**
	 * 系统所有角色
	 */
	@GetMapping("/selectAll")
	@RequiresPermissions("admin:role:selectAll")
	@ApiOperation("系统所有角色列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "页码", paramType = "int"),
			@ApiImplicitParam(name = "size", value = "条数", paramType = "int"),
			@ApiImplicitParam(name = "roleName", value = "角色名", paramType = "string")
	})
	public R<PageUtils<SetRoleEntity>> selectAll(@RequestParam Integer page,
	                                             @RequestParam Integer size,
	                                             @RequestParam(required = false) String roleName) {
		PageUtils<SetRoleEntity> data = adminSysRoleService.selectAll(page, size, roleName);
		return R.ok(data);
	}
	
	/**
	 * 系统所有角色
	 */
	@GetMapping("/selectRoles")
	@RequiresPermissions("admin:role:selectAll")
	@ApiOperation("系统所有角色(id,name)")
	public R<SetRoleEntity> selectRoles() {
		List<SetRoleEntity> data = adminSysRoleService.selectRoles();
		return R.ok(data);
	}
	
	/**
	 * 用户角色
	 */
	@GetMapping("/userRole/{userId}")
	@RequiresPermissions("admin:role:userRole")
	@ApiOperation("用户对应的所有角色")
	public R<List<SetRoleEntity>> roleMenu(@PathVariable("userId") Long userId) {
		List<SetRoleEntity> data = adminSysRoleService.selectUserRole(userId);
		return R.ok(data);
	}
	
	
	/**
	 * 为用户分配角色
	 */
	@PostMapping("/saveRole")
	@RequiresPermissions("admin:role:saveRole")
	@ApiOperation("为用户分配角色")
	public R saveUserRoles(@RequestParam List<Long> roleIds, @RequestParam Long userId) {
		try {
			return R.ok(adminSysRoleService.saveUserRoles(roleIds, userId));
		} catch (Exception e) {
			e.printStackTrace();
			return R.error("系统繁忙");
		}
	}
	
	/**
	 * 查询所有菜单
	 */
	@GetMapping("/select")
	@RequiresPermissions("admin:role:select")
	@ApiOperation("查询所有菜单")
	public R<SetMenuEntity> select() {
		List<SetMenuEntity> list = adminSysRoleService.selectMenus();
		return R.ok(list);
	}
	
	/**
	 * 查询所有菜单
	 */
	@GetMapping("/roleMenu/{roleId}")
	@RequiresPermissions("admin:role:roleMenu")
	@ApiOperation("查询角色的所有菜单")
	public R<SetMenuEntity> selectRoleMenu(@PathVariable("roleId") Long roleId) {
		List<SetMenuEntity> list = adminSysRoleService.selectRoleMenus(roleId);
		return R.ok(list);
	}
	
	
	/**
	 * 为角色分配菜单
	 */
	@PostMapping("/saveMenu")
	@RequiresPermissions("admin:role:saveMenu")
	@ApiOperation("为角色分配菜单(修改角色)")
	public R saveRoleMenus(@RequestBody SetRoleEntity role) {
		try {
			return R.ok(adminSysRoleService.saveRoleMenus(role));
		} catch (Exception e) {
			e.printStackTrace();
			return R.error("系统繁忙");
		}
	}


//	/**
//	 * 角色信息
//	 */
//	@GetMapping("/info/{roleId}")
//	@RequiresPermissions("admin:role:info")
//	public R info(@PathVariable("roleId") Long roleId) {
//		SysRoleEntity role = roleService.selectById(roleId);
//
//		//查询角色对应的菜单
//		List<Long> menuIdList = roleMenuService.queryMenuIdList(roleId);
//		role.setMenuIdList(menuIdList);
//
//		return R.ok().put("role", role);
//	}


//	/**
//	 * 保存角色
//	 */
//	@SysLog("保存角色")
//	@PostMapping("/save")
//	@RequiresPermissions("admin:role:save")
//	public R save(@RequestBody SysRoleEntity role) {
//		ValidatorUtils.validateAdminEntity(role);
//
//		role.setCreateUserId(getUserId());
//		roleService.save(role);
//
//		return R.ok();
//	}
	
	/**
	 * 保存角色
	 */
	@SysLog("添加角色")
	@PostMapping("/save")
	@RequiresPermissions("admin:role:save")
	@ApiOperation("添加角色")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "roleName", value = "角色名", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "remark", value = "备注", dataType = "string", paramType = "query")
	})
	public R save(@ApiIgnore @RequestBody Map<String, Object> params) {
		params.put("createUserId", getUserId());//登陆信息中获取登陆的userId
		try {
			return R.ok(adminSysRoleService.saveRole(params));
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
	}

//	/**
//	 * 修改角色
//	 */
//	@SysLog("修改角色")
//	@PostMapping("/update")
//	@RequiresPermissions("admin:role:update")
//	public R update(@RequestBody SysRoleEntity role) {
//		ValidatorUtils.validateAdminEntity(role);
//
//		role.setCreateUserId(getUserId());
//		roleService.update(role);
//
//		return R.ok();
//	}

//	/**
//	 * 删除角色
//	 */
//	@SysLog("删除角色")
//	@PostMapping("/delete")
//	@RequiresPermissions("admin:role:delete")
//	public R delete(@RequestBody Long[] roleIds) {
//		roleService.deleteBatch(roleIds);
//
//		return R.ok();
//	}
	
	/**
	 * 删除角色
	 */
	@SysLog("删除角色")
	@DeleteMapping("/delete/{roleId}")
	@RequiresPermissions("admin:role:delete")
	@ApiOperation(" 删除角色")
	@ApiImplicitParam(name = "roleId", value = "角色Id", required = true, paramType = "long")
	public R delete(@PathVariable("roleId") Long roleId) {
		try {
			return R.ok(adminSysRoleService.deleteBySysRoleId(roleId));
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
	}

//	/**
//	 * 重置密码(移除功能)
//	 */
//	@SysLog("重置密码")
//	@PostMapping("/reset")
//	@RequiresPermissions("admin:role:reset")
//	@ApiOperation("重置密码(移除功能)")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "userId", value = "用户ID", required = true, paramType = "long"),
//			@ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "string")
//	})
//	public R reset(@RequestParam Long userId, @RequestParam String password) {
//		try {
//			return R.ok(adminSysRoleService.reset(userId, password));
//		} catch (Exception e) {
//			return R.error("系统繁忙");
//		}
//	}
	
	/**
	 * 禁用管理员
	 */
	@SysLog("禁(启)用管理员")
	@GetMapping("/stopUser/{userId}/{status}")
	@RequiresPermissions("admin:role:stopUser")
	@ApiOperation("禁(启)用管理员")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "管理员ID", required = true, paramType = "long"),
			@ApiImplicitParam(name = "status", value = "状态0：禁用1：正常", required = true, paramType = "int")
	})
	public R stopUser(@PathVariable("userId") Long userId, @PathVariable("status") Integer status) {
		try {
			return R.ok(adminSysRoleService.stopUser(userId, status));
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
	}
	
	/**
	 * 禁用管理员
	 */
	@SysLog("禁(启)用角色")
	@GetMapping("/stopRole/{roleId}/{status}")
	@RequiresPermissions("admin:role:stopUser")
	@ApiOperation("禁(启)用角色")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "roleId", value = "角色ID", required = true, paramType = "long"),
			@ApiImplicitParam(name = "status", value = "状态0：禁用1：正常", required = true, paramType = "int")
	})
	public R stopRole(@PathVariable("roleId") Long roleId, @PathVariable("status") Integer status) {
		try {
			return R.ok(adminSysRoleService.stopRole(roleId, status));
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
	}
}
