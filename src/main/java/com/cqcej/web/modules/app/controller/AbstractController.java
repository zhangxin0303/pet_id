package com.cqcej.web.modules.app.controller;

import com.cqcej.web.modules.app.entity.VerificationCodeEntity;
import com.cqcej.web.modules.app.service.AppVerificationCodeService;
import com.cqcej.web.modules.common.entity.MessageEntity;
import com.cqcej.web.modules.common.entity.UserMessageEntity;
import com.cqcej.web.modules.common.service.MessageService;
import com.cqcej.web.modules.common.service.UserMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * AppController公共组件
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-05 下午5:01
 */
public abstract class AbstractController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	AppVerificationCodeService appVerificationCodeService;
	
	@Autowired
	private UserMessageService userMessageService;
	
	@Autowired
	private MessageService messageService;
	
	/**
	 * 验证手机验证码
	 *
	 * @return boolean
	 */
	boolean verifyCode(String code, String account) {
		VerificationCodeEntity entity = appVerificationCodeService.queryByCode(code);
		return entity != null && entity.getAccount().equals(account) && (entity.getExpireTime().getTime() > new Date().getTime());
	}
	
	/**
	 * 发送站内消息
	 * @param userId 用户id
	 * @param type 消息类型
	 * @param objectId 对象id，比如评论通知，或者发布帖子被点赞等，则这是帖子ID，其他类似，纯通知该字段为0
	 * @param title 标题
	 * @param content 内容
	 * @param note 备注
	 */
	protected void sendMessage(Long userId, Short type, Long objectId, String title, String content, String note) {
		MessageEntity message = new MessageEntity();
		message.setObjectId(objectId);
		message.setMessageType(type);
		message.setMessageTitle(title);
		message.setMessageContent(content);
		message.setNote(note);
		message.setCreateAt(new Date());
		// insert
		messageService.insert(message);
		
		// 添加用户-消息中间表
		UserMessageEntity userMessage = new UserMessageEntity();
		userMessage.setUserId(userId);
		userMessage.setIsRead(0);
		userMessage.setMessageId(message.getMessageId());
		// insert
		userMessageService.insert(userMessage);
	}
	
	/**
	 * 发送系统消息
	 * @param userId
	 * @param objectId
	 * @param title
	 * @param content
	 */
	protected void sendSystemMessage(Long userId, Long objectId, String title, String content, String note) {
		 sendMessage(userId, MessageEntity.MESSAGE_TYPE_SYSTEM, objectId, title, content, note);
	}
}
