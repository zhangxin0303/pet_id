package com.cqcej.web.modules.admin.controller;

import com.cqcej.web.common.utils.R;
import com.cqcej.web.modules.admin.entity.AdminAgreementEntity;
import com.cqcej.web.modules.admin.service.AdminAgreementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 协议
 *
 * @author Jia Min
 * @email Empty
 * @date 2018-09-06 16:37:03
 */
@RestController
@RequestMapping("admin/agreement")
@Api(description = "Admin协议模板接口")
public class AdminAgreementController {
	@Autowired
	private AdminAgreementService adminAgreementService;
	
	/**
	 * 列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("admin:agreement:list")
	@ApiOperation("协议列表")
	public R<AdminAgreementEntity> list() {
		List<AdminAgreementEntity> data = adminAgreementService.list();
		return R.ok(data);
	}
	
	
	/**
	 * 信息
	 */
	@GetMapping("/info/{agreeId}")
	@RequiresPermissions("admin:agreement:info")
	@ApiOperation("单个协议")
	@ApiImplicitParam(name = "agreeId", value = "协议Id", required = true, dataType = "long", paramType = "query")
	public R<AdminAgreementEntity> info(@PathVariable("agreeId") Long agreeId) {
		AdminAgreementEntity data = adminAgreementService.getById(agreeId);
		return R.ok(data);
	}
	
	/**
	 * 保存
	 */
	@PostMapping("/save")
	@RequiresPermissions("admin:agreement:save")
	@ApiOperation("添加协议")
	public R save(@RequestBody AdminAgreementEntity agreement) {
		try {
			return R.ok(adminAgreementService.saveAgree(agreement));
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
	}
	
	/**
	 * 修改
	 */
	@PostMapping("/update")
	@RequiresPermissions("admin:agreement:update")
	@ApiOperation("修改协议")
	public R update(@RequestBody AdminAgreementEntity agreement) {
		try {
			return R.ok(adminAgreementService.updateAgree(agreement));
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
	}
	
	/**
	 * 删除
	 */
	@DeleteMapping("/delete/{agreeId}")
	@RequiresPermissions("admin:agreement:delete")
	@ApiOperation("删除协议")
	@ApiImplicitParam(name = "agreeId", value = "协议Id", required = true, dataType = "long", paramType = "query")
	public R delete(@PathVariable("agreeId") Long agreeId) {
		try {
			return R.ok(adminAgreementService.deleteAgree(agreeId));
		} catch (Exception e) {
			e.printStackTrace();
			return R.error("系统繁忙");
		}
	}
}
