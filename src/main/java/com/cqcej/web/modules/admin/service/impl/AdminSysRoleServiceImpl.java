package com.cqcej.web.modules.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.Query;
import com.cqcej.web.modules.admin.dao.AdminSysRoleDao;
import com.cqcej.web.modules.admin.entity.AdminSysRoleEntity;
import com.cqcej.web.modules.admin.entity.userRoleMenu.SetMenuEntity;
import com.cqcej.web.modules.admin.entity.userRoleMenu.SetRoleEntity;
import com.cqcej.web.modules.admin.service.AdminSysRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("adminSysRoleService")
public class AdminSysRoleServiceImpl extends ServiceImpl<AdminSysRoleDao, AdminSysRoleEntity> implements AdminSysRoleService {
	
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<AdminSysRoleEntity> page = this.selectPage(
				new Query<AdminSysRoleEntity>(params).getPage(),
				new EntityWrapper<AdminSysRoleEntity>()
		);
		
		return new PageUtils(page);
	}
	
	//角色列表信息
	@Override
	public PageUtils<AdminSysRoleEntity> getRoleList(String roleType, String userName, Long userId,Integer page,Integer size) {
		int start = (page - 1) * size;
		List<AdminSysRoleEntity> list = baseMapper.getRoleList(roleType, userName, userId,start ,size);
		int count = baseMapper.getRoleListCount();
		PageUtils data = new PageUtils(list,count,page,size);
		return data;
	}
	
	//删除角色(从用户_角色中间表中删除数据)
	@Override
	public int deleteBySysUserRoleId(Long id) {
		return baseMapper.deleteBySysUserRoleId(id);
	}
	
	@Override
	public List<AdminSysRoleEntity> select() {
		return baseMapper.select();
	}
	
	@Override
	public AdminSysRoleEntity selectRoleById(Long id) {
		return baseMapper.selectRoleById(id);
	}
	
	
	//添加角色
	@Override
	public int saveRole(Map<String, Object> map) {
		return baseMapper.saveRole(map);
//		Long userId =  Long.valueOf(String.valueOf(map.get("userId")));
//		Long roleId =  Long.valueOf(String.valueOf(map.get("roleId")));
//		baseMapper.saveUserRole(userId,roleId);
	}
	
	@Override
	public int reset(Long userId, String password) {
		return baseMapper.reset(userId, password);
	}
	
	@Override
	public int stopUser(Long userId, Integer status) {
		return baseMapper.stopUser(userId, status);
	}
	
	@Override
	public int stopRole(Long roleId, Integer status) {
		return baseMapper.stopRole(roleId, status);
	}
	
	//用户角色菜单
	@Override
	public List<SetRoleEntity> selectUserRole(Long userId) {
		List<SetRoleEntity> roles = baseMapper.selectUserRole(userId);//用户对应的所有角色
		return roles;
	}
	
	//为用户分配角色
	@Override
	public int saveUserRoles(List<Long> roleIds, Long userId) {
		int result = 0;
		result = baseMapper.deleteUserRole(userId);//删除原用户的所有角色重新分配
		if (roleIds != null && roleIds.size() > 0) {//至少有一个角色
			result = baseMapper.saveUserRoles(roleIds, userId);//重新分配用户的角色
		}
		return result;
	}
	//系统所有角色
	@Override
	public PageUtils<SetRoleEntity> selectAll(Integer page,Integer size,String roleName) {
		int start = (page -1) * size;
		List<SetRoleEntity> roles = baseMapper.selectRoles(start,size,roleName);
		int count = baseMapper.selectRolesCount(roleName);
		PageUtils data = new PageUtils(roles,count,page,size);
		return data;
	}
	
	@Override
	public List<SetRoleEntity> selectRoles() {
		return baseMapper.selectRole();
	}
	
	//查询所有菜单
	@Override
	public List<SetMenuEntity> selectMenus() {
		List<SetMenuEntity> list = baseMapper.selectMenus();//一级菜单
		for (int i = 0; i < list.size(); i++) {//菜单的子菜单
			List<SetMenuEntity> subMenus = baseMapper.selectSubMenus(list.get(i).getMenuId());
			list.get(i).setMenus(subMenus);
		}
		return list;
	
	}
	
	@Override
	public int deleteBySysRoleId(Long roleId) {
		return baseMapper.deleteBySysRoleId(roleId);
	}
	
	//查询角色的所有菜单
	@Override
	public List<SetMenuEntity> selectRoleMenus(Long roleId) {
		return baseMapper.selectRoleMenus(roleId);
	}
	
	//为角色分配菜单
	@Override
	public int saveRoleMenus(SetRoleEntity role) {
		int result = 0;
		result = baseMapper.deleteRoleMenus(role.getRoleId());//删除原有用户的所有菜单重新分配
		if (role.getMenuIds() != null && role.getMenuIds().size() > 0) {//至少分配一个菜单
			result = baseMapper.saveRoleMenus(role.getMenuIds(), role.getRoleId());
		}
		//修改角色
		result = baseMapper.updateRole(role);
		return result;
	}
}




































