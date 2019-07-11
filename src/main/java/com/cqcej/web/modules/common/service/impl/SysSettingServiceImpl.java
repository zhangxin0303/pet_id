package com.cqcej.web.modules.common.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.modules.common.dao.SysSettingDao;
import com.cqcej.web.modules.common.entity.SysSettingEntity;
import com.cqcej.web.modules.common.service.SysSettingService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("sysSettingService")
public class SysSettingServiceImpl extends ServiceImpl<SysSettingDao, SysSettingEntity> implements SysSettingService {
	
	@Override
	public List<SysSettingEntity> getSetting() {
		EntityWrapper<SysSettingEntity> wrapper = new EntityWrapper<>();
		
		return baseMapper.selectList(wrapper);
	}
	
	@Override
	public String findByKey(String key) {
		return baseMapper.findByKey(key);
	}
}
