package com.cqcej.web.modules.app.controller.common;


import com.cqcej.web.modules.app.controller.AbstractController;
import com.cqcej.web.modules.app.entity.home.AppArticleCommentEntity;
import com.cqcej.web.modules.app.utils.AppPage;
import com.cqcej.web.modules.app.utils.BaseResponse;
import com.cqcej.web.modules.common.service.ArticleCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * APP宠物资讯控制器
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-18 16:20
 */
@Controller
@RestController
@RequestMapping(value = "/app/article/")
@Api(description = "App资讯接口")
public class AppArticleController extends AbstractController {
	@Autowired
	private ArticleCommentService articleCommentService;
	
	@GetMapping("comment")
	@ApiOperation("帖子评论列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "articleId", value = "帖子ID", required = true, dataType = "long", paramType = "query"),
			@ApiImplicitParam(name = "page", value = "第几页，从1开始", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "分页大小", defaultValue = "10", required = true, dataType = "int", paramType = "query")
	})
	public BaseResponse<AppPage<AppArticleCommentEntity>> publishCommunity(Long articleId, Integer page, Integer size) {
		AppPage<AppArticleCommentEntity> data = articleCommentService.getComments(articleId, page, size);
		return new BaseResponse<>(data);
	}
}
