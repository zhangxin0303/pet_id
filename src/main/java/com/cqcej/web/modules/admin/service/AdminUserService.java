package com.cqcej.web.modules.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.admin.entity.AdminAreaEntity;
import com.cqcej.web.modules.admin.entity.AdminUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户
 *
 * @author Jia Min
 * @email empty
 * @date 2018-07-23 15:19:56
 */
public interface AdminUserService extends IService<AdminUserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    //用户列表
    PageUtils<AdminUserEntity> getUserList(Integer forbiddenComment,Long userId,String nickname,Integer page,Integer size);

    //查看user信息
    AdminUserEntity selectUserById(Long userId);

    //删除user
    int deleteUserById(Long userId);

    //修改用户信息
    int updateUserById(AdminUserEntity user);

    //查询省份信息
    List<AdminAreaEntity> selectProvinces();

    //查询城市
    List<AdminAreaEntity> selectCity(Integer provinceId);

    //查询区县
    List<AdminAreaEntity> selectArea(Integer cityId);
}

