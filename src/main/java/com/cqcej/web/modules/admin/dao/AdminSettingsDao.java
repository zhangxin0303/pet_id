package com.cqcej.web.modules.admin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.admin.entity.AdminSettingsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 设置表
 *
 * @author Jia Min
 * @email Empty
 * @date 2018-08-14 16:08:29
 */
@Mapper
public interface AdminSettingsDao extends BaseMapper<AdminSettingsEntity> {
	
	List<AdminSettingsEntity> findAll(@Param("s") String[] s);
	
	int updatePriceSettings(AdminSettingsEntity setting);
	
	String findSettingByKey(@Param("settingKey") String key);
	
	int updateForJson(AdminSettingsEntity set);
}
