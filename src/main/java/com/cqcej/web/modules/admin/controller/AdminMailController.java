package com.cqcej.web.modules.admin.controller;

import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.R;
import com.cqcej.web.modules.admin.entity.AdminAgreementEntity;
import com.cqcej.web.modules.admin.entity.AdminSettingsEntity;
import com.cqcej.web.modules.admin.service.AdminAgreementService;
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
 * 邮箱
 *
 * @author Jia Min
 * @email Empty
 * @date 2018-09-10 15:02:31
 */
@RestController
@RequestMapping("admin/mail")
@Api(description = "Admin邮箱配置接口")
public class AdminMailController {
	@Autowired
	private AdminAgreementService adminAgreementService;
	@Autowired
	private AdminSettingsService adminSettingsService;
	
	/**
	 * 列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("admin:mail:list")
	@ApiOperation("邮箱模板列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "第几页，从1开始", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "分页大小", defaultValue = "10", required = true, dataType = "int", paramType = "query")
	})
	
	public R<PageUtils<AdminAgreementEntity>> list(@ApiIgnore @RequestParam Map<String, Object> params) {
		PageUtils<AdminAgreementEntity> data = adminAgreementService.mailList(params);
		return R.ok(data);
	}
	
	/**
	 * 信息
	 */
	@GetMapping("/info/{agreeId}")
	@RequiresPermissions("admin:mail:info")
	@ApiOperation("单个邮件模板信息")
	@ApiImplicitParam(name = "agreeId", value = "模板Id", required = true, dataType = "long", paramType = "query")
	public R info(@PathVariable("agreeId") Long agreeId) {
		AdminAgreementEntity data = adminAgreementService.getById(agreeId);
		return R.ok(data);
	}
	
	/**
	 * 保存
	 */
	@PostMapping("/save")
	@RequiresPermissions("admin:mail:save")
	@ApiOperation("添加邮件模板")
	public R save(@RequestBody AdminAgreementEntity agreement) {
		try {
			return R.ok(adminAgreementService.saveMail(agreement));
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
	}
	
	/**
	 * 修改
	 */
	@PostMapping("/update")
	@RequiresPermissions("admin:mail:update")
	@ApiOperation("修改邮件模板")
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
	@RequiresPermissions("admin:mail:delete")
	@ApiOperation("删除邮件模板")
	@ApiImplicitParam(name = "agreeId", value = "模板Id", required = true, dataType = "long", paramType = "path")
	public R delete(@PathVariable("agreeId") Long agreeId) {
		try {
			return R.ok(adminAgreementService.deleteAgree(agreeId));
		} catch (Exception e) {
			e.printStackTrace();
			return R.error("系统繁忙");
		}
	}
	
	/**
	 * 保存
	 */
	@GetMapping("/mailSetting")
	@RequiresPermissions("admin:mail:mailSetting")
	@ApiOperation("邮箱配置")
	public R<AdminSettingsEntity> mailSetting() {
		String settings[] = {"mailServerAddress", "mailServerPort", "mailAccount", "mailPassword"};
		List<AdminSettingsEntity> data = adminSettingsService.findAll(settings);
		return R.ok(data);
	}
	
	/**
	 * 修改邮箱配置
	 */
	@PostMapping("/updateMail")
	@RequiresPermissions("admin:mail:updateMail")
	@ApiOperation("修改邮箱配置")
	@ApiImplicitParam(name = "map", value = "将页面显示的配置通过key:value方式全部传递", required = false, dataType = "string")
	public R updateMailSetting(@ApiIgnore @RequestBody Map<String, Object> map) {
		try {
			//调用系统设置接口的方法
			return R.ok(adminSettingsService.updateOtherSettings(map));
		} catch (Exception e) {
			e.printStackTrace();
			return R.error("系统繁忙");
		}
	}
}
























