package com.cqcej.web.modules.admin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 资讯评论
 * 
 * @author Jia Min
 * @email empty
 * @date 2018-07-17 10:41:21
 */
@TableName("ct_article_comment")
@ApiModel("资讯评论")
public class AdminArticleCommentEntity implements Serializable {

	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty("ID")
	protected Long commentId;

	/**
	 * 评论的资讯ID
	 */
	@ApiModelProperty("评论的资讯ID")
	protected Long articleId;

	/**
	 * 评论的用户
	 */
	@ApiModelProperty("评论的用户")
	protected Long userId;

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
	 * 头像地址
	 */
	@ApiModelProperty("头像地址")
	protected String avatar;

	/**
	 * 用户昵称
	 */
	@ApiModelProperty("用户昵称")
	protected String nickname;

	/**
	 * 是否禁言(0正常，1禁言)
	 */
	@ApiModelProperty("是否禁言(0正常，1禁言)")
	protected Integer forbiddenComment;

	/**
	 * 设置：ID
	 */
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	/**
	 * 获取：ID
	 */
	public Long getCommentId() {
		return commentId;
	}

	/**
	 * 设置：评论的资讯ID
	 */
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	/**
	 * 获取：评论的资讯ID
	 */
	public Long getArticleId() {
		return articleId;
	}

	/**
	 * 设置：评论的用户
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 获取：评论的用户
	 */
	public Long getUserId() {
		return userId;
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
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getForbiddenComment() {
		return forbiddenComment;
	}

	public void setForbiddenComment(Integer forbiddenComment) {
		this.forbiddenComment = forbiddenComment;
	}
}
