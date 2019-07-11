package com.cqcej.web.modules.admin.controller;

import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.R;
import com.cqcej.web.modules.admin.entity.AdminPaymentEntity;
import com.cqcej.web.modules.admin.service.AdminPaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 支付方式
 *
 * @author Jia Min
 * @email Empty
 * @date 2018-09-28 13:50:38
 */
@RestController
@RequestMapping("admin/payment")
@Api(description = "Admin支付配置接口")
public class AdminPaymentController {
	@Autowired
	private AdminPaymentService adminPaymentService;
	
	/**
	 * 列表
	 */
	@GetMapping("/list")
	@ApiOperation("列表信息")
	@RequiresPermissions("admin:payment:list")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "int"),
			@ApiImplicitParam(name = "size", value = "条数", required = true, paramType = "int")
	})
	public R<AdminPaymentEntity> list(@RequestParam Integer page, @RequestParam Integer size) {
		PageUtils data = adminPaymentService.getList(page, size);
		return R.ok(data);
	}
	
	/**
	 * 信息
	 */
	@GetMapping("/info/{paymentId}")
	@RequiresPermissions("admin:payment:info")
	@ApiOperation("单个配置信息")
	public R info(@PathVariable("paymentId") Integer paymentId) {
		return R.ok(adminPaymentService.info(paymentId));
	}
	
	/**
	 * 保存
	 */
	@PostMapping("/save")
	@RequiresPermissions("admin:payment:save")
	public R save(@RequestBody AdminPaymentEntity payment) {
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@PostMapping("/update")
	@RequiresPermissions("admin:payment:update")
	@ApiOperation("修改配置信息")
	public R update(@RequestBody AdminPaymentEntity payment) {
		return R.ok(adminPaymentService.updatePay(payment));
	}
	
	/**
	 * 删除
	 */
	@DeleteMapping("/delete/{paymentId}")
	@RequiresPermissions("admin:payment:delete")
	@ApiOperation("删除配置信息")
	public R delete(@PathVariable("paymentId") Integer paymentId) {
		return R.ok(adminPaymentService.deletePay(paymentId));
	}
	
	/**
	 * 禁用(启用)
	 */
	@PostMapping("/forbidden")
	@RequiresPermissions("admin:payment:update")
	@ApiOperation("状态(0禁用1启用")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "paymentId", value = "配置ID", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "paymentStatus", value = "状态(0禁用1启用)", required = true, dataType = "int", paramType = "query")
	})
	public R forbidden(@RequestParam Integer paymentId, @RequestParam Integer paymentStatus) {
		
		return R.ok(adminPaymentService.forbidden(paymentId, paymentStatus));
	}
	
}
