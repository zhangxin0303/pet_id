package com.cqcej.web.modules.app.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.app.entity.AppUserFootprintEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户足迹
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-31 15:59:32
 */
@Mapper
public interface AppUserFootprintDao extends BaseMapper<AppUserFootprintEntity> {
	
	List<AppUserFootprintEntity> getUserFootprint(long userId);
}
