package com.cqcej.web.modules.common.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.modules.app.utils.AppPage;
import com.cqcej.web.modules.common.dao.MessageDao;
import com.cqcej.web.modules.common.entity.MessageEntity;
import com.cqcej.web.modules.common.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("messageService")
public class MessageServiceImpl extends ServiceImpl<MessageDao, MessageEntity> implements MessageService {
	
	@Override
	public AppPage<MessageEntity> getUserMessage(long userId, Integer page, Integer size) {
		int count = baseMapper.getUserMessageCount(userId);
		
		int start = (page - 1) * size;
		List<MessageEntity> lists = baseMapper.getUserMessage(userId, start, size);
		
		return new AppPage<>(count, size, lists);
	}
}
