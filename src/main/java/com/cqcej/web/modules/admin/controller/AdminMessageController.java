package com.cqcej.web.modules.admin.controller;

import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.R;
import com.cqcej.web.modules.admin.entity.AdminMessageEntity;
import com.cqcej.web.modules.admin.service.AdminMessageService;
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
 * 消息
 *
 * @author Jia Min
 * @email Empty
 * @date 2018-08-27 11:37:02
 */
@RestController
@RequestMapping("admin/message")
@Api(description = "Admin消息管理接口")
public class AdminMessageController {
	@Autowired
	private AdminMessageService adminMessageService;
	
	/**
	 * 消息通知
	 */
	@GetMapping("/list")
	@RequiresPermissions("admin:message:list")
	@ApiOperation("消息通知")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "第几页，从1开始", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "分页大小", defaultValue = "10", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "messageType", value = "消息类型（10商家消息，11用户消息，12接送员消息，20系统消息···）", dataType = "int", paramType = "query"),
	})
	public R<PageUtils<AdminMessageEntity>> list(@ApiIgnore @RequestParam Map<String, Object> params) {
		PageUtils<AdminMessageEntity> data = adminMessageService.list(params);
		return R.ok(data);
	}
	
	/**
	 * 查看
	 */
	@GetMapping("/info/{messageId}")
	@RequiresPermissions("admin:message:info")
	@ApiOperation("查看")
	public R<AdminMessageEntity> info(@PathVariable("messageId") Long messageId) {
		AdminMessageEntity message = adminMessageService.getMessageById(messageId);
		return R.ok(message);
	}
	
	/**
	 * 群发消息
	 */
	@PostMapping("/save")
	@ApiOperation("群发消息")
	@RequiresPermissions("admin:message:save")
	public R save(@RequestBody AdminMessageEntity message) {
		try {
			return R.ok(adminMessageService.saveMessage(message));
		} catch (Exception e) {
			e.printStackTrace();
			return R.error("系统繁忙");
		}
	}
	
	/**
	 * 修改
	 */
	@PostMapping("/update")
	@RequiresPermissions("admin:message:update")
	public R update(@RequestBody AdminMessageEntity message) {
		
		return R.ok();
	}
	
	/**
	 * 删除(批量or单独)
	 */
	@DeleteMapping("/delete")
	@RequiresPermissions("admin:message:delete")
	@ApiOperation("删除(批量or单独)")
	@ApiImplicitParam(name = "messageIds", value = "消息ID(删除多个用逗号隔开)", required = true, dataType = "long", paramType = "query")
	public R delete(@RequestParam List<Long> messageIds) {
		try {
			return R.ok(adminMessageService.deleteMessage(messageIds));
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
	}
}
