package com.cqcej.web.modules.admin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 机构评价
 * 
 * @author Jia Min
 * @email empty
 * @date 2018-07-16 11:08:31
 */
@TableName("ct_mechanism_comment")
@ApiModel("机构评价")
public class AdminMechanismCommentEntity implements Serializable {

	/**
	 * 
	 */
	@TableId
	@ApiModelProperty("")
	protected Long commonId;

	/**
	 * 用户ID
	 */
	@ApiModelProperty("用户ID")
	protected Long userId;

	/**
	 * 诊所ID
	 */
	@ApiModelProperty("诊所ID")
	protected Long mechanismId;

	/**
	 * 评论的服务ID
	 */
	@ApiModelProperty("评论的服务ID")
	protected Long serviceId;

	/**
	 * 评星等级，1-5
	 */
	@ApiModelProperty("评星等级，1-5")
	protected Integer commentLevel;

	/**
	 * 评价内容
	 */
	@ApiModelProperty("评价内容")
	protected String commentContent;

	/**
	 * 点赞次数
	 */
	@ApiModelProperty("点赞次数")
	protected Integer priseCount;

	/**
	 * 评价时间
	 */
	@ApiModelProperty("评价时间")
	protected Date createAt;


	/**
	 * 设置：
	 */
	public void setCommonId(Long commonId) {
		this.commonId = commonId;
	}

	/**
	 * 获取：
	 */
	public Long getCommonId() {
		return commonId;
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
	 * 设置：诊所ID
	 */
	public void setMechanismId(Long mechanismId) {
		this.mechanismId = mechanismId;
	}

	/**
	 * 获取：诊所ID
	 */
	public Long getMechanismId() {
		return mechanismId;
	}

	/**
	 * 设置：评论的服务ID
	 */
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	/**
	 * 获取：评论的服务ID
	 */
	public Long getServiceId() {
		return serviceId;
	}

	/**
	 * 设置：评星等级，1-5
	 */
	public void setCommentLevel(Integer commentLevel) {
		this.commentLevel = commentLevel;
	}

	/**
	 * 获取：评星等级，1-5
	 */
	public Integer getCommentLevel() {
		return commentLevel;
	}

	/**
	 * 设置：评价内容
	 */
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	/**
	 * 获取：评价内容
	 */
	public String getCommentContent() {
		return commentContent;
	}

	/**
	 * 设置：点赞次数
	 */
	public void setPriseCount(Integer priseCount) {
		this.priseCount = priseCount;
	}

	/**
	 * 获取：点赞次数
	 */
	public Integer getPriseCount() {
		return priseCount;
	}

	/**
	 * 设置：评价时间
	 */
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	/**
	 * 获取：评价时间
	 */
	public Date getCreateAt() {
		return createAt;
	}
}
