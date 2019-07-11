package com.cqcej.web.modules.admin.controller;

import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.R;
import com.cqcej.web.modules.admin.entity.AdminWithdrawEntity;
import com.cqcej.web.modules.admin.service.AdminSettingsService;
import com.cqcej.web.modules.admin.service.AdminWithdrawService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
import java.util.Map;


/**
 * 提现申请
 *
 * @author Jia Min
 * @email Empty
 * @date 2018-08-17 13:58:16
 */
@RestController
@RequestMapping("admin/withdraw")
@Api(description = "Admin提现申请接口")
public class AdminWithdrawController {
	
	@Autowired
	private AdminWithdrawService adminWithdrawService;
	@Autowired
	private AdminSettingsService adminSettingsService;
	
	/**
	 * 列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("admin:withdraw:list")
	@ApiOperation("接送员提现列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "第几页，从1开始", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "分页大小", defaultValue = "10", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "status", value = "状态:0默认，1已提现，2失败", required = false, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "workerName", value = "姓名", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "beginAt", value = "开始时间", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "endAt", value = "结束时间", required = false, dataType = "string", paramType = "query")
	})
	public R<PageUtils<AdminWithdrawEntity>> list(@ApiIgnore @RequestParam Map<String, Object> params) {
		PageUtils<AdminWithdrawEntity> data = adminWithdrawService.list(params);
		return R.ok(data);
	}
	
	
	/**
	 * 商户提现列表
	 */
	@GetMapping("/info")
	@RequiresPermissions("admin:withdraw:info")
	@ApiOperation("商户提现列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "第几页，从1开始", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "分页大小", defaultValue = "10", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "status", value = "状态:0默认，1已提现，2失败", required = false, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "mechanismName", value = "商户名", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "beginAt", value = "开始时间", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "endAt", value = "结束时间", required = false, dataType = "string", paramType = "query")
	})
	public R<PageUtils<AdminWithdrawEntity>> info(@ApiIgnore @RequestParam Map<String, Object> map) {
		PageUtils<AdminWithdrawEntity> data = adminWithdrawService.mechanList(map);
		return R.ok(data);
	}
	
	/**
	 * 保存
	 */
	@PostMapping("/save")
	@RequiresPermissions("admin:withdraw:save")
	public R save(@RequestParam AdminWithdrawEntity withdraw) {
		
		return R.ok();
	}
	
	/**
	 * 接送员申请提现
	 */
	@PostMapping("/update")
	@RequiresPermissions("admin:withdraw:update")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "withdrawId", value = "申请提现ID", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "status", value = "状态(1同意/2驳回)", required = true, dataType = "int", paramType = "query")
	})
	@ApiOperation("接送员申请提现:1同意/2驳回(需判断是否为'待审核'状态)")
	public R update(@RequestParam Long withdrawId, @RequestParam Integer status) {
		try {
			//提现信息
			AdminWithdrawEntity withdrawEntity = adminWithdrawService.selectAmountMessage(withdrawId);
			if (withdrawEntity == null || withdrawEntity.getStatus() != 0) {//非待审核状态
				return R.error("当前状态不能执行此操作");
			}
			if (status == 1) {//同意提现,判断最低提现额度
				if (withdrawEntity.getBalance().compareTo(withdrawEntity.getAmount()) == -1) {//余额小于提现金额
					return R.error("余额不足");
				}
				//最低提现额度
				BigDecimal minWithdrawTrans = new BigDecimal(adminSettingsService.findSettingByKey("minWithdrawTrans"));
				if (withdrawEntity.getAmount().compareTo(minWithdrawTrans) == -1) {//提现金额小于最低提现额度
					return R.error("提现金额不能小于最低额度:" + minWithdrawTrans);
				}
			}
			return R.ok(adminWithdrawService.updateWithdraw(withdrawId, status));
		} catch (Exception e) {
			e.printStackTrace();
			return R.error("系统繁忙");
		}
		
	}
	
	/**
	 * 商户员申请提现
	 */
	@PostMapping("/mechanUpdate")
	@RequiresPermissions("admin:withdraw:mechanUpdate")
	@ApiImplicitParams({
			
			@ApiImplicitParam(name = "withdrawId", value = "申请提现ID", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "status", value = "状态(1同意/2驳回)", required = true, dataType = "int", paramType = "query")
	})
	@ApiOperation("商户申请提现:1同意/2驳回(需判断是否为'待审核'状态)")
	public R mechanUpdate(@RequestParam Long withdrawId, @RequestParam Integer status) {
		try {
			//提现信息
			AdminWithdrawEntity withdrawEntity = adminWithdrawService.selectAmountMessage(withdrawId);
			if (withdrawEntity == null || withdrawEntity.getStatus() != 0) {//非待审核状态
				return R.error("当前状态不能执行此操作");
			}
			if (status == 1) {//同意提现,判断最低提现额度
				if (withdrawEntity.getBalance().compareTo(withdrawEntity.getAmount()) == -1) {//余额小于提现金额
					return R.error("余额不足");
				}
				//最低提现额度
				BigDecimal minWithdrawBusiness = new BigDecimal(adminSettingsService.findSettingByKey("minWithdrawBusiness"));
				if (withdrawEntity.getAmount().compareTo(minWithdrawBusiness) == -1) {//提现金额小于最低提现额度
					return R.error("提现金额不能小于最低额度:" + minWithdrawBusiness);
				}
			}
			return R.ok(adminWithdrawService.updateWithdraw(withdrawId, status));
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
	}
	
	/**
	 * 删除
	 */
	@DeleteMapping("/delete/{withdrawId}")
	@RequiresPermissions("admin:withdraw:delete")
	public R delete(@PathVariable("withdrawId") Long withdrawId) {
		
		return R.ok();
	}
	
}
