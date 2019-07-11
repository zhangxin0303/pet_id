package com.cqcej.web.modules.admin.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.admin.dao.AdminSysBannerDao;
import com.cqcej.web.modules.admin.entity.AdminSysBannerEntity;
import com.cqcej.web.modules.admin.service.AdminSysBannerService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("adminSysBannerService")
public class AdminSysBannerServiceImpl extends ServiceImpl<AdminSysBannerDao, AdminSysBannerEntity> implements AdminSysBannerService {


    @Override
    public PageUtils<AdminSysBannerEntity> getList(Integer page,Integer size) {
        int start  = (page -1) *size;
        int count = baseMapper.getListCount();
        List<AdminSysBannerEntity> list = baseMapper.getList(start,size);
	    PageUtils<AdminSysBannerEntity> data = new PageUtils(list,count,page,size);
        return data;
    }
    
    @Override
    public AdminSysBannerEntity selectBanner(Integer bannerId) {
        return baseMapper.selectBanner(bannerId);
    }
    
    @Override
    public int remove(Integer id) {
        return baseMapper.remove(id);
    }

    @Override
    public int insertBanner(List<AdminSysBannerEntity> bannerList) {
        return baseMapper.insertBanner(bannerList);
    }
    
    @Override
    public int updateBanner(AdminSysBannerEntity banner) {
        return baseMapper.updateBanner(banner);
    }
    
    @Override
    public int updateStatus(Integer bannerId, Integer status) {
        return baseMapper.updateStatus(bannerId,status);
    }
}
