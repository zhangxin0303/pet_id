package com.cqcej.web.modules.common.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.modules.common.dao.UserMessageDao;
import com.cqcej.web.modules.common.entity.UserMessageEntity;
import com.cqcej.web.modules.common.service.UserMessageService;
import org.springframework.stereotype.Service;


@Service("userMessageService")
public class UserMessageServiceImpl extends ServiceImpl<UserMessageDao, UserMessageEntity> implements UserMessageService {
	
	@Override
	public boolean clearAll(long userId) {
		EntityWrapper<UserMessageEntity> where = new EntityWrapper<>();
		where.where("user_id={0}", userId);
		
		return baseMapper.delete(where) >= 0;
	}
	
	@Override
	public Integer getUnreadMessageCount(long userId) {
		EntityWrapper<UserMessageEntity> where = new EntityWrapper<>();
		where.where("user_id={0} and is_read=0", userId);
		
		return baseMapper.selectCount(where);
	}
	
	@Override
	public Boolean setAllMessageRead(long userId) {
		EntityWrapper<UserMessageEntity> where = new EntityWrapper<>();
		where.where("user_id={0} and is_read=0", userId);
		
		UserMessageEntity update = new UserMessageEntity();
		update.setIsRead(1);
		update(update, where);
		return true;
	}
}
