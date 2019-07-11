package com.cqcej.web.modules.app.service;


import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.modules.app.entity.dto.MechUserCardsDTO;
import com.cqcej.web.modules.app.entity.user.AppUserEntity;
import com.cqcej.web.modules.app.form.LoginForm;
import com.cqcej.web.modules.common.entity.UserEntity;

import java.util.List;

/**
 * 用户
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-03-23 15:22:06
 */
public interface AppUserService extends IService<UserEntity> {
	UserEntity queryByMobileOrEmail(String account);
	
	/**
	 * 用户登录
	 *
	 * @param form 登录表单
	 * @return 返回用户ID
	 */
	AppUserEntity login(LoginForm form);
	
	AppUserEntity getUserInfo(long userId);
	
	boolean saveUserNickname(long userId, String nickname);
	
	boolean saveSex(long userId, Integer sex);
	
	boolean saveSignature(long userId, String signature);
	
	boolean saveCity(long userId, Integer provinceId, Integer cityId, Integer areaId);
	
	boolean bindMobile(long userId, String mobile);
	
	@Deprecated
	List<MechUserCardsDTO> selectCardList(Long userId);
	
	boolean isWatch(Long userId,Long targetId);

	boolean isSign(Long userId);
}
