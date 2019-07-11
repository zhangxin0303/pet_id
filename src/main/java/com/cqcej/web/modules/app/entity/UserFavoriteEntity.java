package com.cqcej.web.modules.app.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户收藏
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-26 10:33:22
 */
@TableName("ct_user_favorite")
@ApiModel("用户收藏")
public class UserFavoriteEntity<T> implements Serializable {
	
	/**
	 * 机构，包括诊所，美容院
	 */
	public static final int FAVORITE_TYPE_CLINIC = 1;
	/**
	 * 帖子收藏
	 */
	public static final int FAVORITE_TYPE_COMMUNITY = 3;
	
	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty("ID")
	private Long favoriteId;
	
	/**
	 * 用户ID
	 */
	@ApiModelProperty("用户ID")
	private Long userId;
	
	/**
	 * 收藏类型（1医疗，2美容，3帖子）
	 */
	@ApiModelProperty("收藏类型（1医疗，2美容，3帖子）")
	private Integer favoriteType;
	
	/**
	 * 收藏的对象ID
	 */
	@ApiModelProperty("收藏的对象ID")
	private Long objectId;
	
	/**
	 * 收藏对象，医生、诊所、帖子
	 */
	@ApiModelProperty("收藏的对象，医生、诊所、帖子")
	private T object;
	
	/**
	 * 收藏时间
	 */
	@ApiModelProperty("收藏时间")
	private Date createAt;
	
	
	/**
	 * 设置：ID
	 */
	public void setFavoriteId(Long favoriteId) {
		this.favoriteId = favoriteId;
	}
	
	/**
	 * 获取：ID
	 */
	public Long getFavoriteId() {
		return favoriteId;
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
	 * 设置：收藏类型（1医疗，2美容，3帖子）
	 */
	public void setFavoriteType(Integer favoriteType) {
		this.favoriteType = favoriteType;
	}
	
	/**
	 * 获取：收藏类型（1医疗，2美容，3帖子）
	 */
	public Integer getFavoriteType() {
		return favoriteType;
	}
	
	/**
	 * 设置：收藏的对象ID
	 */
	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}
	
	/**
	 * 获取：收藏的对象ID
	 */
	public Long getObjectId() {
		return objectId;
	}
	
	public void setObject(T object) {
		this.object = object;
	}
	
	public T getObject() {
		return object;
	}
	
	/**
	 * 设置：收藏时间
	 */
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	/**
	 * 获取：收藏时间
	 */
	public Date getCreateAt() {
		return createAt;
	}
}
