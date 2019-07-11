package com.cqcej.web.modules.admin.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.modules.admin.dao.SysRoleMenuDao;
import com.cqcej.web.modules.admin.entity.SysRoleMenuEntity;
import com.cqcej.web.modules.admin.service.RoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * 角色与菜单对应关系
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2016年9月18日 上午9:44:35
 */
@Service("sysRoleMenuService")
public class RoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenuEntity> implements RoleMenuService {
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
		//先删除角色与菜单关系
		deleteBatch(new Long[]{roleId});
		
		if (menuIdList.size() == 0) {
			return;
		}
		
		//保存角色与菜单关系
		List<SysRoleMenuEntity> list = new ArrayList<>(menuIdList.size());
		for (Long menuId : menuIdList) {
			SysRoleMenuEntity sysRoleMenuEntity = new SysRoleMenuEntity();
			sysRoleMenuEntity.setMenuId(menuId);
			sysRoleMenuEntity.setRoleId(roleId);
			
			list.add(sysRoleMenuEntity);
		}
		this.insertBatch(list);
	}
	
	@Override
	public List<Long> queryMenuIdList(Long roleId) {
		return baseMapper.queryMenuIdList(roleId);
	}
	
	@Override
	public int deleteBatch(Long[] roleIds) {
		return baseMapper.deleteBatch(roleIds);
	}
	
}
