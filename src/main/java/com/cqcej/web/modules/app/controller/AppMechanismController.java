package com.cqcej.web.modules.app.controller;


import com.cqcej.web.common.exception.CTException;
import com.cqcej.web.common.validator.ValidatorUtils;
import com.cqcej.web.modules.app.annotation.Login;
import com.cqcej.web.modules.app.entity.MechanismStatisticsEntity;
import com.cqcej.web.modules.app.entity.dto.MechMechanismDTO;
import com.cqcej.web.modules.app.entity.home.AppMechanismCommentEntity;
import com.cqcej.web.modules.app.entity.home.AppMechanismEntity;
import com.cqcej.web.modules.app.entity.home.AppMechanismServiceEntity;
import com.cqcej.web.modules.app.form.MechanismServiceForm;
import com.cqcej.web.modules.app.interceptor.AuthorizationInterceptor;
import com.cqcej.web.modules.app.service.AppMechanismCommentService;
import com.cqcej.web.modules.app.service.AppMechanismService;
import com.cqcej.web.modules.app.service.AppMechanismServiceService;
import com.cqcej.web.modules.app.utils.AppPage;
import com.cqcej.web.modules.app.utils.AppVerification;
import com.cqcej.web.modules.app.utils.BaseResponse;
import com.cqcej.web.modules.common.entity.MechanismServiceEntity;
import com.cqcej.web.modules.common.service.ServiceOrderService;
import com.cqcej.web.modules.common.service.WorkerIncomeLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-10 14:48
 */
@RestController
@RequestMapping("/app/mechanism")
@Api(description = "App机构，诊所，美容")
public class AppMechanismController extends AbstractController {
	@Autowired
	private AppMechanismCommentService appMechanismCommentService;
	
	@Autowired
	private AppMechanismService appMechanismService;
	
	@Autowired
	private AppMechanismServiceService appMechanismServiceService;
	
	@Autowired
	private WorkerIncomeLogService workerIncomeLogService;
	
	@Autowired
	private ServiceOrderService serviceOrderService;
	
	@GetMapping("recommend/{type}")
	@ApiOperation("获取推荐机构")
	@ApiImplicitParam(name = "type", value = "机构类型，1医疗2美容4健康", required = true, dataType = "int", paramType = "path")
	public BaseResponse<List<AppMechanismEntity>> getMechanismRecommend(@PathVariable("type") int type) {
		List<AppMechanismEntity> recommends = appMechanismService.getMechanismRecommend(type);
		return new BaseResponse<>(recommends);
	}
	
