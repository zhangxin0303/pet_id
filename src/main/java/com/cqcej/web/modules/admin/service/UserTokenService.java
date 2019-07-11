package com.cqcej.web.modules.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.common.utils.R;
import com.cqcej.web.modules.admin.entity.SysUserTokenEntity;

/**
 * 用户Token
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-03-23 15:22:07
 */
public interface UserTokenService extends IService<SysUserTokenEntity> {
	
	/**
	 * 生成token
	 *
	 * @param userId 用户ID
	 */
	R createToken(long userId);
	
	/**
	 * 退出，修改token值
	 *
	 * @param userId 用户ID
	 */
	void logout(long userId);
	
}
