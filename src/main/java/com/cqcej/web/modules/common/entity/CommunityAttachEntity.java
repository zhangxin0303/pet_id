package com.cqcej.web.modules.common.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 社区发帖附件，包括图片，视频等
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-30 16:30:26
 */
@TableName("ct_community_attach")
@ApiModel("社区发帖附件，包括图片，视频等")
public class CommunityAttachEntity implements Serializable {

	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty("ID")
	protected Long attachId;

	/**
	 * 用户ID
	 */
	@ApiModelProperty("用户ID")
	protected Long userId;

	/**
	 * 帖子ID
	 */
	@ApiModelProperty("帖子ID")
	protected Long communityId;

	/**
	 * 图片地址
	 */
	@ApiModelProperty("图片地址")
	protected String attachUrl;


	/**
	 * 设置：
	 */
	public void setAttachId(Long attachId) {
		this.attachId = attachId;
	}

	/**
	 * 获取：
	 */
	public Long getAttachId() {
		return attachId;
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
	 * 设置：图片地址
	 */
	public void setAttachUrl(String attachUrl) {
		this.attachUrl = attachUrl;
	}

	/**
	 * 获取：图片地址
	 */
	public String getAttachUrl() {
		return attachUrl;
	}
}
