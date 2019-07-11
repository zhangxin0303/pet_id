package com.cqcej.web.modules.admin.controller;

import com.cqcej.web.common.utils.R;
import com.cqcej.web.modules.admin.entity.AdminSettingsEntity;
import com.cqcej.web.modules.admin.service.AdminSettingsService;
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
 * 设置表
 *
 * @author Jia Min
 * @email Empty
 * @date 2018-08-14 16:08:29
 */
@RestController
@RequestMapping("admin/settings")
@Api(description = "Admin系统配置接口")
public class AdminSettingsController {
	@Autowired
	private AdminSettingsService adminSettingsService;
	
	/**
	 * 系统配置列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("admin:settings:list")
	@ApiOperation("平台计价配置")
	public R<List<AdminSettingsEntity>> list() {
		String settings[] = {"priceType", "priceFormerPersent", "priceSettled", "priceRealPersent", "pickUpType", "pickUpPersent", "pickUpSettled"};
		List<AdminSettingsEntity> data = adminSettingsService.findAll(settings);
		return R.ok(data);
	}
	
	/**
	 * 其他配置
	 */
	@GetMapping("/info")
	@RequiresPermissions("admin:settings:info")
	@ApiOperation("其他配置")
	public R<AdminSettingsEntity> info() {
		//只查询页面需要的参数。没有做全查
		String settings[] = {"validTime", "outdoorService", "notPayOrderClose", "orderDefaultComment", "doctorChatOdds",
				"minWithdrawBusiness", "minWithdrawTrans", "telephone", "pickUpPrice", "scoreRatioMoney", "refundReason", "scoreProgress"};
		List<AdminSettingsEntity> data = adminSettingsService.findAll(settings);
		return R.ok(data);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("admin:settings:save")
	public R save(@RequestParam AdminSettingsEntity settings) {
		
		return R.ok();
	}
	
	/**
	 * 修改平台计价
	 */
	@PostMapping("/update")
	@RequiresPermissions("admin:settings:update")
	@ApiOperation("修改平台计价")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "priceType", value = "商户计价方式(1.实收价百分比2.原价百分比3.每单固定金额)", required = true, dataType = "string"),
			@ApiImplicitParam(name = "priceResult", value = "商户计价结果", required = true, dataType = "string"),
			@ApiImplicitParam(name = "pickUpType", value = "接送计价方式(1.接送费百分比2.每单固定金额)", required = true, dataType = "string"),
			@ApiImplicitParam(name = "pickUpResult", value = "接送计价结果", required = true, dataType = "string"),
	})
	public R update(@ApiIgnore @RequestBody Map<String, String> map) {
		try {
			return R.ok(adminSettingsService.updatePriceSettings(map));
		} catch (Exception e) {
			e.printStackTrace();
			return R.error("系统繁忙");
		}
	}
	
	/**
	 * 修改其他配置
	 */
	@PostMapping("/updateOther")
	@RequiresPermissions("admin:settings:updateOther")
	@ApiOperation("修改其他配置")
	@ApiImplicitParam(name = "map", value = "将页面显示的配置通过json格式全部传递", required = false, dataType = "string")
	public R updateOther(@ApiIgnore @RequestBody Map<String, Object> map) {
		try {
			return R.ok(adminSettingsService.updateOtherSettings(map));
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
	}
	
	/**
	 * 修改(接送计费，退款原因，阶段积分)
	 */
//	@PostMapping("/update/json")
//	@RequiresPermissions("admin:settings:updateOther")
//	@ApiOperation("修改(接送计费，退款原因，阶段积分)")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "settingKey", value = "键", required = true, dataType = "string"),
//			@ApiImplicitParam(name = "settingValue", value = "值", required = true, dataType = "string")
//	})
	//未使用
	public R updateForJson(@ApiIgnore @RequestBody AdminSettingsEntity set) {
		try {
			//return R.ok(adminSettingsService.updateForJson(set));
			return R.error("系统繁忙");
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete/{settingKey}")
	@RequiresPermissions("admin:settings:delete")
	public R delete(@PathVariable("settingKey") String settingKey) {
		return R.ok();
	}
}
