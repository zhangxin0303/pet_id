package com.cqcej.web.modules.admin.controller;

import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.R;
import com.cqcej.web.modules.admin.entity.AdminServiceOrderEntity;
import com.cqcej.web.modules.admin.entity.order.OrderStatisticsEntity;
import com.cqcej.web.modules.admin.entity.order.PlatformStats;
import com.cqcej.web.modules.admin.service.AdminServiceOrderService;
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
 * 服务订单
 *
 * @author Jia Min
 * @email empty
 * @date 2018-07-24 14:09:29
 */
@RestController
@RequestMapping("admin/serviceorder")
@Api(description = "Admin服务订单接口")
public class AdminServiceOrderController {
	@Autowired
	private AdminServiceOrderService serviceOrderService;
	
	/**
	 * 订单列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("admin:serviceorder:list")
	@ApiOperation("订单列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "第几页，从1开始", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "分页大小", defaultValue = "10", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "userId", value = "用户ID(为用户管理-用户订单所用)", required = false, dataType = "long", paramType = "query"),
			@ApiImplicitParam(name = "orderSubtype", value = "订单子类型(11上门就诊，12线上预约，13聊天咨询，14电话咨询，20美容接送，31遛狗服务，32宠物寄养)", required = false, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "orderStatus", value = "订单状态", required = false, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "orderNo", value = "订单号", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "mechanismName", value = "商家名称", required = false, dataType = "string", paramType = "query")
	})
	public R<PageUtils<AdminServiceOrderEntity>> list(@ApiIgnore @RequestParam Map<String, Object> params) {
		PageUtils<AdminServiceOrderEntity> data = serviceOrderService.getServiceOrderList(params);
		return new R<>(data);
	}
	
	/**
	 * 订单详情
	 */
	@GetMapping("/info/{orderId}")
	@RequiresPermissions("admin:serviceorder:info")
	@ApiOperation("订单详情")
	@ApiImplicitParam(name = "orderId", value = "订单Id", required = true, dataType = "long", paramType = "query")
	public R info(@PathVariable("orderId") Long orderId) {
		Map<String, Object> data = serviceOrderService.selectOrderById(orderId);
		return R.ok(data);
	}
	
	/**
	 * 订单统计
	 */
	@GetMapping("/statistics")
	@RequiresPermissions("admin:serviceorder:statistics")
	@ApiOperation("订单统计")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "第几页，从1开始", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "分页大小", defaultValue = "10", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "searchDay", value = "查询时间(直接传天数)", dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "startDate", value = "开始时间", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "endDate", value = "结束时间", dataType = "string", paramType = "query"),
	})
	public R<PageUtils<OrderStatisticsEntity>> statistics(@ApiIgnore @RequestParam Map<String, Object> params) {
		PageUtils<OrderStatisticsEntity> data = serviceOrderService.statistics(params);
		return R.ok(data);
	}
	
	
	/**
	 * 平台(医疗、美容、健康)分成统计
	 */
	@GetMapping("/platformStats")
	@RequiresPermissions("admin:serviceorder:platformStats")
	@ApiOperation("平台(医疗、美容、健康)分成统计")
	public R<PlatformStats> platformStats() {
		PlatformStats p = serviceOrderService.platformStats();
		return R.ok(p);
	}
}




















