package com.cqcej.web.modules.app.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 反馈
 * 
 * @author Jia Min
 * @date 2018-11-07 16:34:56
 */
@TableName("ct_feedback")
@ApiModel("反馈")
@Data
public class AppFeedbackEntity implements Serializable {

	/**
	 * id
	 */
	@TableId
	@ApiModelProperty("id")
	protected Long feedbackId;

	/**
	 * 用户ID
	 */
	@ApiModelProperty("用户ID")
	protected Long userId;

	/**
	 * 反馈内容
	 */
	@ApiModelProperty("反馈内容")
	protected String content;

	/**
	 * 反馈时间
	 */
	@ApiModelProperty("反馈时间")
	protected Date createAt;
}
