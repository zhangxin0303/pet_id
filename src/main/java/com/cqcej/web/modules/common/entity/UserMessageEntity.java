package com.cqcej.web.modules.common.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 消息，用户中间表
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-08-01 14:36:37
 */
@TableName("ct_user_message")
@ApiModel("消息，用户中间表")
public class UserMessageEntity implements Serializable {

	/**
	 * 
	 */
	@TableId
	@ApiModelProperty("")
	protected Long userMessageId;

	/**
	 * 用户ID
	 */
	@ApiModelProperty("用户ID")
	protected Long userId;

	/**
	 * 消息ID
	 */
	@ApiModelProperty("消息ID")
	protected Long messageId;

	/**
	 * 是否已读（0未读，1已读）
	 */
	@ApiModelProperty("是否已读（0未读，1已读）")
	protected Integer isRead;


	/**
	 * 设置：
	 */
	public void setUserMessageId(Long userMessageId) {
		this.userMessageId = userMessageId;
	}

	/**
	 * 获取：
	 */
	public Long getUserMessageId() {
		return userMessageId;
	}

	/**
	 * 设置：用户ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 获取：用户ID
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 设置：消息ID
	 */
	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	/**
	 * 获取：消息ID
	 */
	public Long getMessageId() {
		return messageId;
	}

	/**
	 * 设置：是否已读（0未读，1已读）
	 */
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	/**
	 * 获取：是否已读（0未读，1已读）
	 */
	public Integer getIsRead() {
		return isRead;
	}
}
