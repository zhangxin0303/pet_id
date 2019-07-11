package com.cqcej.web.modules.common.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.Query;
import com.cqcej.web.modules.common.dao.UserWatchDao;
import com.cqcej.web.modules.common.entity.UserWatchEntity;
import com.cqcej.web.modules.common.service.UserWatchService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("userWatchService")
public class UserWatchServiceImpl extends ServiceImpl<UserWatchDao, UserWatchEntity> implements UserWatchService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<UserWatchEntity> page = this.selectPage(
				new Query<UserWatchEntity>(params).getPage(),
				new EntityWrapper<>()
		);

		return new PageUtils(page);
	}
	
	@Override
	public boolean watchUser(long userId, Long targetId) {
		EntityWrapper<UserWatchEntity> where = new EntityWrapper<>();
		where.where("user_id={0} and watch_user_id={1}", userId, targetId);
		
		UserWatchEntity watchEntity = selectOne(where);
		if (watchEntity != null) {
			// 取消关注
			deleteById(watchEntity.getWatchId());
			return false;
		} else {
			// 添加关注
			UserWatchEntity data = new UserWatchEntity();
			data.setUserId(userId);
			data.setWatchUserId(targetId);
			insert(data);
			return true;
		}
	}
	
}
