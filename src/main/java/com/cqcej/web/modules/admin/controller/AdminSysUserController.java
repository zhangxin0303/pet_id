package com.cqcej.web.modules.admin.controller;

import com.cqcej.web.common.annotation.SysLog;
import com.cqcej.web.common.utils.AdminConstant;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.PasswordCheck;
import com.cqcej.web.common.utils.R;
import com.cqcej.web.common.validator.ValidatorUtils;
import com.cqcej.web.common.validator.group.AddGroup;
import com.cqcej.web.common.validator.group.UpdateGroup;
import com.cqcej.web.modules.admin.entity.SysUserEntity;
import com.cqcej.web.modules.admin.form.PasswordForm;
import com.cqcej.web.modules.admin.service.UserRoleService;
import com.cqcej.web.modules.admin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2016年10月31日 上午10:40:10
 */
@RestController
@RequestMapping("/admin/sysuser")
@Api(description = "Admin系统用户接口")
public class AdminSysUserController extends AbstractController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRoleService userRoleService;
	
	
	/**
	 * 所有用户列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("admin:sysuser:list")
	public R list(@RequestParam Map<String, Object> params) {
		//只有超级管理员，才能查看所有管理员列表
		if (getUserId() != AdminConstant.SUPER_ADMIN) {
			params.put("createUserId", getUserId());
		}
		PageUtils page = userService.queryPage(params);
		
		return R.ok(page);
	}
	
	/**
	 * 获取登录的用户信息
	 */
	@GetMapping("/info")
	public R info() {
		return R.ok(getUser());
	}
	
	/**
	 * 修改登录用户密码
	 */
	@SysLog("修改密码")
	@PostMapping("/password")
	@ApiOperation("修改密码")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "password", value = "密码", paramType = "string"),
			@ApiImplicitParam(name = "newPassword", value = "新密码", paramType = "string")
	})
	public R password(@ApiIgnore @RequestBody PasswordForm form) {
		PasswordCheck.check(form.getNewPassword());
		SysUserEntity user = getUser();
		//sha256加密
		String password = new Sha256Hash(form.getPassword(), user.getSalt()).toHex();
		
		//sha256加密
		String newPassword = new Sha256Hash(form.getNewPassword(), user.getSalt()).toHex();
		
		//更新密码
		boolean flag = userService.updatePassword(user.getUserId(), password, newPassword);
		if (!flag) {
			return R.error("原密码不正确");
		}
		return R.ok("操作成功");
	}
	
	/**
	 * 用户信息
	 */
	@GetMapping("/info/{userId}")
	@RequiresPermissions("admin:sysuser:info")
	public R info(@PathVariable("userId") Long userId) {
		SysUserEntity sysuser = userService.selectById(userId);
		
		//获取用户所属的角色列表
		List<Long> roleIdList = userRoleService.queryRoleIdList(userId);
		sysuser.setRoleIdList(roleIdList);
		
		return R.ok(sysuser);
	}
	
	/**
	 * 保存用户
	 */
	@SysLog("保存用户")
	@PostMapping("/save")
	@RequiresPermissions("admin:sysuser:save")
	@ApiOperation("保存用户")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "username", required = true, value = "用户名", paramType = "string"),
			@ApiImplicitParam(name = "password", required = true, value = "密码", paramType = "string"),
			@ApiImplicitParam(name = "email", required = true, value = "邮箱", paramType = "string"),
			@ApiImplicitParam(name = "mobile", required = true, value = "手机号", paramType = "string"),
			@ApiImplicitParam(name = "roleIdList", required = true, value = "角色ID(可多个)", paramType = "long")
	})
	public R save(@ApiIgnore @RequestBody SysUserEntity sysuser) {
		try {
			ValidatorUtils.validateAdminEntity(sysuser, AddGroup.class);
			sysuser.setCreateUserId(getUserId());
			userService.save(sysuser);
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
		return R.ok("操作成功");
	}
	
	/**
	 * 修改用户
	 */
	@SysLog("修改用户")
	@PostMapping("/update")
	@RequiresPermissions("admin:sysuser:update")
	public R update(@RequestBody SysUserEntity sysuser) {
		ValidatorUtils.validateAdminEntity(sysuser, UpdateGroup.class);
		sysuser.setCreateUserId(getUserId());
		userService.update(sysuser);
		return R.ok();
	}
	
	/**
	 * 删除用户
	 */
	@SysLog("删除用户")
	@DeleteMapping("/delete")
	@RequiresPermissions("admin:sysuser:delete")
	@ApiOperation("删除用户")
	@ApiImplicitParam(name = "userIds", value = "多(单)个用户Id", paramType = "long")
	public R delete(@RequestBody Long[] userIds) {
		if (ArrayUtils.contains(userIds, 1L)) {
			return R.error("系统管理员不能删除");
		}
		if (ArrayUtils.contains(userIds, getUserId())) {
			return R.error("当前用户不能删除");
		}
		userService.deleteBatch(userIds);
		return R.ok();
	}
}


