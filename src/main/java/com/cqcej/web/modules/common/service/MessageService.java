package com.cqcej.web.modules.common.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.modules.app.utils.AppPage;
import com.cqcej.web.modules.common.entity.MessageEntity;

/**
 * 消息
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-08-01 14:36:37
 */
public interface MessageService extends IService<MessageEntity> {

	AppPage<MessageEntity> getUserMessage(long userId, Integer page, Integer size);
}

