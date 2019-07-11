package com.cqcej.web.modules.admin.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.modules.admin.dao.AdminSettingsDao;
import com.cqcej.web.modules.admin.entity.AdminSettingsEntity;
import com.cqcej.web.modules.admin.service.AdminSettingsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Service("adminSettingsService")
public class AdminSettingsServiceImpl extends ServiceImpl<AdminSettingsDao, AdminSettingsEntity> implements AdminSettingsService {
	
	
	//所有配置
	@Override
	public List<AdminSettingsEntity> findAll(String[] s) {
		return baseMapper.findAll(s);
	}
	
	//查询单个配置
	
	
	//配置平台计价
	@Override
	public int updatePriceSettings(Map<String, String> map) {
		List<AdminSettingsEntity> list = new ArrayList<>();
		AdminSettingsEntity s1 = new AdminSettingsEntity();
		String priceType = map.get("priceType");//商户计价
		s1.setSettingKey("priceType");
		s1.setSettingValue(priceType);
		list.add(s1);
		String priceResult = map.get("priceResult");//结果
		AdminSettingsEntity s2 = new AdminSettingsEntity();
		if("1".equals(priceType)){
			s2.setSettingKey("priceRealPersent");
		}
		if("2".equals(priceType)){
			s2.setSettingKey("priceFormerPersent");
		}
		if("3".equals(priceType)){
			s2.setSettingKey("priceSettled");
		}
		s2.setSettingValue(priceResult);
		list.add(s2);
		AdminSettingsEntity s3 = new AdminSettingsEntity();
		String pickUpType = map.get("pickUpType");//接送计价
		s3.setSettingKey("pickUpType");
		s3.setSettingValue(pickUpType);
		list.add(s3);
		String pickUpResult = map.get("pickUpResult");//结果
		AdminSettingsEntity s4 = new AdminSettingsEntity();
		if("1".equals(pickUpType)){
			s4.setSettingKey("pickUpPersent");
		}
		if("2".equals(pickUpType)){
			s4.setSettingKey("pickUpSettled");
		}
		s4.setSettingValue(pickUpResult);
		list.add(s4);
		int result = 0;
		for (AdminSettingsEntity s: list) {
			result = baseMapper.updatePriceSettings(s);
		}
		return result;
	}
	
	@Override
	public String findSettingByKey(String key) {
		return baseMapper.findSettingByKey(key);
	}
	
	//修改其他配置
	@Override
	public int updateOtherSettings(Map<String, Object> map) {
		List<AdminSettingsEntity> list = new ArrayList<>();
		//获得map的迭代器转成list
		Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			AdminSettingsEntity setting = new AdminSettingsEntity();
			Map.Entry<String, Object> next = iterator.next();
			setting.setSettingKey(next.getKey());//获取key
			setting.setSettingValue(next.getValue().toString());//获取value
			list.add(setting);//封装成对象放入list
		}
		int result = 0;
		//循环修改配置
		for (AdminSettingsEntity s: list) {
			result = baseMapper.updatePriceSettings(s);
		}
		return result;
	}
	
	@Override
	public int updateForJson(AdminSettingsEntity set) {
		return baseMapper.updateForJson(set);
	}
}























