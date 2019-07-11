package com.cqcej.web.modules.app.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.modules.app.service.AppUserPriseService;
import com.cqcej.web.modules.common.dao.UserPriseDao;
import com.cqcej.web.modules.common.entity.UserPriseEntity;
import org.springframework.stereotype.Service;


@Service("appUserPriseService")
public class AppUserPriseServiceImpl extends ServiceImpl<UserPriseDao, UserPriseEntity> implements AppUserPriseService {
	
	@Override
	public boolean isPrised(long userId, long objectId, int type) {
		EntityWrapper<UserPriseEntity> wrapper = new EntityWrapper<>();
		wrapper.where("object_id={0} and user_id={1} and prise_type={2}", objectId, userId, type);
		int count = selectCount(wrapper);
		return count > 0;
	}
}
