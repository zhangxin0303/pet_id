package com.cqcej.web.common.email;

import java.io.Serializable;
import java.util.List;

/**
 * 邮件消息实体类
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-04 14:55
 */
public class EmailMessageEntity implements Serializable {
	
	private String subject;
	
	private List<String> to;
	
	private String content;
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public List<String> getTo() {
		return to;
	}
	
	public void setTo(List<String> to) {
		this.to = to;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
}
