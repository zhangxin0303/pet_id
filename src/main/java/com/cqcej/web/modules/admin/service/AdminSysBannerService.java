package com.cqcej.web.modules.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.admin.entity.AdminSysBannerEntity;

import java.util.List;

/**
 * 轮播图
 *
 * @author Jia Min
 * @email Empty
 * @date 2018-08-10 16:27:30
 */
public interface AdminSysBannerService extends IService<AdminSysBannerEntity> {
    
    PageUtils<AdminSysBannerEntity> getList(Integer page, Integer size);

    AdminSysBannerEntity selectBanner(Integer bannerId);
    
    int remove(Integer id);

    int insertBanner(List<AdminSysBannerEntity> bannerList);

    int updateBanner(AdminSysBannerEntity banner);
    
    int updateStatus(Integer bannerId,Integer status);
}

