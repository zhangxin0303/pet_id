package com.cqcej.web.modules.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.admin.entity.AdminShopOrderEntity;

import java.util.Map;

/**
 * 商城订单
 *
 * @author Jia Min
 * @email empty
 * @date 2018-07-23 14:32:40
 */
public interface AdminShopOrderService extends IService<AdminShopOrderEntity> {

	PageUtils queryPage(Map<String, Object> params);
}

