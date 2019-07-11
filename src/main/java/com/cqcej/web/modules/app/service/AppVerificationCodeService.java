package com.cqcej.web.modules.app.service;


import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.modules.app.entity.VerificationCodeEntity;

/**
 * 验证码
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-03-23 17:24:32
 */
public interface AppVerificationCodeService extends IService<VerificationCodeEntity> {
	
	VerificationCodeEntity queryByCode(String code);
	
	VerificationCodeEntity queryByAccount(String account);
	
	void clearOutOfDateCode();
}
