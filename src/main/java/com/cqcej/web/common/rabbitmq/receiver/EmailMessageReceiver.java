package com.cqcej.web.common.rabbitmq.receiver;

/**
 * 邮件消息接收者
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-04 14:35
 */
import com.cqcej.web.common.email.EmailMessageEntity;
import com.cqcej.web.common.utils.ConfigConstant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = ConfigConstant.RABBIT_MQ_EMAIL_QUEUE_NAME)
public class EmailMessageReceiver {
	
	// private final EmailService emailService;
	
	@RabbitHandler
	public void process(String hello) {
		System.out.println("Receiver1  : " + hello);
	}
	
	@RabbitHandler
	public void process(EmailMessageEntity message) {
//		String[] to = new String[message.getTo().size()];
//		message.getTo().toArray(to);
//		emailService.sendSimpleMail(message.getSubject(), message.getContent(), to);
	}
}
