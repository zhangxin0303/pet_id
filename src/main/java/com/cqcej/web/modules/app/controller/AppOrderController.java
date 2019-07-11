package com.cqcej.web.modules.app.controller;


import com.cqcej.web.modules.app.annotation.Login;
import com.cqcej.web.modules.app.entity.AppServiceOrderEntity;
import com.cqcej.web.modules.app.entity.AppServiceOrderStatisticsEntity;
import com.cqcej.web.modules.app.entity.AppWorkerOrderEntity;
import com.cqcej.web.modules.app.form.RefundForm;
import com.cqcej.web.modules.app.form.ServiceCommentForm;
import com.cqcej.web.modules.app.interceptor.AuthorizationInterceptor;
import com.cqcej.web.modules.app.service.AppMechanismCommentService;
import com.cqcej.web.modules.app.service.AppServiceOrderService;
import com.cqcej.web.modules.app.service.AppWorkerService;
import com.cqcej.web.modules.app.utils.AppConstant;
import com.cqcej.web.modules.app.utils.AppPage;
import com.cqcej.web.modules.app.utils.AppVerification;
import com.cqcej.web.modules.app.utils.BaseResponse;
import com.cqcej.web.modules.common.entity.ServiceOrderStatisticsDataEntity;
import com.cqcej.web.modules.common.entity.ServiceOrderStatisticsDetailEntity;
import com.cqcej.web.modules.common.entity.TrajectoryEntity;
import com.cqcej.web.modules.common.entity.WorkerEntity;
import com.cqcej.web.modules.common.service.SysSettingService;
import com.cqcej.web.modules.common.service.TrajectoryService;
import com.cqcej.web.modules.common.service.WorkerIncomeLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-07-26 16:00
 */
@Login
@RestController
@RequestMapping("/app/service/order")
@Api(description = "App订单")
public class AppOrderController {
	private final AppMechanismCommentService mechanismCommentService;
	private final AppServiceOrderService serviceOrderService;
	private AppWorkerService appWorkerService;
	private TrajectoryService trajectoryService;
	private WorkerIncomeLogService workerIncomeLogService;
	private SysSettingService sysSettingService;
	
	@Autowired
	public AppOrderController(AppMechanismCommentService mechanismCommentService,
	                          AppServiceOrderService serviceOrderService,
	                          AppWorkerService appWorkerService,
	                          TrajectoryService trajectoryService,
	                          WorkerIncomeLogService workerIncomeLogService,
	                          SysSettingService sysSettingService) {
		this.mechanismCommentService = mechanismCommentService;
		this.serviceOrderService = serviceOrderService;
		this.appWorkerService = appWorkerService;
		this.trajectoryService = trajectoryService;
		this.workerIncomeLogService = workerIncomeLogService;
		this.sysSettingService = sysSettingService;
	}
	
