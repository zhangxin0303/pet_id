package com.cqcej.web.modules.admin.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.MapUtils;
import com.cqcej.web.modules.admin.dao.SysUserRoleDao;
import com.cqcej.web.modules.admin.entity.SysUserRoleEntity;
import com.cqcej.web.modules.admin.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 用户与角色对应关系
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2016年9月18日 上午9:45:48
 */
@Service("sysUserRoleService")
public class UserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRoleEntity> implements UserRoleService {
	
	@Override
	public void saveOrUpdate(Long userId, List<Long> roleIdList) {
		//先删除用户与角色关系
		this.deleteByMap(new MapUtils().put("user_id", userId));
		
		if (roleIdList == null || roleIdList.size() == 0) {
			return;
		}
		
		//保存用户与角色关系
		List<SysUserRoleEntity> list = new ArrayList<>(roleIdList.size());
		for (Long roleId : roleIdList) {
			SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();
			sysUserRoleEntity.setUserId(userId);
			sysUserRoleEntity.setRoleId(roleId);
			
			list.add(sysUserRoleEntity);
		}
		this.insertBatch(list);
	}
	
	@Override
	public List<Long> queryRoleIdList(Long userId) {
		return baseMapper.queryRoleIdList(userId);
	}
	
	@Override
	public int deleteBatch(Long[] roleIds) {
		return baseMapper.deleteBatch(roleIds);
	}
}
