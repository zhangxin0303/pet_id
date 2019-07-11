package com.cqcej.web.modules.admin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 社区评论
 * 
 * @author Jia Min
 * @email empty
 * @date 2018-07-19 13:36:15
 */
@ApiModel("社区评论")
public class AdminCommunityCommentEntity implements Serializable {

	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty("ID")
	protected Long commentId;

	/**
	 * 评论者
	 */
	@ApiModelProperty("评论者")
	protected Long userId;

	/**
	 * 帖子ID
	 */
	@ApiModelProperty("帖子ID")
	protected Long communityId;

	/**
	 * 评论内容
	 */
	@ApiModelProperty("评论内容")
	protected String context;

	/**
	 * 点赞数
	 */
	@ApiModelProperty("点赞数")
	protected Integer priseCount;

	/**
	 * 发布评论时间
	 */
	@ApiModelProperty("发布评论时间")
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
	 * 设置：评论者
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 获取：评论者
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 设置：帖子ID
	 */
	public void setCommunityId(Long communityId) {
		this.communityId = communityId;
	}

	/**
	 * 获取：帖子ID
	 */
	public Long getCommunityId() {
		return communityId;
	}

	/**
	 * 设置：评论内容
	 */
	public void setContext(String context) {
		this.context = context;
	}

	/**
	 * 获取：评论内容
	 */
	public String getContext() {
		return context;
	}

	/**
	 * 设置：点赞数
	 */
	public void setPriseCount(Integer priseCount) {
		this.priseCount = priseCount;
	}

	/**
	 * 获取：点赞数
	 */
	public Integer getPriseCount() {
		return priseCount;
	}

	/**
	 * 设置：发布评论时间
	 */
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	/**
	 * 获取：发布评论时间
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
