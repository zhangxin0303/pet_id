package com.cqcej.web.modules.common.service;


import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.app.form.ChangePasswordForm;
import com.cqcej.web.modules.common.entity.UserEntity;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 用户
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-03-23 15:22:06
 */
public interface UserService extends IService<UserEntity> {
	PageUtils queryPage(Map<String, Object> params);
	
	UserEntity getUserProfile(Long userId);
	
	boolean changePassword(long userId, ChangePasswordForm form);
	
	/**
	 * 今日收入
	 * @param userId
	 * @return
	 */
	BigDecimal getTodayIncome(long userId);
	
	BigDecimal reduceAmount(Long userId, Double amount);
	
	boolean changeType(long userId, Integer type);
	
	void reduceScore(Long userId, Integer scoreDiscount);
}
