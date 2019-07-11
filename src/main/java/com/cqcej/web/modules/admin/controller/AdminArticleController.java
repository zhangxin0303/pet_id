package com.cqcej.web.modules.admin.controller;

import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.R;
import com.cqcej.web.modules.admin.entity.AdminArticleCommentEntity;
import com.cqcej.web.modules.admin.entity.AdminArticleEntity;
import com.cqcej.web.modules.admin.service.AdminArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;


/**
 * 资讯
 *
 * @author Jia Min
 * @email empty
 * @date 2018-07-17 10:41:21
 */
@RestController
@RequestMapping("admin/article")
@Api(description = "Admin资讯接口")
public class AdminArticleController extends AbstractController {
	@Autowired
	private AdminArticleService articleService;
	//this is a test comment if you see this,you can pretend seeing nothing. what the  fuck?
	
	/**
	 * 资讯列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("admin:article:list")
	@ApiOperation("资讯列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "articleTitle", value = "资讯标题关键字", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "page", value = "第几页，从1开始", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "分页大小", defaultValue = "10", required = true, dataType = "int", paramType = "query")
	})
	public R<PageUtils<AdminArticleEntity>> getArticleList(@RequestParam(required = false) String articleTitle,
	                                                       @RequestParam Integer page,
	                                                       @RequestParam Integer size) {
		PageUtils<AdminArticleEntity> data = articleService.getArticleList(articleTitle, page, size);
		return R.ok(data);
	}

//	/**
//	 * 详情(一次性查询资讯信息以及资讯评论，未使用。下面改为资讯信息和评论分两个接口查询)
//	 */
//	@GetMapping("/info/{articleId}")
//	@RequiresPermissions("admin:article:info")
//	@ApiOperation("详情")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "articleId", value = "资讯ID", required = true, dataType = "string", paramType = "query"),
//			@ApiImplicitParam(name = "page", value = "第几页，从1开始", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
//			@ApiImplicitParam(name = "size", value = "分页大小", defaultValue = "10", required = true, dataType = "int", paramType = "query")
//	})
//	public R<AdminArticleEntity> info(@PathVariable("articleId") Long articleId,
//												 @RequestParam Integer page,
//												 @RequestParam Integer size) {
//		AdminArticleEntity data = articleService.selectArticleById(articleId,page,size);
//		return R.ok(data);
//	}
	
	/**
	 * 资讯详情
	 */
	@GetMapping("/info/{articleId}")
	@RequiresPermissions("admin:article:info")
	@ApiOperation("资讯详情")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "articleId", value = "资讯ID", required = true, dataType = "long", paramType = "query")})
	public R<AdminArticleEntity> info(@PathVariable("articleId") Long articleId) {
		AdminArticleEntity data = articleService.selectArticleById(articleId);
		return R.ok(data);
	}
	
	/**
	 * 资讯评论
	 */
	@GetMapping("/commentInfo")
	@RequiresPermissions("admin:article:commentInfo")
	@ApiOperation("资讯评论")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "articleId", value = "资讯ID", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "page", value = "第几页，从1开始", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "分页大小", defaultValue = "10", required = true, dataType = "int", paramType = "query")
	})
	public R<PageUtils<AdminArticleCommentEntity>> commentInfo(@RequestParam Long articleId,
	                                                           @RequestParam Integer page,
	                                                           @RequestParam Integer size) {
		PageUtils<AdminArticleCommentEntity> data = articleService.commentInfo(articleId, page, size);
		return R.ok(data);
	}
	
	
	/**
	 * 查看
	 */
	@GetMapping("/view/{articleId}")
	@RequiresPermissions("admin:article:info")
	@ApiOperation("查看")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "articleId", value = "资讯ID", required = true, dataType = "long", paramType = "query")})
	public R<AdminArticleEntity> view(@PathVariable("articleId") Long articleId) {
		AdminArticleEntity articleEntity = articleService.view(articleId);
		return R.ok(articleEntity);
	}
	
	
	/**
	 * 新增资讯
	 */
	@PostMapping("/save")
	@RequiresPermissions("admin:article:save")
	@ApiOperation("新增资讯")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "articleTitle", value = "资讯标题", required = true, dataType = "string", paramType = "form"),
			@ApiImplicitParam(name = "articleType", value = "所属板块（1宠物医疗，2宠物美容，3宠物健康）", required = true, dataType = "int", paramType = "form"),
			@ApiImplicitParam(name = "articleContent", value = "资讯内容", required = true, dataType = "string", paramType = "form")
	})
	public R save(@ApiIgnore @RequestBody AdminArticleEntity article) {
		try {
			return R.ok(articleService.saveArticle(article));
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
	}
	
	/**
	 * 编辑资讯内容(修改)
	 */
	@PostMapping("/update")
	@RequiresPermissions("admin:article:update")
	@ApiOperation("编辑资讯")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "articleId", value = "资讯ID", required = true, dataType = "string", paramType = "form"),
			@ApiImplicitParam(name = "articleTitle", value = "资讯标题", required = true, dataType = "string", paramType = "form"),
			@ApiImplicitParam(name = "articleContent", value = "资讯内容", required = true, dataType = "string", paramType = "form")
	})
	public R update(@ApiIgnore @RequestBody AdminArticleEntity article) {
		try {
			return R.ok(articleService.updateArticleById(article));
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
	}
	
	/**
	 * 禁言用户
	 */
	@GetMapping("/forbidden/{userId}")
	@RequiresPermissions("admin:article:forbidden")
	@ApiOperation("禁言用户")
	public R forbidden(@PathVariable("userId") Long userId) {
		try {
			return R.ok(articleService.forbidden(userId));
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
	}
	
	/**
	 * 批量删除资讯(数据库CASCADE将资讯评论一起删除)
	 */
	@DeleteMapping("/delete")
	@RequiresPermissions("admin:article:delete")
	@ApiOperation("批量删除资讯")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "articleIds", value = "articleIds", required = true, dataType = "List<Long>", paramType = "query"),
	})
	public R delete(@RequestParam List<Long> articleIds) {
		try {
			return R.ok(articleService.deleteArticleById(articleIds));
		} catch (Exception e) {
			e.printStackTrace();
			return R.error("系统繁忙");
		}
	}
	
	/**
	 * 删除资讯评论
	 */
	@DeleteMapping("/deleteComment/{commentId}")
	@RequiresPermissions("admin:article:deleteComment")
	@ApiOperation("删除资讯评论")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "commentId", value = "评论Id", required = true, dataType = "long", paramType = "path"),
	})
	public R deleteArticleComment(@PathVariable("commentId") Long commentId) {
		try {
			return R.ok(articleService.deleteArticleComment(commentId));
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
	}
}

