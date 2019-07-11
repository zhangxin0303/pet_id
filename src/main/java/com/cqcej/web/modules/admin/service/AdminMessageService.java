package com.cqcej.web.modules.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.admin.entity.AdminMessageEntity;

import java.util.List;
import java.util.Map;

/**
 * 消息
 *
 * @author Jia Min
 * @email Empty
 * @date 2018-08-27 11:37:02
 */
public interface AdminMessageService extends IService<AdminMessageEntity> {


	PageUtils<AdminMessageEntity> list(Map<String,Object> map);
	
	int deleteMessage(List<Long> messageIds);
	
	AdminMessageEntity getMessageById(Long messageId);
	
	int saveMessage(AdminMessageEntity message);
}

