package com.cqcej.web.common.rabbitmq.sender;

import com.cqcej.web.common.email.EmailMessageEntity;
import com.cqcej.web.common.utils.ConfigConstant;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 邮件消息发送者
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-04 14:31
 */
@Component
public class EmailMessageSender {
	
	private final AmqpTemplate rabbitTemplate;
	
	@Autowired
	public EmailMessageSender(AmqpTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void send() {
		String sendMsg = "hello1 " + new Date();
		System.out.println("Sender1 : " + sendMsg);
		this.rabbitTemplate.convertAndSend(ConfigConstant.RABBIT_MQ_EMAIL_QUEUE_NAME, sendMsg);
	}
	
	public void send(EmailMessageEntity message) {
		rabbitTemplate.convertAndSend(ConfigConstant.RABBIT_MQ_EMAIL_QUEUE_NAME, message);
	}
}
