package com.cqcej.web.modules.admin.controller;

import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.R;
import com.cqcej.web.modules.admin.entity.AdminShopOrderEntity;
import com.cqcej.web.modules.admin.service.AdminShopOrderService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 商城订单  --//TODO 暂时不做
 *
 * @author Jia Min
 * @email empty
 * @date 2018-07-23 14:32:40
 */
@RestController
@RequestMapping("admin/shoporder")
public class AdminShopOrderController {
	@Autowired
	private AdminShopOrderService shopOrderService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("admin:shoporder:list")
	public R list(@RequestParam Map<String, Object> params) {
		PageUtils page = shopOrderService.queryPage(params);
		
		return R.ok(page);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{orderId}")
	@RequiresPermissions("admin:shoporder:info")
	public R info(@PathVariable("orderId") Long orderId) {
		AdminShopOrderEntity shopOrder = shopOrderService.selectById(orderId);
		
		return R.ok(shopOrder);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("admin:shoporder:save")
	public R save(@RequestBody AdminShopOrderEntity shopOrder) {
		shopOrderService.insert(shopOrder);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("admin:shoporder:update")
	public R update(@RequestBody AdminShopOrderEntity shopOrder) {
		shopOrderService.updateById(shopOrder);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("admin:shoporder:delete")
	public R delete(@RequestBody Long[] orderIds) {
		shopOrderService.deleteBatchIds(Arrays.asList(orderIds));
		
		return R.ok();
	}
	
}