	@GetMapping("/{status}")
	@ApiOperation("获取用户订单")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "status", value = "状态，0全部，1待付款，2待使用，3待评价，4已完成", allowableValues = "1, 2, 3", dataType = "long", paramType = "path"),
			@ApiImplicitParam(name = "page", value = "当前页", defaultValue = "1", dataType = "long", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "分页大小", dataType = "long", paramType = "query")
	})
	public BaseResponse<AppPage<AppServiceOrderEntity>> getUserServiceOrder(HttpServletRequest request, @PathVariable("status") Integer status, Integer page, Integer size) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		AppPage<AppServiceOrderEntity> appPage = serviceOrderService.getUserServiceOrder(userId, status, page, size);
		return new BaseResponse<>(appPage);
	}
	
	@PostMapping("comment/{orderId}")
	@ApiOperation("评价订单")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orderId", value = "订单ID", dataType = "long", paramType = "path")
	})
	public BaseResponse<Boolean> serviceComment(HttpServletRequest request, @RequestBody ServiceCommentForm form, @PathVariable("orderId") long orderId) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		boolean result = mechanismCommentService.comment(userId, form, orderId);
		BaseResponse<Boolean> response = new BaseResponse<>(result);
		if (!result) {
			response.setMessage("订单已评价！");
		}
		return response;
	}
	
	@GetMapping("reservation/{type}")
	@ApiOperation("获取用户预约订单")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "type", value = "预约类型(1诊所2美容3健康)", allowableValues = "1, 2, 3", dataType = "long", paramType = "path"),
			@ApiImplicitParam(name = "page", value = "当前页", defaultValue = "1", dataType = "long", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "分页大小", dataType = "long", paramType = "query")
	})
	public BaseResponse<AppPage<AppServiceOrderEntity>> getUserReservationOrder(HttpServletRequest request, @PathVariable("type") Integer type, Integer page, Integer size) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		AppPage<AppServiceOrderEntity> appPage = serviceOrderService.getUserReservation(userId, type, page, size);
		return new BaseResponse<>(appPage);
	}
	
	@GetMapping("type/{type}")
	@ApiOperation("根据类型获取订单")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "type", value = "类型，为0获取全部", dataType = "long", paramType = "path"),
			@ApiImplicitParam(name = "status", value = "状态，-1表示全部状态", defaultValue = "-1", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "app", value = "客户端分类", defaultValue = "main", allowableValues = "main, service", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "page", value = "当前页", defaultValue = "1", dataType = "long", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "分页大小", dataType = "long", paramType = "query")
	})
	public BaseResponse<AppPage<AppWorkerOrderEntity>> getServiceOrderByType(HttpServletRequest request, @PathVariable("type") Integer type, String status, String app, Integer page, Integer size) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		AppPage<AppWorkerOrderEntity> appPage = serviceOrderService.getServiceOrderByType(userId, type, status, app, page, size);
		return new BaseResponse<>(appPage);
	}
	
	@GetMapping("statistics/{workerId}")
	@ApiOperation("获取接送/遛狗订单数据")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "workerId", value = "工作者id", dataType = "long", paramType = "path"),
	})
	public BaseResponse<AppServiceOrderStatisticsEntity> getServiceOrderStatistics(@PathVariable("workerId") Long workerId) {
		AppServiceOrderStatisticsEntity data = serviceOrderService.getServiceOrderStatistics(workerId);
		return new BaseResponse<>(data);
	}
	
	@PostMapping("status/{orderId}")
	@ApiOperation("更新订单状态")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orderId", value = "订单id", dataType = "long", paramType = "path"),
			@ApiImplicitParam(name = "app", value = "客户端分类", defaultValue = "main", allowableValues = "main, service", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "status", value = "状态", dataType = "int", paramType = "form")
	})
	public BaseResponse<Boolean> updateStatus(HttpServletRequest request, @PathVariable("orderId") Long orderId, String app, Integer status) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		if (AppConstant.ClientService.equals(app)) {
			WorkerEntity worker = appWorkerService.getWorkerDetailWithUserId(userId);
			userId = worker.getWorkerId();
		}
		Boolean result = serviceOrderService.updateStatus(userId, app, orderId, status);
		return new BaseResponse<>(result);
	}
	
	@GetMapping("detail/{orderId}")
	@ApiOperation("订单详情")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orderId", value = "订单id", dataType = "long", paramType = "path")
	})
	@Login(allowAnonymous = true)
	public BaseResponse<AppWorkerOrderEntity> orderDetail(@PathVariable("orderId") Long orderId) {
		AppWorkerOrderEntity order = serviceOrderService.getServiceOrderDetail(orderId);
		return new BaseResponse<>(order);
	}
	
	@GetMapping("detail")
	@ApiOperation("订单详情")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "obj", value = "订单号或预支付id", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "type", value = "支付方式，微信或支付宝", allowableValues = "wechat,alipay", dataType = "string", paramType = "query")
	})
	@Login(allowAnonymous = true)
	public BaseResponse<AppWorkerOrderEntity> detailByOrderNo(String obj, String type) {
		AppWorkerOrderEntity order = serviceOrderService.getServiceOrderDetail(obj, type);
		return new BaseResponse<>(order);
	}
	
	@PostMapping("trajectory/{orderId}")
	@ApiOperation("设置遛狗距离和轨迹，同时遛狗结束")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orderId", value = "订单id", dataType = "long", paramType = "path"),
			@ApiImplicitParam(name = "distance", value = "遛狗距离", dataType = "float", paramType = "form"),
			@ApiImplicitParam(name = "trajectory", value = "轨迹坐标，xxx:xxx,xxx:xxx", dataType = "string", paramType = "form"),
	})
	public BaseResponse<Boolean> updateWalkDogTrajectory(@PathVariable("orderId") Long orderId, Float distance, String trajectory) {
		TrajectoryEntity entity = new TrajectoryEntity();
		entity.setCreateAt(new Date());
		entity.setOrderId(orderId);
		entity.setDistance(new BigDecimal(distance));
		entity.setTrajectory(trajectory);
		
		return new BaseResponse<>(trajectoryService.insert(entity));
	}
	
	@GetMapping("statistics/data")
	@ApiOperation("订单统计基础数据")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "time", value = "统计时间，格式yyyy-MM", dataType = "string", paramType = "query")
	})
	public BaseResponse<ServiceOrderStatisticsDataEntity> getStatisticsDate(HttpServletRequest request, String time) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		ServiceOrderStatisticsDataEntity result = workerIncomeLogService.getServiceOrderStatisticsDate(userId, time);
		return new BaseResponse<>(result);
	}
	
	@GetMapping("statistics/detail")
	@ApiOperation("订单统计基础数据")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "time", value = "统计时间，格式yyyy-MM", dataType = "string", paramType = "query")
	})
	public BaseResponse<List<ServiceOrderStatisticsDetailEntity>> getStatisticsDetail(HttpServletRequest request, String time) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		
		List<ServiceOrderStatisticsDetailEntity> list = workerIncomeLogService.getServiceOrderStatisticsDetail(userId, time);
		
		return new BaseResponse<>(list);
	}
	
	@PutMapping("refund")
	@ApiOperation("订单退款申请")
	@ApiImplicitParams({
	
	})
	public BaseResponse<Boolean> refund(HttpServletRequest request, @RequestBody RefundForm form) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		boolean result = serviceOrderService.refund(userId, form);
		return new BaseResponse<>(result);
	}
	
	
	//-------------------------------------------------商家端(jm)-----------------------------------------------------
	@GetMapping("/mech/today")
	@ApiOperation("今日订单(订单明细)")
	@Login
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "当前页", defaultValue = "1", dataType = "long", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "分页大小", dataType = "long", paramType = "query"),
			@ApiImplicitParam(name = "orderStatus", value = "订单状态", dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "mechId", value = "商户Id", dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "today", value = "查询今日传yes,历史订单传no", dataType = "int", paramType = "query")
	})
	public BaseResponse<AppPage<AppServiceOrderEntity>> todayOrder(HttpServletRequest request,
	                                                               Integer page, Integer size,Integer orderStatus,Long mechId,String today) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		AppVerification.check(userId,mechId);
		AppPage<AppServiceOrderEntity> appPage = serviceOrderService.getTodayOrder(today,orderStatus,mechId, page, size);
		return new BaseResponse<>(appPage);
	}
	
	@GetMapping("/mech/detail/{orderId}/{mechId}")
	@ApiOperation("订单详情")
	@Login
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orderId", value = "订单Id", required = true, dataType = "long", paramType = "path"),
			@ApiImplicitParam(name = "mechId", value = "商户Id", required = true, dataType = "long", paramType = "path")
	})
	public BaseResponse<AppServiceOrderEntity> getMechOrderDetail(HttpServletRequest request,
	                                                              @PathVariable("orderId") Long orderId,@PathVariable("mechId") Long mechId) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		AppVerification.check(userId,mechId);
		AppServiceOrderEntity data = serviceOrderService.getMechOrderDetail(orderId);
		return new BaseResponse<>(data);
	}
	
	@GetMapping("/mech/refund/list")
	@ApiOperation("退款订单列表")
	@Login
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "当前页", required = true, defaultValue = "1", dataType = "long", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "分页大小", required = true, dataType = "long", paramType = "query"),
			@ApiImplicitParam(name = "mechId", value = "商户Id", required = true, dataType = "long", paramType = "path")
	})
	public BaseResponse<AppPage<AppServiceOrderEntity>> getMechRefundOrderList(HttpServletRequest request,
	                                                              @RequestParam Integer page,
	                                                              @RequestParam Integer size,
	                                                              @RequestParam("mechId") Long mechId) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		AppVerification.check(userId,mechId);
		AppPage<AppServiceOrderEntity> data = serviceOrderService.getMechRefundOrderList(page,size,mechId);
		return new BaseResponse(data);
	}
	
	@GetMapping("/mech/refund/detail/{orderId}/{mechId}")
	@ApiOperation("退款订单详情")
	@Login
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orderId", value = "订单Id", required = true, dataType = "long", paramType = "path"),
			@ApiImplicitParam(name = "mechId", value = "商户Id", required = true, dataType = "long", paramType = "path")
	})
	public BaseResponse<AppServiceOrderEntity> getMechRefundOrderDetail(HttpServletRequest request,
	                                                                          @PathVariable("orderId") Long orderId,
	                                                                           @PathVariable("mechId") Long mechId) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		AppVerification.check(userId,mechId);
		AppServiceOrderEntity data = serviceOrderService.getMechRefundOrderDetail(orderId);
		return new BaseResponse(data);
	}
	
	@GetMapping("/mech/refund/reason")
	@ApiOperation("退款原因")
	public BaseResponse<String> getMechRefundReason() {
		return new BaseResponse(sysSettingService.findByKey("refundReason"));
	}
	
	/**
	 * 同意退款
	 */
	@GetMapping("/mech/refund/agree/{orderId}/{mechId}")
	@ApiOperation("同意退款")
	@Login
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orderId", value = "订单id", required = true, dataType = "long", paramType = "path"),
			@ApiImplicitParam(name = "mechId", value = "商户id", required = true, dataType = "long", paramType = "path")
	})
	public BaseResponse<Boolean> refund(HttpServletRequest request, @PathVariable Long orderId, @PathVariable Long mechId) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		AppVerification.check(userId,mechId);
		return new BaseResponse(serviceOrderService.agreeRefund(orderId,mechId));
	}
	
	/**
	 * 退款失败
	 */
	@GetMapping("/refuse/{orderId}/{mechId}")
	@ApiOperation("退款失败(驳回)")
	@Login
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orderId", value = "订单id", required = true, dataType = "long", paramType = "path"),
			@ApiImplicitParam(name = "mechId", value = "商户id", required = true, dataType = "long", paramType = "path")
	})
	public BaseResponse<Boolean> refuse(HttpServletRequest request, @PathVariable Long orderId, @PathVariable Long mechId) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		AppVerification.check(userId,mechId);
		return new BaseResponse(serviceOrderService.refuse(orderId,mechId));
	}
}
