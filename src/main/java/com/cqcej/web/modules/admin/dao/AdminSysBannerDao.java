package com.cqcej.web.modules.admin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.admin.entity.AdminSysBannerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 轮播图
 *
 * @author Jia Min
 * @email Empty
 * @date 2018-08-10 16:27:30
 */
@Mapper
public interface AdminSysBannerDao extends BaseMapper<AdminSysBannerEntity> {

    List<AdminSysBannerEntity> getList(@Param("start")Integer start,@Param("size")Integer size);
    
    AdminSysBannerEntity selectBanner(Integer bannerId);

    int getListCount();
    
    int remove(@Param("bannerId") Integer id);

    int insertBanner(List<AdminSysBannerEntity> bannerList);

    int updateBanner(AdminSysBannerEntity banner);
    
    int updateStatus(@Param("bannerId")Integer bannerId,@Param("status")Integer status);
}
