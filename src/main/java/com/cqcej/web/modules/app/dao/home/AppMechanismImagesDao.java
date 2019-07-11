package com.cqcej.web.modules.app.dao.home;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.app.entity.home.AppMechanismImagesEntity;
import com.cqcej.web.modules.common.entity.MechanismImagesEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 机构轮播
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-20 11:10:15
 */
@Mapper
public interface AppMechanismImagesDao extends BaseMapper<MechanismImagesEntity> {
	
	List<AppMechanismImagesEntity> getMechanismBanners(long mechanismId);
}
