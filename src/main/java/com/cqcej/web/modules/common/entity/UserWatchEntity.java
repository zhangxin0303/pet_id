package com.cqcej.web.modules.common.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户关注
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-06-22 10:47:54
 */
@TableName("ct_user_watch")
@ApiModel("用户关注")
public class UserWatchEntity implements Serializable {

	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty("ID")
	protected Long watchId;

	/**
	 * 
	 */
	@ApiModelProperty("")
	protected Long userId;

	/**
	 * 
	 */
	@ApiModelProperty("")
	protected Long watchUserId;

	/**
	 * 
	 */
	@ApiModelProperty("")
	protected Date createAt;


	/**
	 * 设置：ID
	 */
	public void setWatchId(Long watchId) {
		this.watchId = watchId;
	}

	/**
	 * 获取：ID
	 */
	public Long getWatchId() {
		return watchId;
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
	public void setWatchUserId(Long watchUserId) {
		this.watchUserId = watchUserId;
	}

	/**
	 * 获取：
	 */
	public Long getWatchUserId() {
		return watchUserId;
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
