package com.cqcej.web.modules.admin.controller;

import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.R;
import com.cqcej.web.modules.admin.entity.AdminServiceOrderCommentEntity;
import com.cqcej.web.modules.admin.entity.AdminWorkerCommentEntity;
import com.cqcej.web.modules.admin.entity.AdminWorkerCommentResultEntity;
import com.cqcej.web.modules.admin.service.AdminWorkerCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


/**
 * 评论工作人员
 *
 * @author Jia Min
 * @email empty
 * @date 2018-07-11 14:39:18
 */
@RestController
@RequestMapping("/admin/workercomment")
@Api(description = "Admin人员评价接口")
public class AdminWorkerCommentController {
	@Autowired
	private AdminWorkerCommentService workerCommentService;
	
	
	/**
	 * 获取工作人员评价
	 */
	@GetMapping("/list")
	@RequiresPermissions("admin:workercomment:list")
	@ApiOperation("工作人员评价")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "workerType", value = "工作者类型，20医师21接送者22遛狗人员23美容师", dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "workerName", value = "人员名称", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "page", value = "第几页，从1开始", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "分页大小", defaultValue = "10", required = true, dataType = "int", paramType = "query")
	})
	public R<PageUtils<AdminWorkerCommentResultEntity>> getWorkerCommentResultList(@RequestParam(required = false) Integer workerType,
	                                                                               @RequestParam(required = false) String workerName,
	                                                                               @RequestParam Integer page,
	                                                                               @RequestParam Integer size) {
		PageUtils<AdminWorkerCommentResultEntity> data = workerCommentService.getWorkerCommentResultList(workerType, workerName, page, size);
		return new R<>(data);
	}
	
	/**
	 * 获取订单评价(查看)
	 */
	@GetMapping("/info")
	@RequiresPermissions("admin:workercomment:info")
	@ApiOperation("查看订单")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "workerId", value = "工作人员ID", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "commentLevel", value = "评论等级(1差评2中评3好评）", required = false, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "page", value = "第几页，从1开始", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "分页大小", defaultValue = "10", required = true, dataType = "int", paramType = "query"),
	})
	public R<PageUtils<AdminServiceOrderCommentEntity>> info(@RequestParam("workerId") Integer workerId,
	                                                         @RequestParam(required = false, value = "commentLevel") Integer commentLevel,
	                                                         @RequestParam Integer page,
	                                                         @RequestParam Integer size) {
		PageUtils<AdminServiceOrderCommentEntity> data = workerCommentService.getOrderCommentList(workerId, commentLevel, page, size);
		return new R(data);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("admin:workercomment:save")
	public R save(@RequestBody AdminWorkerCommentEntity workerComment) {
		workerCommentService.insert(workerComment);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("admin:workercomment:update")
	public R update(@RequestBody AdminWorkerCommentEntity workerComment) {
		workerCommentService.updateById(workerComment);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("admin:workercomment:delete")
	public R delete(@RequestBody Long[] commonIds) {
		workerCommentService.deleteBatchIds(Arrays.asList(commonIds));
		
		return R.ok();
	}
	
}
