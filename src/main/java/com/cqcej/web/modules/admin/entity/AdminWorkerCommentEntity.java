package com.cqcej.web.modules.admin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论工作人员
 * 
 * @author Jia Min
 * @email empty
 * @date 2018-07-11 14:39:18
 */
@TableName("ct_worker_comment")
@ApiModel("评论工作人员")
public class AdminWorkerCommentEntity implements Serializable {

	/**
	 * 
	 */
	@TableId
	@ApiModelProperty("")
	protected Long commonId;

	/**
	 * 评论的用户ID
	 */
	@ApiModelProperty("评论的用户ID")
	protected Long userId;

	/**
	 * 医师ID
	 */
	@ApiModelProperty("医师ID")
	protected Long workerId;

	/**
	 * 评论等级(1差评2中评3好评）
	 */
	@ApiModelProperty("评论等级(1差评2中评3好评）")
	protected Integer commentLevel;

	/**
	 * 评论内容
	 */
	@ApiModelProperty("评论内容")
	protected String commentContent;

	/**
	 * 评论时间
	 */
	@ApiModelProperty("评论时间")
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
	 * 设置：评论的用户ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 获取：评论的用户ID
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 设置：医师ID
	 */
	public void setWorkerId(Long workerId) {
		this.workerId = workerId;
	}

	/**
	 * 获取：医师ID
	 */
	public Long getWorkerId() {
		return workerId;
	}

	/**
	 * 设置：评论等级(1差评2中评3好评）
	 */
	public void setCommentLevel(Integer commentLevel) {
		this.commentLevel = commentLevel;
	}

	/**
	 * 获取：评论等级(1差评2中评3好评）
	 */
	public Integer getCommentLevel() {
		return commentLevel;
	}

	/**
	 * 设置：评论内容
	 */
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	/**
	 * 获取：评论内容
	 */
	public String getCommentContent() {
		return commentContent;
	}

	/**
	 * 设置：评论时间
	 */
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	/**
	 * 获取：评论时间
	 */
	public Date getCreateAt() {
		return createAt;
	}
}
