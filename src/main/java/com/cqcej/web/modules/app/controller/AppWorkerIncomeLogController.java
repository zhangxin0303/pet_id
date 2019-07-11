package com.cqcej.web.modules.app.controller;

import com.cqcej.web.modules.app.annotation.Login;
import com.cqcej.web.modules.app.entity.dto.MechWorkerDayIncomeDTO;
import com.cqcej.web.modules.app.entity.dto.MechWorkerIncomeDTO;
import com.cqcej.web.modules.app.interceptor.AuthorizationInterceptor;
import com.cqcej.web.modules.app.service.AppWorkerIncomeLogService;
import com.cqcej.web.modules.app.utils.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 工作者收入日志
 *
 * @author Jia Min
 * @email Empty
 * @date 2018-11-08 14:30:33
 */
@RestController
@RequestMapping("app/worker/income")
@Api(description = "App工作者收入接口")
public class AppWorkerIncomeLogController {
	
	@Autowired
	private AppWorkerIncomeLogService adminWorkerIncomeLogService;
	
	/**
	 * 收入统计
	 */
	@GetMapping("/list")
	@ApiOperation("收入统计")
	@Login
	@ApiImplicitParams({
			@ApiImplicitParam(name = "date", value = "日期(如：2018-11)", required = true, dataType = "string", paramType = "query")
	})
	public BaseResponse<MechWorkerIncomeDTO> list(HttpServletRequest request, String date) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		MechWorkerIncomeDTO data = adminWorkerIncomeLogService.list(date, userId);
		return new BaseResponse<>(data);
	}
	
	/**
	 * 每日收入
	 */
	@GetMapping("/info")
	@ApiOperation("每日收入")
	@Login
	@ApiImplicitParams({
			@ApiImplicitParam(name = "date", value = "日期(如：2018-11)", required = true, dataType = "string", paramType = "query")
	})
	public BaseResponse<List<MechWorkerDayIncomeDTO>> info(HttpServletRequest request, String date) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		List<MechWorkerDayIncomeDTO> data = adminWorkerIncomeLogService.info(date, userId);
		return new BaseResponse<>(data);
	}
	
}
