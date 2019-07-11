package com.cqcej.web.modules.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.exception.CTException;
import com.cqcej.web.common.utils.AdminConstant;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.Query;
import com.cqcej.web.modules.admin.dao.SysRoleDao;
import com.cqcej.web.modules.admin.entity.SysRoleEntity;
import com.cqcej.web.modules.admin.service.RoleMenuService;
import com.cqcej.web.modules.admin.service.RoleService;
import com.cqcej.web.modules.admin.service.UserRoleService;
import com.cqcej.web.modules.admin.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 角色
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2016年9月18日 上午9:45:12
 */
@Service("sysRoleService")
public class RoleServiceImpl extends ServiceImpl<SysRoleDao, SysRoleEntity> implements RoleService {
	@Autowired
	private RoleMenuService roleMenuService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserRoleService userRoleService;
	
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		String roleName = (String) params.get("roleName");
		Long createUserId = (Long) params.get("createUserId");
		
		Page<SysRoleEntity> page = this.selectPage(
				new Query<SysRoleEntity>(params).getPage(),
				new EntityWrapper<SysRoleEntity>()
						.like(StringUtils.isNotBlank(roleName), "role_name", roleName)
						.eq(createUserId != null, "create_user_id", createUserId)
		);
		
		return new PageUtils(page);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(SysRoleEntity role) {
		role.setCreateTime(new Date());
		this.insert(role);
		
		//检查权限是否越权
		checkPrems(role);
		
		//保存角色与菜单关系
		roleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SysRoleEntity role) {
		this.updateById(role);
		
		//检查权限是否越权
		checkPrems(role);
		
		//更新角色与菜单关系
		roleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteBatch(Long[] roleIds) {
		//删除角色
		this.deleteBatchIds(Arrays.asList(roleIds));
		
		//删除角色与菜单关联
		roleMenuService.deleteBatch(roleIds);
		
		//删除角色与用户关联
		userRoleService.deleteBatch(roleIds);
	}
	
	
	@Override
	public List<Long> queryRoleIdList(Long createUserId) {
		return baseMapper.queryRoleIdList(createUserId);
	}
	
	/**
	 * 检查权限是否越权
	 */
	private void checkPrems(SysRoleEntity role) {
		//如果不是超级管理员，则需要判断角色的权限是否超过自己的权限
		if (role.getCreateUserId() == AdminConstant.SUPER_ADMIN) {
			return;
		}
		
		//查询用户所拥有的菜单列表
		List<Long> menuIdList = userService.queryAllMenuId(role.getCreateUserId());
		
		//判断是否越权
		if (!menuIdList.containsAll(role.getMenuIdList())) {
			throw new CTException("新增角色的权限，已超出你的权限范围");
		}
	}
}
