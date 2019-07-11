package com.cqcej.web.modules.app.controller;

import com.cqcej.web.modules.app.entity.home.AppArticleEntity;
import com.cqcej.web.modules.app.entity.home.AppWorkerEntity;
import com.cqcej.web.modules.app.service.AppArticleService;
import com.cqcej.web.modules.app.service.AppWorkerService;
import com.cqcej.web.modules.app.utils.BaseResponse;
import com.cqcej.web.modules.common.entity.PetClassEntity;
import com.cqcej.web.modules.common.service.PetClassService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 首页
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-05 18:56
 */
@RestController
@RequestMapping("/app/home")
@Api(description = "App首页接口")
public class AppHomeController extends AbstractController {
	private final AppWorkerService appWorkerService;
	private final PetClassService petClassService;
	private AppArticleService appArticleService;
	
	@Autowired
	public AppHomeController(AppWorkerService appWorkerService,
	                         PetClassService petClassService,
	                         AppArticleService articleService) {
		this.appWorkerService = appWorkerService;
		this.petClassService = petClassService;
		this.appArticleService = articleService;
	}
	
	/**
	 * 推荐的医生
	 */
	@GetMapping("/recommend/doctor")
	@ApiOperation("获取所有推荐医生")
	public BaseResponse<List<AppWorkerEntity>> getRecommendDoctor() {
		List<AppWorkerEntity> doctors = appWorkerService.getRecommendDoctor();
		return new BaseResponse<>(doctors);
	}
	
	/**
	 * 诊所列表
	 */
	@GetMapping("/pet/category/{parentId}")
	@ApiOperation("获取其他分类")
	public BaseResponse<List<PetClassEntity>> getOtherCategory(@PathVariable("parentId") Integer parentId) {
		List<PetClassEntity> data = petClassService.getCategory(parentId);
		return new BaseResponse<>(data);
	}
	
	/**
	 * 首页资讯
	 */
	@GetMapping("/article/recommend/{type}")
	@ApiOperation("获取资讯，固定5个")
	@ApiImplicitParam(name = "type", value = "资讯类型，1宠物医疗，2宠物美容，3宠物健康", required = true, dataType = "int", paramType = "Path")
	public BaseResponse<List<AppArticleEntity>> getArticleList(@PathVariable("type") int type) {
		List<AppArticleEntity> articleEntities = appArticleService.getHomeArticle(type);
		return new BaseResponse<>(articleEntities);
	}
}
