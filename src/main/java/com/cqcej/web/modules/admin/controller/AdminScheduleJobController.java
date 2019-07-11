package com.cqcej.web.modules.admin.controller;

import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.R;
import com.cqcej.web.modules.admin.entity.AdminScheduleJobEntity;
import com.cqcej.web.modules.admin.service.AdminScheduleJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 定时任务
 *
 * @author Jia Min
 * @email Empty
 * @date 2018-09-05 14:17:00
 */
@RestController
@RequestMapping("admin/schedulejob")
@Api(description = "Admin计划任务接口")
public class AdminScheduleJobController {
	@Autowired
	private AdminScheduleJobService adminScheduleJobService;
	
	/**
	 * 列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("admin:schedulejob:list")
	@ApiOperation("任务列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "第几页，从1开始", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "分页大小", defaultValue = "10", required = true, dataType = "int", paramType = "query")
	})
	public R<PageUtils<AdminScheduleJobEntity>> list(@RequestParam Integer page, @RequestParam Integer size) {
		PageUtils<AdminScheduleJobEntity> data = adminScheduleJobService.list(page, size);
		return R.ok(data);
	}
	
	/**
	 * 单个计划任务
	 */
	@GetMapping("/info/{jobId}")
	@RequiresPermissions("admin:schedulejob:info")
	@ApiOperation("单个计划任务")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "jobId", value = "任务Id", required = true, dataType = "long", paramType = "query"),
	})
	public R<AdminScheduleJobEntity> info(@PathVariable("jobId") Long jobId) {
		AdminScheduleJobEntity schedulejob = adminScheduleJobService.getSchedulejobById(jobId);
		return R.ok(schedulejob);
	}
	
	/**
	 *
	 */
	@GetMapping("/getCount")
	@RequiresPermissions("admin:schedulejob:getCount")
	@ApiOperation("已启用start数量/已禁用forbidden数量")
	public R getCount() {
		Map<String, Integer> data = adminScheduleJobService.getStartForbiddenCount();
		return R.ok(data);
	}
	
	/**
	 * 保存
	 */
	@PostMapping("/save")
	@RequiresPermissions("admin:schedulejob:save")
	@ApiOperation("新增任务")
	public R save(@RequestBody AdminScheduleJobEntity scheduleJob) {
		try {
			return R.ok(adminScheduleJobService.saveScheduleJob(scheduleJob));
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
	}
	
	/**
	 * 任务状态  0：正常  1：暂停
	 */
	@PostMapping("/forbidden")
	@RequiresPermissions("admin:schedulejob:forbidden")
	@ApiOperation("0：正常 1：暂停")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "jobId", value = "任务Id", required = true, dataType = "long", paramType = "query"),
			@ApiImplicitParam(name = "status", value = "状态", required = true, dataType = "int", paramType = "query")
	})
	public R updateStstus(@RequestParam Long jobId, @RequestParam Integer status) {
		try {
			return R.ok(adminScheduleJobService.updateStstus(jobId, status));
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
		
	}
	
	/**
	 * 修改
	 */
	@PostMapping("/update")
	@RequiresPermissions("admin:schedulejob:update")
	@ApiOperation("修改任务")
	public R update(@RequestBody AdminScheduleJobEntity scheduleJob) {
		try {
			return R.ok(adminScheduleJobService.updateScheduleJob(scheduleJob));
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
		
	}
	
	/**
	 * 删除
	 */
	@DeleteMapping("/delete/{jobId}")
	@RequiresPermissions("admin:schedulejob:delete")
	@ApiOperation("删除定时任务")
	@ApiImplicitParam(name = "jobId", value = "任务Id", required = true, dataType = "long", paramType = "query")
	public R delete(@PathVariable("jobId") Long jobId) {
		try {
			return R.ok(adminScheduleJobService.deleteSchedule(jobId));
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
	}
}
