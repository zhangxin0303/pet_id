package com.cqcej.web.modules.common.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.modules.common.entity.UserMessageEntity;

/**
 * 消息，用户中间表
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-08-01 14:36:37
 */
public interface UserMessageService extends IService<UserMessageEntity> {
	
	boolean clearAll(long userId);
	
	Integer getUnreadMessageCount(long userId);
	
	Boolean setAllMessageRead(long userId);
}

