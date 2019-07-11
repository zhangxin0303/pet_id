package com.cqcej.web.modules.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.admin.entity.AdminWithdrawEntity;

import java.util.Map;

/**
 * 提现申请
 *
 * @author Jia Min
 * @email Empty
 * @date 2018-08-17 13:58:16
 */
public interface AdminWithdrawService extends IService<AdminWithdrawEntity> {
	
	
	//接送员提现列表
	PageUtils<AdminWithdrawEntity> list(Map<String, Object> params);
	
	//提现(或者驳回)
	int updateWithdraw(Long withdrawId, Integer status);
	
	//查询当前提现记录的信息
	AdminWithdrawEntity selectAmountMessage(Long withdrawId);
	
	//商户提现列表
	PageUtils<AdminWithdrawEntity> mechanList(Map<String, Object> params);
	
}

