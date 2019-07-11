package com.cqcej.web.modules.common.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 帖子点赞记录
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-06-14 16:16:54
 */
@TableName("ct_community_prise")
@ApiModel("帖子点赞记录")
public class CommunityPriseEntity implements Serializable {

	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty("ID")
	protected Long priseId;

	/**
	 * 
	 */
	@ApiModelProperty("")
	protected Long userId;

	/**
	 * 
	 */
	@ApiModelProperty("")
	protected Long communityId;

	/**
	 * 
	 */
	@ApiModelProperty("")
	protected Date createAt;


	/**
	 * 设置：ID
	 */
	public void setPriseId(Long priseId) {
		this.priseId = priseId;
	}

	/**
	 * 获取：ID
	 */
	public Long getPriseId() {
		return priseId;
	}

	/**
	 * 设置：
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 获取：
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 设置：
	 */
	public void setCommunityId(Long communityId) {
		this.communityId = communityId;
	}

	/**
	 * 获取：
	 */
	public Long getCommunityId() {
		return communityId;
	}

	/**
	 * 设置：
	 */
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	/**
	 * 获取：
	 */
	public Date getCreateAt() {
		return createAt;
	}
}
