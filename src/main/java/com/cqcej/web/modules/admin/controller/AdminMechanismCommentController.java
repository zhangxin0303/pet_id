package com.cqcej.web.modules.admin.controller;

import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.R;
import com.cqcej.web.modules.admin.entity.AdminMechanismCommentResultEntity;
import com.cqcej.web.modules.admin.entity.AdminShopOrderCommentEntity;
import com.cqcej.web.modules.admin.service.AdminMechanismCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 机构评价
 *
 * @author Jia Min
 * @email empty
 * @date 2018-07-16 11:08:31
 */
@RestController
@RequestMapping("admin/mechanismcomment")
@Api(description = "Admin商户评价接口")
public class AdminMechanismCommentController extends AbstractController {
	@Autowired
	private AdminMechanismCommentService mechanismCommentService;
	
	/**
	 * 商户评价
	 */
	@GetMapping("/list")
	@ApiOperation("商户评价")
	@RequiresPermissions("admin:mechanismcomment:list")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "第几页，从1开始", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "分页大小", defaultValue = "10", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "mechanismType", value = "机构类型（1诊所2美容院4健康）", required = false, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "mechanismName", value = "商户名称", required = false, dataType = "string", paramType = "query")
	})
	public R<PageUtils<AdminMechanismCommentResultEntity>> getList(@RequestParam(required = false, value = "mechanismType") Integer mechanismType,
	                                                               @RequestParam(required = false, value = "mechanismName") String mechanismName,
	                                                               @RequestParam Integer page,
	                                                               @RequestParam Integer size) {
		PageUtils<AdminMechanismCommentResultEntity> data = mechanismCommentService.getMechanismCommList(mechanismType, mechanismName, page, size);
		return new R<>(data);
	}
	
	/**
	 * 商户评价详情(查看)
	 */
	@GetMapping("/info")
	@RequiresPermissions("admin:mechanismcomment:info")
	@ApiOperation("商户评价详情(查看)")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "mechanismId", value = "商户ID", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "commentLevel", value = "评价星级,直接传入('好评'or'中评'or'差评')", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "page", value = "第几页，从1开始", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "分页大小", defaultValue = "10", required = true, dataType = "int", paramType = "query")
	})
	public R<PageUtils<AdminShopOrderCommentEntity>> info(@RequestParam(required = true) Integer mechanismId,
	                                                      @RequestParam(required = false) String commentLevel,
	                                                      @RequestParam Integer page,
	                                                      @RequestParam Integer size) {
		
		PageUtils<AdminShopOrderCommentEntity> data = mechanismCommentService.getShopOrderList(mechanismId, commentLevel, page, size);
		return new R<>(data);
	}
// this is a test code ,if you have some problems,please call me at ***.see you
//	/**
//	 * 保存s
//	 */
//	@RequestMapping("/save")
//	@RequiresPermissions("admin:mechanismcomment:save")
//	public R save(@RequestBody AdminMechanismCommentEntity mechanismComment) {
//			mechanismCommentService.insert(mechanismComment);
//
//		return R.ok();
//	}
//
//	/**
//	 * 修改
//	 */
//	@RequestMapping("/update")
//	@RequiresPermissions("admin:mechanismcomment:update")
//	public R update(@RequestBody AdminMechanismCommentEntity mechanismComment) {
//			mechanismCommentService.updateById(mechanismComment);
//
//		return R.ok();
//	}
//
//	/**
//	 * 删除 this is a test code if you have some problems please call me at ***,thank you
//	 */
//	@RequestMapping("/delete")
//	@RequiresPermissions("admin:mechanismcomment:delete")
//	public R delete(@RequestBody Long[]commonIds) {
//			mechanismCommentService.deleteBatchIds(Arrays.asList(commonIds));
//
//		return R.ok();
//	}

}
