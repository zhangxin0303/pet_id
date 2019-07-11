package com.cqcej.web.modules.common.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.Query;
import com.cqcej.web.modules.common.dao.UserBlacklistDao;
import com.cqcej.web.modules.common.entity.UserBlacklistEntity;
import com.cqcej.web.modules.common.service.UserBlacklistService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("userBlacklistService")
public class UserBlacklistServiceImpl extends ServiceImpl<UserBlacklistDao, UserBlacklistEntity> implements UserBlacklistService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<UserBlacklistEntity> page = this.selectPage(
				new Query<UserBlacklistEntity>(params).getPage(),
				new EntityWrapper<>()
		);

		return new PageUtils(page);
	}
	
	@Override
	public List<UserBlacklistEntity> getUserBlacklist(long userId) {
		Map<String, Object> where = new HashMap<String, Object>() {{
			put("user_id", userId);
		}};
		return baseMapper.selectByMap(where);
	}
	
	@Override
	public boolean deleteUserBlacklist(long userId, String ids) {
		EntityWrapper<UserBlacklistEntity> where = new EntityWrapper<>();
		where.in("list_id", ids);
		return baseMapper.delete(where) > 0;
	}
}
