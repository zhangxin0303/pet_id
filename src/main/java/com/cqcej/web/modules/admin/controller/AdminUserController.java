package com.cqcej.web.modules.admin.controller;

import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.R;
import com.cqcej.web.common.validator.ValidatorUtils;
import com.cqcej.web.modules.admin.entity.AdminAreaEntity;
import com.cqcej.web.modules.admin.entity.AdminUserEntity;
import com.cqcej.web.modules.admin.service.AdminUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 用户
 *
 * @author Jia Min
 * @email empty
 * @date 2018-07-23 15:19:56
 */
@RestController
@RequestMapping("admin/user")
@Api(description = "Admin用户管理接口")
public class AdminUserController extends AbstractController {
	@Autowired
	private AdminUserService userService;
	
	/**
	 * 列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("admin:user:list")
	@ApiOperation("用户列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "第几页，从1开始", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "分页大小", defaultValue = "10", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "forbiddenComment", value = "状态(0正常，1禁言)", dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "userId", value = "用户ID", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "nickname", value = "昵称", dataType = "string", paramType = "query")
	})
	public R<PageUtils<AdminUserEntity>> list(@RequestParam(required = false) Integer forbiddenComment,
	                                          @RequestParam(required = false) Long userId,
	                                          @RequestParam(required = false) String nickname,
	                                          @RequestParam Integer page,
	                                          @RequestParam Integer size
	) {
		PageUtils<AdminUserEntity> data = userService.getUserList(forbiddenComment, userId, nickname, page, size);
		
		return new R<>(data);
	}
	
	
	/**
	 * 查询省份
	 */
	@GetMapping("/province")
	@RequiresPermissions("admin:user:province")
	@ApiOperation("查询省份信息")
	public R<AdminAreaEntity> getProvince() {
		List<AdminAreaEntity> prov = userService.selectProvinces();
		return R.ok(prov);
	}
	
	/**
	 * 查询城市
	 */
	@GetMapping("/city/{provinceId}")
	@RequiresPermissions("admin:user:city")
	@ApiOperation("查询城市")
	@ApiImplicitParam(name = "provinceId", value = "省份ID", required = true, dataType = "int", paramType = "query")
	public R<AdminAreaEntity> getCity(@PathVariable Integer provinceId) {
		List<AdminAreaEntity> city = userService.selectCity(provinceId);
		return R.ok(city);
	}
	
	/**
	 * 查询区县信息
	 */
	@GetMapping("/area/{cityId}")
	@RequiresPermissions("admin:user:area")
	@ApiOperation("查询区县信息")
	@ApiImplicitParam(name = "cityId", value = "城市ID", required = true, dataType = "int", paramType = "query")
	public R<AdminAreaEntity> getArea(@PathVariable Integer cityId) {
		List<AdminAreaEntity> area = userService.selectArea(cityId);
		return R.ok(area);
	}
	
	
	/**
	 * 查看
	 */
	@GetMapping("/info/{userId}")
	@RequiresPermissions("admin:user:info")
	@ApiOperation("查看user信息")
	@ApiImplicitParam(name = "userId", value = "userId", required = true, dataType = "long", paramType = "query")
	public R<AdminUserEntity> info(@PathVariable("userId") Long userId) {
		AdminUserEntity user = userService.selectUserById(userId);
		return R.ok(user);
	}
//
//	/**
//	 * 统计
//	 */
//	@RequestMapping("/count")
//	@RequiresPermissions("admin:user:count")
//	public R count(@RequestBody UserEntity user) {
//		userService.insert(user);
//		return R.ok();
//	}
//		select count(*) from ct_user where status = 1  -- 当前在线人数
//   	select count(*) from ct_user where DATE_FORMAT(create_at,'%Y-%m-%d') = DATE_FORMAT(now(),'%Y-%m-%d')-- 今日注册人数
//		select count(*) from ct_user -- 累计注册人数
	
	
	/**
	 * 修改
	 */
	@PostMapping("/update")
	@RequiresPermissions("admin:user:update")
	@ApiOperation("修改user信息")
	@ApiImplicitParam(name = "user", value = "user信息", required = false)
	public R update(AdminUserEntity user) {
		ValidatorUtils.validateAdminEntity(user);
		try {
			return R.ok(userService.updateUserById(user));
		} catch (Exception e) {
			e.printStackTrace();
			return R.error("系统繁忙");
		}
		
	}
	
	/**
	 * 删除user
	 */
	@DeleteMapping("/delete/{userId}")
	@RequiresPermissions("admin:user:delete")
	@ApiOperation("删除user")
	@ApiImplicitParam(name = "userId", value = "userId", required = true)
	public R delete(@PathVariable("userId") Long userId) {
		try {
			return R.ok(userService.deleteUserById(userId));
		} catch (Exception e) {
			e.printStackTrace();
			return R.error("系统繁忙");
		}
	}
	
	/**
	 * 获取登录的用户信息(ct_sys_user)
	 */
	@GetMapping("/info")
	public R info() {
		return R.ok(getUser());
	}
}