	/**
	 * 诊所列表
	 */
	@GetMapping("/list")
	@ApiOperation("诊所列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "petClassId", value = "宠物分类ID，如果为0，则筛选所有分类", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "sortType", value = "排序类型，0推荐诊所，1距离最近", defaultValue = "0", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "page", value = "第几页，从1开始", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "分页大小", defaultValue = "10", required = true, dataType = "int", paramType = "query")
	})
	public BaseResponse<AppPage<AppMechanismEntity>> getClinic(int petClassId, int sortType, int page, int size, @ApiIgnore double longitude, @ApiIgnore double latitude) {
		if (sortType != 0 && sortType != 1) {
			throw new CTException("参数错误");
		}
		
		AppPage<AppMechanismEntity> appPage = appMechanismService.getClinicList(petClassId, sortType, page, size, longitude, latitude);
		return new BaseResponse<>(appPage);
	}
	
	@GetMapping("beauty/newest")
	@ApiOperation("获取最新美容店")
	public BaseResponse<List<AppMechanismEntity>> getNewestBeautyStore() {
		List<AppMechanismEntity> newest = appMechanismService.getNewestBeautyStore();
		return new BaseResponse<>(newest);
	}
	
	@GetMapping("beauty/recommend")
	@ApiOperation("获取推荐美容店")
	public BaseResponse<List<AppMechanismEntity>> getRecommendBeautyStore() {
		List<AppMechanismEntity> recommends = appMechanismService.getRecommendBeautyStore();
		return new BaseResponse<>(recommends);
	}
	
	@GetMapping("/clinic/detail")
	@ApiOperation("诊所详情")
	@ApiImplicitParam(name = "clinicId", value = "诊所ID", required = true, dataType = "long", paramType = "query")
	@Login(allowAnonymous = true)
	public BaseResponse<AppMechanismEntity> getClinicDetail(HttpServletRequest request, long clinicId) {
		AppMechanismEntity entity = appMechanismService.getClinicDetail(request, clinicId);
		return new BaseResponse<>(entity);
	}
	
	@GetMapping("/clinic/services")
	@ApiOperation("诊所服务列表")
	@ApiImplicitParam(name = "clinicId", value = "诊所ID", required = true, dataType = "long", paramType = "query")
	public BaseResponse<LinkedHashMap<String, ArrayList<AppMechanismServiceEntity>>> getClinicServices(long clinicId) {
		LinkedHashMap<String, ArrayList<AppMechanismServiceEntity>> map = appMechanismServiceService.getClinicServices(clinicId);
		return new BaseResponse<>(map);
	}
	
	@GetMapping("/clinic/comments")
	@ApiOperation("诊所评论列表")
	@Login(allowAnonymous = true)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "clinicId", value = "诊所ID", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "page", value = "第几页，从1开始", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "分页大小", defaultValue = "10", required = true, dataType = "int", paramType = "query")
	})
	public BaseResponse<AppPage<AppMechanismCommentEntity>> getClinicComments(HttpServletRequest request, long clinicId, int page, int size) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		AppPage<AppMechanismCommentEntity> comments = appMechanismCommentService.getClinicComments(userId, clinicId, page, size);
		return new BaseResponse<>(comments);
	}
	
	@GetMapping("/services")
	@ApiOperation("获取机构服务信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "mechanismId", value = "机构ID", required = true, dataType = "long", paramType = "query"),
			@ApiImplicitParam(name = "type", value = "服务类型，如果为0，则获取全部服务", required = true, dataType = "int", paramType = "query"),
	})
	public BaseResponse<List<MechanismServiceEntity>> getMechanismServices(Long mechanismId, Integer type) {
		List<MechanismServiceEntity> data = appMechanismServiceService.getMechanismServiceByType(mechanismId, type);
		return new BaseResponse<>(data);
	}
	
	//////////////////////////////////////////////商家端/////////////////////////////////////////////////////////////////
	
	@GetMapping("/statistics/{mechanismId}")
	@ApiOperation("商家端用户首页统计")
	@Login
	public BaseResponse<MechanismStatisticsEntity> getMechanismStatistics(HttpServletRequest request, @PathVariable("mechanismId") Long mechanismId) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		MechanismStatisticsEntity entity = new MechanismStatisticsEntity();
		
		// 收入
		Map<String, BigDecimal> incomeLog = workerIncomeLogService.getMechanismIncomeStatistics(userId);
		entity.setTodayIncome(incomeLog.get("today"));
		entity.setHistoryIncome(incomeLog.get("history"));
		
		// 订单
		Map<String, Integer> orderLog = serviceOrderService.getMechanismStatistics(mechanismId);
		entity.setTodayOrderCount(orderLog.get("today"));
		entity.setHistoryOrderCount(orderLog.get("history"));
		entity.setRefundOrderCount(orderLog.get("refund"));
		
		return new BaseResponse<>(entity);
	}
	
	/**
	 * 店铺信息m
	 */
	@GetMapping("/info/{mechId}")
	@ApiOperation("店铺信息")
	@Deprecated
	@Login
	@ApiImplicitParams({
			@ApiImplicitParam(name = "mechId", value = "机构ID", required = true, dataType = "long", paramType = "path"),
	})
	public BaseResponse info(HttpServletRequest request, @PathVariable("mechId") Long mechId) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		AppVerification.check(userId, mechId);
		MechMechanismDTO data = appMechanismService.mechInfo(mechId);
		return new BaseResponse(data);
	}
	
	/**
	 * 编辑店铺m
	 */
	@PostMapping("/update")
	@ApiOperation("编辑店铺")
	@Login
	public BaseResponse update(HttpServletRequest request, @RequestBody MechMechanismDTO m) {
		//表单校验
		ValidatorUtils.validateAppEntity(m);
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		m.setUserId(userId);
		AppVerification.check(userId, m.getMechanismId());
		AppMechanismEntity data = null;
		//更新成功返回更新后数据，更新失败返回null
		if (appMechanismService.updateMech(m) > 0) {
			data = appMechanismService.getMechanismDetailWithUserId(m.getUserId());
		}
		return new BaseResponse(data);
	}
	
	/**
	 * 批量操作m
	 */
	@DeleteMapping("images/delete")
	@ApiOperation("批量操作(店铺图片)")
	@Login
	@ApiImplicitParams({
			@ApiImplicitParam(name = "imagesId", value = "图片Id(多个)", required = true, dataType = "long", paramType = "query"),
			@ApiImplicitParam(name = "mechId", value = "商户Id", required = true, dataType = "long", paramType = "query")
	})
	public BaseResponse<Boolean> deleteImages(HttpServletRequest request, @RequestParam Long mechId, @RequestParam String imagesId) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		AppVerification.check(userId, mechId);
		return new BaseResponse(appMechanismServiceService.deleteImages(imagesId) > 0 ? true : false);
	}
	
	/**
	 * 服务管理m
	 */
	@GetMapping("service/list")
	@ApiOperation("服务列表")
	@Login
	@ApiImplicitParams({
			@ApiImplicitParam(name = "mechId", value = "机构ID", required = true, dataType = "long", paramType = "query"),
			@ApiImplicitParam(name = "serviceType", value = "服务类型", required = true, dataType = "int", paramType = "query")
	})
	public BaseResponse<List<AppMechanismServiceEntity>> serviceList(HttpServletRequest request, @RequestParam Long mechId, @RequestParam Integer serviceType) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		AppVerification.check(userId, mechId);
		List<AppMechanismServiceEntity> data = appMechanismServiceService.servieList(mechId, serviceType);
		return new BaseResponse(data);
	}
	
	/**
	 * 添加服务m
	 */
	@PostMapping("service/update")
	@ApiOperation("添加(修改)服务")
	@Login
	public BaseResponse<Boolean> addService(HttpServletRequest request, @RequestBody MechanismServiceForm form) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		//表单校验
		ValidatorUtils.validateAppEntity(form);
		AppVerification.check(userId, form.getMechId());
		if (form.getMechServiceId() == null) {
			return new BaseResponse(appMechanismServiceService.addService(form) > 0 ? true : false);
		}
		return new BaseResponse(appMechanismServiceService.updateService(form) > 0 ? true : false);
	}
	
	/**
	 * 置顶服务m
	 */
	@PostMapping("service/top")
	@ApiOperation("置顶服务")
	public BaseResponse<Boolean> serviceTop(@RequestBody List<MechanismServiceEntity> list) {
		return new BaseResponse(appMechanismServiceService.updateBatchById(list));
	}
	
	/**
	 * 批量操作m
	 */
	@DeleteMapping("service/delete")
	@ApiOperation("批量操作(服务)")
	@Login
	@ApiImplicitParams({
			@ApiImplicitParam(name = "mechServiceId", value = "服务Id(多个用逗号隔开)", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "mechId", value = "商户Id", required = true, dataType = "long", paramType = "query")
	})
	public BaseResponse<Boolean> deleteService(HttpServletRequest request, @RequestParam Long mechId, @RequestParam String mechServiceId) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		AppVerification.check(userId, mechId);
		List<Long> list = new ArrayList<>();
		for (String s : mechServiceId.split(",")) {
			list.add(Long.valueOf(s));
		}
		return new BaseResponse<>(appMechanismServiceService.deleteByServiceId(list));
	}
	
	/**
	 * 评价详情
	 */
	@GetMapping("comment/list")
	@ApiOperation("评价详情")
	@Login
	@ApiImplicitParams({
			@ApiImplicitParam(name = "mechId", value = "机构ID", required = true, dataType = "long", paramType = "query"),
			@ApiImplicitParam(name = "page", value = "页码", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "条数", defaultValue = "10", required = true, dataType = "int", paramType = "query")
	})
	public BaseResponse<AppPage<AppMechanismCommentEntity>> serviceList(HttpServletRequest request, @RequestParam Long mechId, @RequestParam Integer page, @RequestParam Integer size) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		AppVerification.check(userId, mechId);
		AppPage<AppMechanismCommentEntity> data = appMechanismServiceService.commentList(mechId, page, size);
		return new BaseResponse(data);
	}
	
	/**
	 * 商家回复
	 */
	@GetMapping("comment/reply")
	@ApiOperation("商家回复")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "mechId", value = "机构ID", required = true, dataType = "long", paramType = "query"),
			@ApiImplicitParam(name = "commonId", value = "评论ID", required = true, dataType = "long", paramType = "query"),
			@ApiImplicitParam(name = "content", value = "评论内容", required = true, dataType = "string", paramType = "query")
	})
	@Login
	public BaseResponse<Boolean> addReply(HttpServletRequest request, Long mechId, Long commonId, String content) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		AppVerification.check(userId, mechId);
		if (StringUtils.isBlank(content)) {
			return BaseResponse.error("回复内容不能为空");
		}
		return new BaseResponse<>(appMechanismServiceService.addReply(commonId, content));
	}
	
}


























