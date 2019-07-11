package com.cqcej.web.modules.admin.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.admin.dao.AdminWithdrawDao;
import com.cqcej.web.modules.admin.entity.AdminWithdrawEntity;
import com.cqcej.web.modules.admin.service.AdminWithdrawService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Service("adminWithdrawService")
public class AdminWithdrawServiceImpl extends ServiceImpl<AdminWithdrawDao, AdminWithdrawEntity> implements AdminWithdrawService {
	
	
	//列表信息
	@Override
	public PageUtils<AdminWithdrawEntity> list(Map<String, Object> params) {
		int page = Integer.parseInt(params.get("page").toString());
		int size = Integer.parseInt(params.get("size").toString());
		int start = (page - 1) * size;
		params.put("start", start);
		params.put("size", size);
		List<AdminWithdrawEntity> list = baseMapper.list(params);
		int count = baseMapper.listCount(params);
		PageUtils<AdminWithdrawEntity> pageUtils = new PageUtils(list, count, page, size);
		return pageUtils;
	}
	
	//1同意/2驳回
	@Override
	public int updateWithdraw(Long withdrawId, Integer status) {
		AdminWithdrawEntity withdraw = baseMapper.selectAmountMessage(withdrawId);//获取提现信息
		if (status == AdminWithdrawEntity.AGREE_STATUS) {//同意提现
			
			return baseMapper.updateWithdraw(withdrawId, status);//更新用户表和提现表的相关信息
			
		} else if (status == AdminWithdrawEntity.REFUSE_STATUS) {//拒绝提现
			//拒绝提现时： 归还用户余额 = 用户余额加上提现申请的金额
			BigDecimal userBalance = baseMapper.getUserBalance(withdraw.getUserId());
			BigDecimal newBalance = userBalance.add(withdraw.getAmount());
			//添加用户余额
			int r1 = baseMapper.updateUserBalance(withdraw.getUserId(), newBalance);
			int r2 = baseMapper.updateWithdraw(withdrawId, status);//更新提现表状态以及相关信息
			if (r1 > 0 && r2 > 0) {
				return 1;
			}
		}
		return 0;
	}
	
	//当前提现申请的信息
	@Override
	public AdminWithdrawEntity selectAmountMessage(Long withdrawId) {
		return baseMapper.selectAmountMessage(withdrawId);
	}
	
	//商户提现列表
	@Override
	public PageUtils<AdminWithdrawEntity> mechanList(Map<String, Object> params) {
		int page = Integer.parseInt(params.get("page").toString());
		int size = Integer.parseInt(params.get("size").toString());
		int start = (page - 1) * size;
		params.put("start", start);
		params.put("size", size);
		List<AdminWithdrawEntity> list = baseMapper.mechanlist(params);
		int count = baseMapper.mechanlistCount(params);
		PageUtils<AdminWithdrawEntity> pageUtils = new PageUtils(list, count, page, size);
		return pageUtils;
	}
}
























