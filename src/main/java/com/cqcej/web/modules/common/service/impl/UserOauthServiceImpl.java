package com.cqcej.web.modules.common.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.modules.app.entity.user.AppUserEntity;
import com.cqcej.web.modules.app.form.OauthLoginForm;
import com.cqcej.web.modules.app.service.AppUserService;
import com.cqcej.web.modules.app.utils.AppConstant;
import com.cqcej.web.modules.common.dao.UserOauthDao;
import com.cqcej.web.modules.common.entity.UserEntity;
import com.cqcej.web.modules.common.entity.UserOauthEntity;
import com.cqcej.web.modules.common.service.UserOauthService;
import com.cqcej.web.modules.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;


@Service("userOauthService")
public class UserOauthServiceImpl extends ServiceImpl<UserOauthDao, UserOauthEntity> implements UserOauthService {
	
	@Autowired
	private UserService userService;
	@Autowired
	private AppUserService appUserService;
	
	@Override
	public AppUserEntity login(OauthLoginForm form, String deviceOs) {
		EntityWrapper<UserOauthEntity> where = new EntityWrapper<>();
		where.where("unique_id={0}", form.getUid());
		UserOauthEntity oauth = selectOne(where);
		
		if (oauth == null) {
			// 未绑定，首先创建对应用户，在创建第三方登录数据
			AppUserEntity user = new AppUserEntity();
			user.setAvatar(form.getAvatar());
			user.setNickname(form.getNickname());
			user.setClient(deviceOs);       // 设置注册的设备(Android/iOS)
			user.setDeviceToken(form.getDeviceToken());
			
			if (form.getClient().equals(AppConstant.ClientMain)) {
				user.setUserType(UserEntity.USER_TYPE_NORMAL);
			}
			
			// 性别
			user.setSex(genderToIdentity(form.getGender()));
			userService.insert(user);
			
			// 设置初始属性
			user.setInitIm(0);
			user.setPets(new ArrayList<>());
			
			UserOauthEntity newOauth = new UserOauthEntity();
			newOauth.setUniqueId(form.getUid());
			newOauth.setUserId(user.getUserId());
			newOauth.setCreateAt(new Date());
			insert(newOauth);
			
			return appUserService.getUserInfo(user.getUserId());
		} else {
			// 已绑定，获取用户信息
			return appUserService.getUserInfo(oauth.getUserId());
		}
	}
	
	/**
	 * 性别文字转数字
	 */
	private Integer genderToIdentity(String gender) {
		if (gender.equals("男")) {
			return 1;
		} else if (gender.equals("女")) {
			return 2;
		}
		
		return null;
	}
}
