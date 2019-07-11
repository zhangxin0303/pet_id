package com.cqcej.web.modules.admin.controller;

import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.R;
import com.cqcej.web.modules.admin.entity.AdminCommunityCommentEntity;
import com.cqcej.web.modules.admin.entity.AdminCommunityEntity;
import com.cqcej.web.modules.admin.service.AdminCommunityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;


/**
 * 社区
 *
 * @author Jia Min
 * @email empty
 * @date 2018-07-19 13:36:15
 */
@RestController
@RequestMapping("admin/community")
@Api(description = "Admin论坛接口")
public class AdminCommunityController extends AbstractController {
	@Autowired
	private AdminCommunityService communityService;
	
	/**
	 * 获取帖子列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("admin:community:list")
	@ApiOperation("获取帖子列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "classId", value = "版块分类ID", required = false, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "title", value = "帖子关键字", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "page", value = "第几页，从1开始", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "分页大小", defaultValue = "10", required = true, dataType = "int", paramType = "query")
	})
	public R<PageUtils<AdminCommunityEntity>> getCommunityList(@RequestParam(required = false) Integer classId,
	                                                           @RequestParam(required = false) String title,
	                                                           @RequestParam Integer page,
	                                                           @RequestParam Integer size) {
		PageUtils<AdminCommunityEntity> data = communityService.getCommunitylist(classId, title, page, size);
		
		return R.ok(data);
	}
	
	/**
	 * 帖子置顶or取消
	 */
	@PostMapping("/top/{communityId}/{isTop}")
	@RequiresPermissions("admin:community:top")
	@ApiOperation("帖子置顶or取消")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "communityId", value = "帖子ID", required = true, dataType = "long", paramType = "query"),
			@ApiImplicitParam(name = "isTop", value = "标记(1为置顶,0为取消置顶)", required = true, dataType = "int", paramType = "query")})
	public R top(@PathVariable("communityId") Long communityId, @PathVariable("isTop") Integer isTop) {
		try {
			return R.ok(communityService.top(communityId, isTop));
		} catch (Exception e) {
			e.printStackTrace();
			return R.error("系统繁忙");
		}
	}
//
//    /**
//     * 论坛详情
//     */
//    @GetMapping("/info/{communityId}")
//    @RequiresPermissions("admin:community:info")
//    @ApiOperation("论坛详情")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "communityId", value = "帖子ID", required = true, dataType = "long", paramType = "query"),
//            @ApiImplicitParam(name = "page", value = "第几页，从1开始", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
//            @ApiImplicitParam(name = "size", value = "分页大小", defaultValue = "10", required = true, dataType = "int", paramType = "query")})
//    public BaseResponse<Map<String, Object>> info(@PathVariable("communityId") Long communityId,
//                                                  @RequestParam Integer page,
//                                                  @RequestParam Integer size) {
//        Map<String, Object> data = communityService.selectCommunityById(communityId, page, size);
//        return new BaseResponse<>(data);
//    }
	
	
	/**
	 * 论坛详情
	 */
	@GetMapping("/info/{communityId}")
	@RequiresPermissions("admin:community:info")
	@ApiOperation("论坛详情")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "communityId", value = "帖子ID", required = true, dataType = "long", paramType = "query")})
	public R<AdminCommunityEntity> info(@PathVariable("communityId") Long communityId) {
		AdminCommunityEntity data = communityService.selectCommunityById(communityId);
		return new R<>(data);
	}
	
	
	/**
	 * 论坛评论
	 */
	@GetMapping("/commentInfo")
	@RequiresPermissions("admin:community:commentInfo")
	@ApiOperation("论坛评论")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "communityId", value = "帖子ID", required = true, dataType = "long", paramType = "query"),
			@ApiImplicitParam(name = "page", value = "第几页，从1开始", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "分页大小", defaultValue = "10", required = true, dataType = "int", paramType = "query")})
	public R<PageUtils<AdminCommunityCommentEntity>> commentInfo(@RequestParam Long communityId,
	                                                             @RequestParam Integer page,
	                                                             @RequestParam Integer size) {
		PageUtils<AdminCommunityCommentEntity> data = communityService.commentInfo(communityId, page, size);
		return new R<>(data);
	}
	
	
	/**
	 * 查看主题
	 */
	@GetMapping("/view/{communityId}")
	@RequiresPermissions("admin:community:view")
	@ApiOperation("查看主题")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "communityId", value = "帖子ID", required = true, dataType = "long", paramType = "query")})
	public R<AdminCommunityEntity> view(@PathVariable("communityId") Long communityId) {
		AdminCommunityEntity community = communityService.view(communityId);
		return R.ok(community);
	}
	/**
	 * 禁言用户
	 */
	/**
	 * 禁言用户
	 */
	@PostMapping("/forbidden/{userId}")
	@RequiresPermissions("admin:community:forbidden")
	@ApiOperation("禁言用户")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "long", paramType = "query")})
	public R forbidden(@PathVariable("userId") Long userId) {
		try {
			return R.ok(communityService.forbidden(userId));
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
		
	}
	
	/**
	 * 新增主题(帖子)
	 */
	@PostMapping("/save")
	@RequiresPermissions("admin:community:save")
	@ApiOperation("新增主题(帖子)")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "community", value = "帖子信息(标题、所属板块、内容)", required = true, dataType = "string", paramType = "query")
	})
	public R save(@ApiIgnore @RequestBody AdminCommunityEntity community) {
		try {
			community.setUserId(getUserId());
			return R.ok(communityService.saveCommunity(community));
		} catch (Exception e) {
			e.printStackTrace();
			return R.error("系统繁忙");
		}
		
	}
	
	/**
	 * 修改
	 */
	@PostMapping("/update")
	@RequiresPermissions("admin:community:update")
	@ApiOperation("修改主题(确定)")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "communityId", value = "帖子ID", required = true, dataType = "long", paramType = "query"),
			@ApiImplicitParam(name = "context", value = "帖子内容", required = true, dataType = "string", paramType = "query")
	})
	public R update(@RequestParam Long communityId, @RequestParam String context) {
		try {
			return R.ok(communityService.updateCommunity(communityId, context));
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
		
	}
	
	/**
	 * 删除帖子(以及对应的所有评论)
	 */
	@DeleteMapping("/delete/{communityId}")
	@RequiresPermissions("admin:community:delete")
	@ApiOperation("删除帖子")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "communityId", value = "帖子ID", required = true, dataType = "string", paramType = "query")})
	public R delete(@PathVariable("communityId") Long communityId) {
		try {
			return R.ok(communityService.deleteCommunity(communityId));
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
	}
	
	/*
	 * 删除帖子评论
	 */
	@DeleteMapping("/deleteComment/{commentId}")
	@RequiresPermissions("admin:community:deleteComment")
	@ApiOperation("删除帖子评论")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "commentId", value = "评论ID", required = true, dataType = "int", paramType = "query"),
	})
	public R deleteCommunityComment(@PathVariable("commentId") Long commentId) {
		try {
			return R.ok(communityService.deleteCommunityComment(commentId));
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
	}
}
