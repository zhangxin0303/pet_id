package com.cqcej.web.modules.admin.service;


import com.cqcej.web.modules.admin.entity.SysUserEntity;
import com.cqcej.web.modules.admin.entity.SysUserTokenEntity;

import java.util.Set;

/**
 * shiro相关接口
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-06-06 8:49
 */
public interface ShiroService {
	/**
	 * 获取用户权限列表
	 */
	Set<String> getUserPermissions(long userId);
	
	SysUserTokenEntity queryByToken(String token);
	
	/**
	 * 根据用户ID，查询用户
	 *
	 * @param userId
	 */
	SysUserEntity queryUser(Long userId);
}
