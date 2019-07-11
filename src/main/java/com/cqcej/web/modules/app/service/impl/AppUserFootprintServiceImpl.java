package com.cqcej.web.modules.app.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.modules.app.dao.AppUserFootprintDao;
import com.cqcej.web.modules.app.entity.AppUserFootprintEntity;
import com.cqcej.web.modules.app.service.AppUserFootprintService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("appUserFootprintService")
public class AppUserFootprintServiceImpl extends ServiceImpl<AppUserFootprintDao, AppUserFootprintEntity> implements AppUserFootprintService {
	
	@Override
	public List<AppUserFootprintEntity> getUserFootprint(long userId) {
		return baseMapper.getUserFootprint(userId);
	}
	
	@Override
	public boolean clearUserFootprint(long userId) {
		EntityWrapper<AppUserFootprintEntity> where = new EntityWrapper<>();
		where.where("user_id={0}", userId);
		return baseMapper.delete(where) > 0;
	}
}
