package com.cqcej.web.modules.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.admin.entity.AdminSysRoleEntity;
import com.cqcej.web.modules.admin.entity.userRoleMenu.SetMenuEntity;
import com.cqcej.web.modules.admin.entity.userRoleMenu.SetRoleEntity;

import java.util.List;
import java.util.Map;

/**
 * 角色
 *
 * @author Jia Min
 * @email Empty
 * @date 2018-08-01 10:26:17
 */
public interface AdminSysRoleService extends IService<AdminSysRoleEntity> {

	PageUtils queryPage(Map<String, Object> params);
	
	PageUtils<AdminSysRoleEntity> getRoleList(String roleType, String userName, Long userId,Integer page,Integer size);

	int deleteBySysUserRoleId(Long id);

	List<AdminSysRoleEntity> select();

	AdminSysRoleEntity selectRoleById(Long id);
	
	int saveRole(Map<String,Object> map);

	int reset(Long userId,String password);
	
	int stopUser(Long userId,Integer status);
	
	int stopRole(Long roleId,Integer status);
	
	List<SetRoleEntity> selectUserRole(Long userId);
	
	int saveUserRoles(List<Long> roleIds,Long userId);
	
	PageUtils<SetRoleEntity> selectAll(Integer page,Integer size,String roleName);
	
	List<SetRoleEntity> selectRoles();
	
	List<SetMenuEntity> selectMenus();
	
	int deleteBySysRoleId(Long roleId);
	
	List<SetMenuEntity> selectRoleMenus(Long roleId);
	
	int saveRoleMenus(SetRoleEntity role);
}

