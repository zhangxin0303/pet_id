package com.cqcej.web.modules.common.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.exception.CTException;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.Query;
import com.cqcej.web.modules.app.form.ChangePasswordForm;
import com.cqcej.web.modules.common.dao.UserDao;
import com.cqcej.web.modules.common.entity.UserEntity;
import com.cqcej.web.modules.common.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<UserEntity> page = this.selectPage(
				new Query<UserEntity>(params).getPage(),
				new EntityWrapper<>()
		);
		
		return new PageUtils<>(page);
	}
	
	@Override
	public UserEntity getUserProfile(Long userId) {
		return baseMapper.selectById(userId);
	}
	
	@Override
	public boolean changePassword(long userId, ChangePasswordForm form) {
		UserEntity user = selectById(userId);
		
		if (!user.getPassword().equals(DigestUtils.sha256Hex(form.getOriginPassword()))) {
			// 原密码错误
			return false;
		}
		
		UserEntity update = new UserEntity();
		update.setUserId(userId);
		update.setPassword(DigestUtils.sha256Hex(form.getNewPassword()));
		return updateById(update);
	}
	
	@Override
	public BigDecimal getTodayIncome(long userId) {
		return new BigDecimal(10);
	}
	
	/**
	 * 扣除提现金额
	 */
	@Override
	public BigDecimal reduceAmount(Long userId, Double amount) {
		UserEntity user = selectById(userId);
		if (user.getBalance().compareTo(new BigDecimal(amount)) < 0) {
			return new BigDecimal(-1);
		}
		
		UserEntity update = new UserEntity();
		update.setBalance(new BigDecimal(user.getBalance().doubleValue() - amount));
		update.setUserId(userId);
		if (baseMapper.updateById(update) > 0) {
			return update.getBalance();
		} else {
			return new BigDecimal(0);
		}
	}
	
	@Override
	public boolean changeType(long userId, Integer type) {
		UserEntity user = new UserEntity();
		user.setUserId(userId);
		user.setUserType(type);
		return baseMapper.updateById(user) > 0;
	}
	
	@Override
	public void reduceScore(Long userId, Integer scoreDiscount) {
		UserEntity user = selectById(userId);
		if (user.getScore() < scoreDiscount) {
			throw new CTException("积分不足！");
		}
		
		UserEntity update = new UserEntity();
		update.setUserId(user.getUserId());
		update.setScore(user.getScore() - scoreDiscount);
		updateById(update);
	}
}
