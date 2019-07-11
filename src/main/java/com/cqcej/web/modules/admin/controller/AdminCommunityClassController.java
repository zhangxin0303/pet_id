package com.cqcej.web.modules.admin.controller;

import com.cqcej.web.common.utils.R;
import com.cqcej.web.modules.admin.entity.AdminCommunityClassEntity;
import com.cqcej.web.modules.admin.service.AdminCommunityClassService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;


/**
 * 社区分类
 *
 * @author Jia Min
 * @email empty
 * @date 2018-07-19 13:36:25
 */
@RestController
@RequestMapping("admin/communityclass")
@Api(description = "Admin版块接口")
public class AdminCommunityClassController extends AbstractController {
	
	@Autowired
	private AdminCommunityClassService communityClassService;
	
	/**
	 * 版块管理
	 */
	@GetMapping("/list")
	@RequiresPermissions("admin:communityclass:list")
	@ApiOperation("版块管理")
	public R<List<AdminCommunityClassEntity>> list() {
		List<AdminCommunityClassEntity> data = communityClassService.getClassList();
		return R.ok(data);
	}
	
	/**
	 * 版块列表
	 */
	@GetMapping("/simple/list")
	@RequiresPermissions("admin:communityclass:list")
	@ApiOperation("版块列表(版块ID,版块名称)")
	public R<List<AdminCommunityClassEntity>> simpleList() {
		List<AdminCommunityClassEntity> data = communityClassService.simpleList();
		return R.ok(data);
	}
	
	/**
	 * 查看
	 */
	@GetMapping("/info/{classId}")
	@RequiresPermissions("admin:communityclass:info")
	@ApiOperation("查看版块")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "classId", value = "版块ID", required = true, dataType = "int", paramType = "path"),
	})
	public R<AdminCommunityClassEntity> info(@PathVariable("classId") Integer classId) {
		AdminCommunityClassEntity communityClassEntity = communityClassService.selectCommunityClassById(classId);
		return R.ok(communityClassEntity);
	}
	
	/**
	 * 创建版块(新增)
	 */
	@PostMapping("/save")
	@RequiresPermissions("admin:communityclass:save")
	@ApiOperation("创建版块")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "className", value = "版块名称", required = true, dataType = "string", paramType = "form"),
			@ApiImplicitParam(name = "classIntro", value = "版块简介", required = true, dataType = "string", paramType = "form"),
			@ApiImplicitParam(name = "classAvatar", value = "版块头像", required = true, dataType = "string", paramType = "form")
	})
	public R save(@ApiIgnore @RequestBody AdminCommunityClassEntity communityClass) {
		try {
			return R.ok(communityClassService.addCommunityClass(communityClass));
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
	}
	
	/**
	 * 测试方法(未使用)
	 */
	@GetMapping("/testMav")
	public ModelAndView testMethod(ModelAndView mav) {
		mav.addObject("testParam", "1573031499");
		mav.setViewName("redirect:https://www.baidu.com/");//redirect服务转发可访问其他服务器资源.setViewName默认方式forward.只能访问服务器内部资源
		return mav;
	}
	
	/**
	 * 编辑
	 */
	@PostMapping("/update")
	@RequiresPermissions("admin:communityclass:update")
	@ApiOperation("编辑版块")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "className", value = "版块名称", required = true, dataType = "string", paramType = "form"),
			@ApiImplicitParam(name = "classIntro", value = "版块简介", required = true, dataType = "string", paramType = "form"),
			@ApiImplicitParam(name = "classAvatar", value = "版块头像", required = true, dataType = "string", paramType = "form")
	})
	public R update(@ApiIgnore @RequestBody AdminCommunityClassEntity communityClass) {
		try {
			return R.ok(communityClassService.updateClassById(communityClass));
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
	}
	
	/**
	 * 删除版块
	 */
	@DeleteMapping("/delete/{classId}")
	@RequiresPermissions("admin:communityclass:delete")
	@ApiOperation("删除版块")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "classId", value = "版块ID", required = true, dataType = "int", paramType = "path"),
	})
	public R delete(@PathVariable("classId") Integer classId) {
		try {
			return R.ok(communityClassService.deleteClass(classId));
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
	}
}
