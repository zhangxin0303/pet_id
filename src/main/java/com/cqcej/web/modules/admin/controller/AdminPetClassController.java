package com.cqcej.web.modules.admin.controller;

import com.cqcej.web.common.utils.R;
import com.cqcej.web.modules.admin.entity.AdminPetClassEntity;
import com.cqcej.web.modules.admin.service.AdminPetClassService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;


/**
 * 宠物分类
 *
 * @author Jia Min
 * @email Empty
 * @date 2018-09-05 09:45:00
 */
@RestController
@RequestMapping("admin/petclass")
@Api(description = "Admin宠物种类设置接口")
public class AdminPetClassController {
	@Autowired
	private AdminPetClassService adminPetClassService;
	
	/**
	 * 列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("admin:petclass:list")
	@ApiOperation("列表信息")
	public R<AdminPetClassEntity> list() {
		List<AdminPetClassEntity> data = adminPetClassService.list();
		return R.ok(data);
	}
	
	/**
	 * 列表
	 */
	@GetMapping("/simple/list")
	@RequiresPermissions("admin:petclass:list")
	@ApiOperation("简单列表信息")
	public R<AdminPetClassEntity> simpleList() {
		List<AdminPetClassEntity> data = adminPetClassService.simpleList();
		return R.ok(data);
	}
	
	/**
	 * 信息
	 */
	@GetMapping("/info/{petClassId}")
	@RequiresPermissions("admin:petclass:info")
	public R info(@PathVariable("petClassId") Integer petClassId) {
		AdminPetClassEntity pet = adminPetClassService.info(petClassId);
		return R.ok(pet);
	}
	
	/**
	 * 添加种类
	 */
	@PostMapping("/save")
	@ApiOperation("添加种类")
	@RequiresPermissions("admin:petclass:save")
	@ApiImplicitParam(name = "className", value = "种类名称", required = true, paramType = "string")
	public R save(@RequestBody @ApiIgnore AdminPetClassEntity petClass) {
		try {
			return R.ok(adminPetClassService.save(petClass));
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
		
	}
	
	/**
	 * 添加宠物
	 */
	@PostMapping("/savepet")
	@ApiOperation("添加宠物")
	@RequiresPermissions("admin:petclass:savepet")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "parentId", value = "种类ID", required = true, paramType = "int"),
			@ApiImplicitParam(name = "className", value = "宠物名称", required = true, paramType = "string")
	})
	public R savePet(@RequestBody @ApiIgnore AdminPetClassEntity petClass) {
		try {
			return R.ok(adminPetClassService.savePet(petClass));
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
	}
	
	
	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperation("修改宠物")
	@RequiresPermissions("admin:petclass:update")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "petClassId", value = "ID", required = true, paramType = "int"),
			@ApiImplicitParam(name = "className", value = "宠物名称", required = true, paramType = "string")
	})
	public R update(@RequestBody @ApiIgnore AdminPetClassEntity petClass) {
		return R.ok(adminPetClassService.updatePet(petClass));
	}
	
	/**
	 * 删除
	 */
	@ApiOperation("删除宠物")
	@DeleteMapping("/delete/{petClassId}")
	@RequiresPermissions("admin:petclass:delete")
	public R delete(@PathVariable("petClassId") Integer petClassId) {
		try {
			return R.ok(adminPetClassService.deletePet(petClassId));
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
	}
}
