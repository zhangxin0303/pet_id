package com.cqcej.web.modules.common.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 黑名单
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-24 11:55:55
 */
@TableName("ct_user_blacklist")
@ApiModel("黑名单")
public class UserBlacklistEntity implements Serializable {

	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty("ID")
	protected  Long listId;

	/**
	 * 用户ID
	 */
	@ApiModelProperty("用户ID")
	protected  Long userId;

	/**
	 * 被拉黑的用户ID
	 */
	@ApiModelProperty("被拉黑的用户ID")
	protected  Long blackId;
	
	/**
	 * 被拉黑的用户昵称
	 */
	@ApiModelProperty("被拉黑的用户昵称")
	protected String blackNickname;
	
	/**
	 * 被拉黑的用户头像
	 */
	@ApiModelProperty("被拉黑的用户头像")
	protected String blackAvatar;

	/**
	 * 拉黑时间
	 */
	@ApiModelProperty("拉黑时间")
	protected  Date createAt;


	/**
	 * 设置：ID
	 */
	public void setListId(Long listId) {
		this.listId = listId;
	}

	/**
	 * 获取：ID
	 */
	public Long getListId() {
		return listId;
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
	 * 设置：被拉黑的用户ID
	 */
	public void setBlackId(Long blackId) {
		this.blackId = blackId;
	}

	/**
	 * 获取：被拉黑的用户ID
	 */
	public Long getBlackId() {
		return blackId;
	}
	
	public void setBlackNickname(String blackNickname) {
		this.blackNickname = blackNickname;
	}
	
	public String getBlackNickname() {
		return blackNickname;
	}
	
	public void setBlackAvatar(String blackAvatar) {
		this.blackAvatar = blackAvatar;
	}
	
	public String getBlackAvatar() {
		return blackAvatar;
	}
	
	/**
	 * 设置：拉黑时间
	 */
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	/**
	 * 获取：拉黑时间
	 */
	public Date getCreateAt() {
		return createAt;
	}
}
