package com.cqcej.web.modules.common.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.common.entity.SysSettingEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统设置
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-24 10:17
 */
@Mapper
public interface SysSettingDao extends BaseMapper<SysSettingEntity> {

	String findByKey(String key);
}
