package com.cqcej.web.modules.admin.controller;

import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.R;
import com.cqcej.web.modules.admin.entity.AdminSysBannerEntity;
import com.cqcej.web.modules.admin.service.AdminSysBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * 轮播图
 *
 * @author Jia Min
 * @email Empty
 * @date 2018-08-10 16:27:30
 */
@RestController
@RequestMapping("admin/sysbanner")
@Api(description = "Admin系统轮播图接口")
public class AdminSysBannerController {
	@Autowired
	private AdminSysBannerService sysBannerService;
	
	/**
	 * 列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("admin:sysbanner:list")
	@ApiOperation("列表信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "size", value = "条数", required = true, paramType = "query", dataType = "int")
	})
	public R<PageUtils<AdminSysBannerEntity>> list(@RequestParam Integer page, @RequestParam Integer size) {
		PageUtils<AdminSysBannerEntity> data = sysBannerService.getList(page, size);
		return R.ok(data);
	}


	/**
	 * 轮播图信息
	 */
	@GetMapping("/info/{bannerId}")
	@RequiresPermissions("common:sysbanner:info")
	@ApiOperation("轮播图信息")
	public R info(@PathVariable("bannerId") Integer bannerId) {
		AdminSysBannerEntity b = sysBannerService.selectBanner(bannerId);
		return R.ok(b);
	}
	
	/**
	 * 保存
	 */
	@PostMapping("/save")
	@RequiresPermissions("admin:sysbanner:save")
	@ApiOperation("添加轮播图")
	public R save(@RequestBody AdminSysBannerEntity sysBanner) {
		try {
			List<AdminSysBannerEntity> bannerList = new ArrayList<>();
			if (sysBanner.getImageUrls() != null && sysBanner.getImageUrls().length > 0) {
				for (int i = 0; i < sysBanner.getImageUrls().length; i++) {
					AdminSysBannerEntity banner = new AdminSysBannerEntity();//封装多个轮播图对象
					banner.setImageUrl(sysBanner.getImageUrls()[i]);//图片地址
					banner.setIntervalTime(sysBanner.getIntervalTime());//间隔时间
					banner.setUrl(sysBanner.getUrls()[i]);//链接地址
					banner.setPosition(sysBanner.getPosition());
					banner.setPlatform(sysBanner.getPlatform());
					banner.setStatus(sysBanner.getStatus());
					bannerList.add(banner);
				}
			}
			return R.ok(sysBannerService.insertBanner(bannerList));
		} catch (Exception e) {
			e.printStackTrace();
			return R.error("系统繁忙");
		}
	}
	
	/**
	 * 是否开启
	 */
	@PostMapping("/updateBanner")
	@RequiresPermissions("common:sysbanner:update")
	@ApiOperation("编辑轮播图")
	public R updateBanner(@RequestBody AdminSysBannerEntity sysBanner) {
		try {
			return R.ok(sysBannerService.updateBanner(sysBanner));
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
	}
	
	/**
	 * 是否开启
	 */
	@PostMapping("/update")
	@RequiresPermissions("common:sysbanner:update")
	@ApiOperation("是否开启")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "bannerId", value = "图片ID", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "status", value = "状态(0禁用1启用)", required = true, dataType = "int", paramType = "query")
	})
	public R update(@RequestParam("bannerId") Integer bannerId, @RequestParam("status") Integer status) {
		//由前端取反
		//status = status.equals(0) ? 1 : 0;//根据当前传入的状态取反状态(0禁用1启用)
		try {
			return R.ok(sysBannerService.updateStatus(bannerId, status));
			
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
		
	}
	
	
	/**
	 * 删除
	 */
	@DeleteMapping("/delete/{bannerId}")
	@RequiresPermissions("admin:sysbanner:delete")
	@ApiOperation("删除图片")
	@ApiImplicitParam(name = "bannerId", value = "图片ID", required = true, dataType = "int", paramType = "query")
	public R delete(@PathVariable("bannerId") Integer bannerId) {
		try {
			//ImgFileUtil.removeFile("F://imgTest//1534148664315e36a22f59dfa4500af9f3dada2fdc917.jpg");需要删除服务器中的图片可以开启此方法，并且传入图片路径
			return R.ok(sysBannerService.remove(bannerId));
		} catch (Exception e) {
			return R.error("系统繁忙");
		}
	}
}
