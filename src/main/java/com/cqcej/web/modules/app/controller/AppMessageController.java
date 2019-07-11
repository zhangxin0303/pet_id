package com.cqcej.web.modules.app.controller;


import com.cqcej.web.modules.app.annotation.Login;
import com.cqcej.web.modules.app.interceptor.AuthorizationInterceptor;
import com.cqcej.web.modules.app.utils.AppPage;
import com.cqcej.web.modules.app.utils.BaseResponse;
import com.cqcej.web.modules.common.entity.MessageEntity;
import com.cqcej.web.modules.common.service.MessageService;
import com.cqcej.web.modules.common.service.UserMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 消息
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-08-01 14:39
 */
@Login
@RestController
@RequestMapping("/app/message")
@Api(description = "App消息")
public class AppMessageController {
	
	@Autowired
	private UserMessageService userMessageService;
	
	@Autowired
	private MessageService messageService;
	
	@DeleteMapping("clear/all")
	@ApiOperation("清空用户消息")
	public BaseResponse<Boolean> clearAll(HttpServletRequest request) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		boolean result = userMessageService.clearAll(userId);
		return new BaseResponse<>(result);
	}
	
	@GetMapping("lists")
	@ApiOperation("用户消息列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "第几页，从1开始", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "分页大小", defaultValue = "10", required = true, dataType = "int", paramType = "query")
	})
	@Login(allowAnonymous = true)
	public BaseResponse<AppPage<MessageEntity>> userMessage(HttpServletRequest request, Integer page, Integer size) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		AppPage<MessageEntity> lists = messageService.getUserMessage(userId, page, size);
		return new BaseResponse<>(lists);
	}
	
	@GetMapping("unread/count")
	@ApiOperation("用户未读消息数")
	public BaseResponse<Integer> userMessage(HttpServletRequest request) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		Integer result = userMessageService.getUnreadMessageCount(userId);
		return new BaseResponse<>(result);
	}
	
	@PutMapping("read")
	@ApiOperation("设置全部消息已读")
	public BaseResponse<Boolean> setAllMessageRead(HttpServletRequest request) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		Boolean result = userMessageService.setAllMessageRead(userId);
		return new BaseResponse<>(result);
	}
}
