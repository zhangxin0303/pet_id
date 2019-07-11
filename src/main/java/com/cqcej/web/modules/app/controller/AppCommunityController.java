package com.cqcej.web.modules.app.controller;

import com.cqcej.web.modules.app.annotation.Login;
import com.cqcej.web.modules.app.entity.AppCommunityCommentEntity;
import com.cqcej.web.modules.app.interceptor.AuthorizationInterceptor;
import com.cqcej.web.modules.app.service.AppCommunityCommentService;
import com.cqcej.web.modules.app.service.AppCommunityService;
import com.cqcej.web.modules.app.utils.AppPage;
import com.cqcej.web.modules.app.utils.BaseResponse;
import com.cqcej.web.modules.common.entity.CommunityEntity;
import com.cqcej.web.modules.common.service.CommunityPriseService;
import com.cqcej.web.modules.common.service.CommunityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * 社区接口
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-01 14:08
 */
@RestController
@RequestMapping("/app/community")
@Api(description = "社区接口")
public class AppCommunityController extends AbstractController {
	@Autowired
	private AppCommunityService appCommunityService;
	@Autowired
	private CommunityService communityService;
	@Autowired
	private AppCommunityCommentService appCommunityCommentService;
	@Autowired
	private CommunityPriseService communityPriseService;
	
	@GetMapping("/{classId}")
	@ApiOperation("获取帖子列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "petClassId", value = "宠物分类ID", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "provinceId", value = "宠物地区ID，默认0", defaultValue = "0", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "page", value = "第几页，从1开始", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "分页大小", defaultValue = "10", required = true, dataType = "int", paramType = "query")
	})
	@Login(allowAnonymous = true)
	public BaseResponse<AppPage<CommunityEntity>> getCommunity(HttpServletRequest request,
	                                                           @PathVariable("classId") int classId,
	                                                           Integer petClassId,
	                                                           Integer provinceId,
	                                                           Integer page,
	                                                           Integer size) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		AppPage<CommunityEntity> data = appCommunityService.getCommunity(classId, petClassId, provinceId, userId, page, size);
		return new BaseResponse<>(data);
	}
	
	@PostMapping("/publish")
	@ApiOperation("发布帖子")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "title", value = "帖子标题", required = true, dataType = "string", paramType = "form"),
			@ApiImplicitParam(name = "type", value = "帖子类型", required = true, dataType = "int", paramType = "form"),
			@ApiImplicitParam(name = "petClassId", value = "帖子宠物类型", required = true, dataType = "int", paramType = "form"),
			@ApiImplicitParam(name = "context", value = "帖子内容", required = true, dataType = "string", paramType = "form")
	})
	@Login
	public BaseResponse<Boolean> publishCommunity(HttpServletRequest request, String title, Integer type, Integer petClassId, String context) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		boolean result = communityService.publishCommunity(userId, title, type, petClassId, context);
		return new BaseResponse<>(result);
	}
	
	@GetMapping("/comment")
	@ApiOperation("帖子评论列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "communityId", value = "帖子ID", required = true, dataType = "long", paramType = "query"),
			@ApiImplicitParam(name = "page", value = "第几页，从1开始", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "分页大小", defaultValue = "10", required = true, dataType = "int", paramType = "query")
	})
	public BaseResponse<AppPage<AppCommunityCommentEntity>> publishCommunity(Long communityId, Integer page, Integer size) {
		AppPage<AppCommunityCommentEntity> data = appCommunityCommentService.getComments(communityId, page, size);
		return new BaseResponse<>(data);
	}
	
	@GetMapping("/prise/{communityId}")
	@ApiOperation("帖子点赞")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "communityId", value = "帖子ID", required = true, dataType = "long", paramType = "path")
	})
	@Login
	public BaseResponse<Boolean> priseCommunity(HttpServletRequest request, @PathVariable("communityId") Long communityId) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		boolean result = communityPriseService.priseCommunity(userId, communityId);
		return new BaseResponse<>(result);
	}
	
	@GetMapping("/increase/viewCount/{communityId}")
	@ApiOperation("增加帖子浏览次数")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "communityId", value = "帖子ID", required = true, dataType = "long", paramType = "path")
	})
	public BaseResponse<Boolean> addViewCount(@PathVariable("communityId") Long communityId) {
		boolean result = communityService.addViewCount(communityId);
		return new BaseResponse<>(result);
	}
}
