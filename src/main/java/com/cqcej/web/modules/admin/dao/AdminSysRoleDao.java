package com.cqcej.web.modules.admin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.admin.entity.AdminSysRoleEntity;
import com.cqcej.web.modules.admin.entity.userRoleMenu.SetMenuEntity;
import com.cqcej.web.modules.admin.entity.userRoleMenu.SetRoleEntity;
import com.cqcej.web.modules.admin.entity.userRoleMenu.SetUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 角色
 *
 * @author Jia Min
 * @email Empty
 * @date 2018-08-01 10:26:17
 */
@Mapper
public interface AdminSysRoleDao extends BaseMapper<AdminSysRoleEntity> {


    List<AdminSysRoleEntity> getRoleList(@Param("roleType") String roleType,
                                         @Param("userName")String userName,
                                         @Param("userId")Long userId,
                                         @Param("start")Integer start,
                                         @Param("size")Integer size);

    Integer getRoleListCount();
    
    int deleteBySysUserRoleId(@Param("id")Long id);

    List<AdminSysRoleEntity> select();

    AdminSysRoleEntity selectRoleById(@Param("id")Long id);

    int updateRole(SetRoleEntity role);

    int updateSysUerRole(@Param("id")Long id,@Param("roleId")Long roleId);
    
    int saveRole(Map<String,Object> map);

    void saveUserRole(@Param("userId")Long userId,@Param("roleId")Long roleId);

    int reset(@Param("userId")Long userId,@Param("password")String password);
    
    int stopUser(@Param("userId")Long userId,@Param("status")Integer status);
    
    int stopRole(@Param("roleId")Long roleId,@Param("status")Integer status);
    
    List<SetUserEntity> selectUsers();
    
    List<SetRoleEntity> selectUserRole(@Param("userId")Long userId);
    
    List<SetRoleEntity> selectRoles(@Param("start")Integer start,@Param("size")Integer size,@Param("roleName")String roleName);
    
    Integer selectRolesCount(@Param("roleName")String roleName);
    
    List<SetRoleEntity> selectRole();
    
    int deleteUserRole(@Param("userId")Long userId);
    
    int saveUserRoles(@Param("roleIds") List<Long> roleIds,@Param("userId") Long userId);
    
    List<SetMenuEntity> selectRoleMenus(@Param("roleId")Long roleId);
    
    List<SetMenuEntity> selectMenus();
    
    List<SetMenuEntity> selectSubMenus(@Param("menuId") Long menuId);
    
    int deleteBySysRoleId(@Param("roleId") Long roleId);
    
    int deleteRoleMenus(@Param("roleId")Long roleId);
    
    int saveRoleMenus(@Param("menuIds") List<Long> menuIds,@Param("roleId") Long roleId);
}
