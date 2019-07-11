package com.cqcej.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * DESCRIPTION
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-04 上午12:14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailTest {
	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
	@Autowired
	private JavaMailSender mailSender; //自动注入的Bean
	
	@Value("${spring.mail.username}")
	private String Sender; //读取配置文件中的参数
	
	@Test
	public void sendSimpleMail() throws Exception {
		long time = new Date().getTime();
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(Sender);
		message.setTo("503580622@qq.com"); //自己给自己发送邮件
		message.setSubject("主题：简单邮件");
		message.setText("测试邮件内容");
		mailSender.send(message);
		System.out.println(new Date().getTime() - time);
	}
}
