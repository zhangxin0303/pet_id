package com.cqcej.web.modules.admin.service.impl;

import com.cqcej.web.common.utils.AdminConstant;
import com.cqcej.web.modules.admin.dao.SysMenuDao;
import com.cqcej.web.modules.admin.dao.SysUserDao;
import com.cqcej.web.modules.admin.dao.SysUserTokenDao;
import com.cqcej.web.modules.admin.entity.SysMenuEntity;
import com.cqcej.web.modules.admin.entity.SysUserEntity;
import com.cqcej.web.modules.admin.entity.SysUserTokenEntity;
import com.cqcej.web.modules.admin.service.ShiroService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShiroServiceImpl implements ShiroService {
	@Autowired
	private SysMenuDao sysMenuDao;
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysUserTokenDao sysUserTokenDao;
	
	@Override
	public Set<String> getUserPermissions(long userId) {
		List<String> permsList;
		
		//系统管理员，拥有最高权限
		if (userId == AdminConstant.SUPER_ADMIN) {
			List<SysMenuEntity> menuList = sysMenuDao.selectList(null);
			permsList = new ArrayList<>(menuList.size());
			for (SysMenuEntity menu : menuList) {
				permsList.add(menu.getPerms());
			}
		} else {
			permsList = sysUserDao.queryAllPerms(userId);
		}
		//用户权限列表
		Set<String> permsSet = new HashSet<>();
		for (String perms : permsList) {
			if (StringUtils.isBlank(perms)) {
				continue;
			}
			permsSet.addAll(Arrays.asList(perms.trim().split(",")));
		}
		return permsSet;
	}
	
	@Override
	public SysUserTokenEntity queryByToken(String token) {
		return sysUserTokenDao.queryByToken(token);
	}
	
	@Override
	public SysUserEntity queryUser(Long userId) {
		return sysUserDao.selectById(userId);
	}
}