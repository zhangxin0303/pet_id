package com.cqcej.web.modules.admin.controller;

import com.alibaba.fastjson.JSON;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.R;
import com.cqcej.web.modules.admin.entity.AdminMechanismEntity;
import com.cqcej.web.modules.admin.entity.mechanism.DetailEntity;
import com.cqcej.web.modules.admin.entity.mechanism.DoctorEntity;
import com.cqcej.web.modules.admin.entity.mechanism.MechanBasicEntity;
import com.cqcej.web.modules.admin.entity.mechanism.ServiceEntity;
import com.cqcej.web.modules.admin.service.AdminMechanismService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;


/**
 * 机构，包含诊所，美容院
 *
 * @author Jia Min
 * @email empty
 * @date 2018-07-23 10:36:31
 */
@RestController
@RequestMapping("admin/mechanism")
@Api(description = "Admin服务商户接口")
public class AdminMechanismController extends AbstractController {
	
	
	@Autowired
	private AdminMechanismService mechanismService;
	
	/**
	 * 列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("admin:mechanism:list")
	@ApiOperation("商户列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "第几页，从1开始", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "分页大小", defaultValue = "10", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "mechanismType", value = "机构类型（1诊所2美容院4健康）", required = false, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "status", value = "商家状态", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "mechanismName", value = "商户名称", required = false, dataType = "string", paramType = "query")
	})
	public R<PageUtils<AdminMechanismEntity>> getMechanismList(@RequestParam Integer page,
	                                                           @RequestParam Integer size,
	                                                           @RequestParam(required = false) Integer mechanismType,
	                                                           @RequestParam(required = false) Integer mechanismStatus,
	                                                           @RequestParam(required = false) String mechanismName
	) {
		
		PageUtils<AdminMechanismEntity> data = mechanismService.getMechanismList(mechanismType, mechanismStatus, mechanismName, page, size);
		return new R<>(data);
	}
	
	/**
	 * 店内医师列表
	 */
	@GetMapping("/doctorList")
	@RequiresPermissions("admin:mechanism:doctorList")
	@ApiOperation("店内医师列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "第几页，从1开始", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "分页大小", defaultValue = "10", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "mechanismId", value = "商户ID", required = true, dataType = "long", paramType = "query")
	})
	public R<PageUtils<DoctorEntity>> doctorList(@RequestParam Integer page, @RequestParam Integer size, @RequestParam Long mechanismId) {
		PageUtils<DoctorEntity> data = mechanismService.doctorList(page, size, mechanismId);
		return new R<>(data);
	}
	
	/**
	 * 修改医师信息
	 */
	@PostMapping("/updateDoctor")
	@RequiresPermissions("admin:mechanism:updateDoctor")
	@ApiOperation("修改医师信息")
	public R updateDoctor(@RequestBody DoctorEntity doctor) {
		try {
			//update医师信息
			return R.ok(mechanismService.updateDoctor(doctor));
		} catch (Exception e) {
			e.printStackTrace();
			return R.error("系统繁忙");
		}
		
	}
	
	/**
	 * 店内服务列表
	 */
	@GetMapping("/serviceList")
	@RequiresPermissions("admin:mechanism:serviceList")
	@ApiOperation("店内服务列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "第几页，从1开始", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "分页大小", defaultValue = "10", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "mechanismId", value = "商户ID", required = true, dataType = "long", paramType = "query")
	})
	public R<PageUtils<ServiceEntity>> serviceList(@RequestParam Integer page, @RequestParam Integer size, @RequestParam Long mechanismId) {
		PageUtils<ServiceEntity> data = mechanismService.serviceList(page, size, mechanismId);
		return new R<>(data);
	}
	
	/**
	 * 修改服务信息
	 */
	@PostMapping("/updateService")
	@RequiresPermissions("admin:mechanism:updateService")
	@ApiOperation("修改服务信息")
	public R updateService(@RequestBody ServiceEntity service) {
		try {
			return R.ok(mechanismService.updateService(service));
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
	}
	
	
	/**
	 * 锁定
	 */
	@GetMapping("/lock/{mechanismId}")
	@RequiresPermissions("admin:mechanism:lock")
	@ApiOperation("锁定商户")
	@ApiImplicitParam(name = "mechanismId", value = "商户ID", required = true, dataType = "int", paramType = "query")
	public R lock(@PathVariable("mechanismId") Long mechanismId) {
		try {
			return R.ok(mechanismService.lockById(mechanismId));
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
	}
	
	/**
	 * 信息
	 */
	@GetMapping("/info/{mechanismId}")
	@RequiresPermissions("admin:mechanism:info")
	@ApiOperation("商户信息(用于编辑页面)")
	@ApiImplicitParam(name = "mechanismId", value = "商户ID", required = true, dataType = "int", paramType = "query")
	public R info(@PathVariable("mechanismId") Long mechanismId) {
		Map<String, Object> data = mechanismService.selectMechanismById(mechanismId);
		return R.ok(data);
	}
	
	/**
	 * 保存
	 */
	@PostMapping("/save")
	@RequiresPermissions("admin:mechanism:save")
	public R save(@RequestBody AdminMechanismEntity mechanism) {
		mechanismService.insert(mechanism);
		
		return R.ok();
	}
	
	/**
	 * 编辑商户页面
	 */
	@PostMapping("/update")
	@RequiresPermissions("admin:mechanism:update")
	@ApiOperation("编辑商户页面")
	
	public R update(@RequestBody @ApiIgnore Map<String, Object> params) {
		try {
			MechanBasicEntity basic = JSON.parseObject(JSON.toJSONString(params), MechanBasicEntity.class);
			DetailEntity detail = JSON.parseObject(JSON.toJSONString(params), DetailEntity.class);
			//修改商户信息
			return R.ok(mechanismService.updateMechanism(basic, detail));
		} catch (Exception e) {
			e.printStackTrace();
			return R.error("系统繁忙");
		}
		
	}
	
	/**
	 * 删除轮播图
	 */
	@DeleteMapping("/deleteImage")
	@RequiresPermissions("admin:mechanism:deleteImage")
	@ApiOperation("删除轮播图")
	@ApiImplicitParam(name = "imageUrl", value = "图片名字", required = true, dataType = "string", paramType = "query")
	public R deleteImage(@RequestParam String imageUrl) {
		try {
			//未删除服务器上的图片.只是删除数据库存储路径
			return R.ok(mechanismService.deleteImage(imageUrl));
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
	}
	
	/**
	 * 删除
	 */
	@DeleteMapping("/delete/{mechanismId}")
	@RequiresPermissions("admin:mechanism:delete")
	@ApiOperation("删除商户")
	@ApiImplicitParam(name = "mechanismId", value = "商户ID", required = true, dataType = "int", paramType = "query")
	public R delete(@PathVariable("mechanismId") Long mechanismId) {
		try {
			return R.ok(mechanismService.deleteMechanismById(mechanismId));
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
	}
}
