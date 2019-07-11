package com.cqcej.web.modules.common.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.modules.app.entity.user.AppUserEntity;
import com.cqcej.web.modules.app.form.OauthLoginForm;
import com.cqcej.web.modules.common.entity.UserOauthEntity;

/**
 * 第三方登录
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-10-12 11:07:27
 */
public interface UserOauthService extends IService<UserOauthEntity> {
	
	
	AppUserEntity login(OauthLoginForm form, String deviceOs);
}

