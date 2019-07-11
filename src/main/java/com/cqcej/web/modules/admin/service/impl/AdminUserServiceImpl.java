package com.cqcej.web.modules.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.Query;
import com.cqcej.web.modules.admin.dao.AdminUserDao;
import com.cqcej.web.modules.admin.entity.AdminAreaEntity;
import com.cqcej.web.modules.admin.entity.AdminUserEntity;
import com.cqcej.web.modules.admin.service.AdminUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("adminUserService")
public class AdminUserServiceImpl extends ServiceImpl<AdminUserDao, AdminUserEntity> implements AdminUserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AdminUserEntity> page = this.selectPage(
                new Query<AdminUserEntity>(params).getPage(),
                new EntityWrapper<AdminUserEntity>()
        );

        return new PageUtils(page);
    }

    //用户列表
    @Override
    public PageUtils<AdminUserEntity> getUserList(Integer forbiddenComment, Long userId, String nickname, Integer page, Integer size) {
        int start = (page - 1) * size;
        List<AdminUserEntity> list = baseMapper.getUserList(forbiddenComment,userId,nickname,start,size);
        int count = baseMapper.getUserCount(forbiddenComment,userId,nickname);
        PageUtils<AdminUserEntity> pageUtils = new PageUtils(list,count,page,size);
        return pageUtils;
    }

    @Override
    public AdminUserEntity selectUserById(Long userId) {
        return baseMapper.selectUserById(userId);
    }

    @Override
    public int deleteUserById(Long userId) {
       return baseMapper.deleteUserById(userId);
    }

    @Override
    public int updateUserById(AdminUserEntity user) {
        return baseMapper.updateUserById(user);
    }

    @Override
    public List<AdminAreaEntity> selectProvinces() {
        return baseMapper.selectProvinces();
    }

    @Override
    public List<AdminAreaEntity> selectCity(Integer provinceId) {
        return baseMapper.selectCity(provinceId);
    }

    @Override
    public List<AdminAreaEntity> selectArea(Integer cityId) {
        return baseMapper.selectArea(cityId);
    }
}
