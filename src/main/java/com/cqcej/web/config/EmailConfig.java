package com.cqcej.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 邮箱配置
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-04 13:47
 */
@Component
public class EmailConfig {
	/**
	 * 发件邮箱
	 */
	@Value("${spring.mail.username}")
	private String emailFrom;
	
	public String getEmailFrom() {
		return emailFrom;
	}
	
	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}
}