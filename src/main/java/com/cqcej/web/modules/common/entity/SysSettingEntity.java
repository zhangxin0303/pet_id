package com.cqcej.web.modules.common.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * 系统设置
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-24 10:15
 */
@TableName("ct_settings")
public class SysSettingEntity implements Serializable {
	public String getSettingKey() {
		return settingKey;
	}
	
	public void setSettingKey(String settingKey) {
		this.settingKey = settingKey;
	}
	
	private String settingKey;
	
	private String settingValue;
	
	private String description;
	
	public String getSettingValue() {
		return settingValue;
	}
	
	public void setSettingValue(String settingValue) {
		this.settingValue = settingValue;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
