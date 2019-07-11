package com.cqcej.web.modules.admin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息
 * 
 * @author Jia Min
 * @date 2018-08-27 11:37:02
 */
@TableName("ct_message")
@ApiModel("消息.")
@Data
public class AdminMessageEntity implements Serializable {
	
	/**
	 * 商家消息
	 */
	public static final int MESSAGE_TYPE_MECHANISM = 11;
	/**
	 * 用户消息
	 */
	public static final int MESSAGE_TYPE_USER = 12;
	/**
	 * 接送人员消息
	 */
	public static final int MESSAGE_TYPE_PICKUP = 13;
	/**
	 * 系统消息
	 */
	public static final int MESSAGE_TYPE_SYSTEM = 20;
	
	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty("ID")
	protected Long messageId;

	/**
	 * 消息类型（1系统消息···）
	 */
	@ApiModelProperty("消息类型（10商家消息，11用户消息，12接送员消息，20系统消息···）")
	protected Integer messageType;

	/**
	 * 事件ID，比如，评论通知，或者发布帖子被点赞等，则这是帖子ID，其他类似，纯通知该字段为0
	 */
	@ApiModelProperty("事件ID，比如，评论通知，或者发布帖子被点赞等，则这是帖子ID，其他类似，纯通知该字段为0")
	protected Long objectId;

	/**
	 * 消息标题
	 */
	@ApiModelProperty("消息标题")
	protected String messageTitle;

	/**
	 * 消息内容
	 */
	@ApiModelProperty("消息内容")
	protected String messageContent;

	/**
	 * 备注
	 */
	@ApiModelProperty("备注")
	protected String note;

	/**
	 * 发布时间
	 */
	@ApiModelProperty("发布时间")
	protected Date createAt;
}
