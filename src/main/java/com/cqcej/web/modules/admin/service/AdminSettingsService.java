package com.cqcej.web.modules.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.modules.admin.entity.AdminSettingsEntity;

import java.util.List;
import java.util.Map;

/**
 * 设置表
 *
 * @author Jia Min
 * @email Empty
 * @date 2018-08-14 16:08:29
 */
public interface AdminSettingsService extends IService<AdminSettingsEntity> {

	List<AdminSettingsEntity> findAll(String[] settings);
	
	int updatePriceSettings(Map<String,String> map);
	
	String findSettingByKey(String key);
	
	int updateOtherSettings(Map<String,Object> map);
	
	int updateForJson(AdminSettingsEntity set);
}

