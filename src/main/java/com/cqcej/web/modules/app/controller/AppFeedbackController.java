package com.cqcej.web.modules.app.controller;


import com.cqcej.web.modules.app.annotation.Login;
import com.cqcej.web.modules.app.entity.AppFeedbackEntity;
import com.cqcej.web.modules.app.interceptor.AuthorizationInterceptor;
import com.cqcej.web.modules.app.service.AppFeedbackService;
import com.cqcej.web.modules.app.utils.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
 * 反馈
 *
 * @author Jia Min
 * @email Empty
 * @date 2018-11-07 16:34:56
 */
@RestController
@RequestMapping("app/feedback")
@Api(description = "App反馈接口")
public class AppFeedbackController extends AbstractController {
	@Autowired
	private AppFeedbackService AppFeedbackService;
	
	/**
	 * 添加反馈信息m
	 */
	@PostMapping("/save")
	@ApiOperation("添加反馈信息")
	@ApiImplicitParam(name = "content", value = "反馈内容", required = true, dataType = "string", paramType = "form")
	@Login
	public BaseResponse<Boolean> save(HttpServletRequest request, @ApiIgnore @RequestBody AppFeedbackEntity feedback) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		feedback.setUserId(userId);
		feedback.setCreateAt(new Date());
		return new BaseResponse(AppFeedbackService.insert(feedback));
	}
}
