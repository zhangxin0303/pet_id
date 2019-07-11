package com.cqcej.web.modules.common.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户点赞记录
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-30 19:26:37
 */
@TableName("ct_user_prise")
public class UserPriseEntity implements Serializable {
	protected static final long serialVersionUID = 1L;
	
	/**
	 * 点赞类型，帖子
	 */
	public static final int PRISE_TYPE_POST = 1;
	
	/**
	 * 点赞类型，回复
	 */
	public static final int PRISE_TYPE_REPLY = 2;
	
	/**
	 * 点赞类型，诊所评论
	 */
	public static final int PRISE_TYPE_CLINIC_COMMENT = 3;
	
	/**
	 * 点赞类型，医生评论
	 */
	public static final int PRISE_TYPE_DOCTOR_COMMENT = 4;

	/**
	 * 
	 */
	@TableId
	@ApiModelProperty("ID")
	protected Long priseId;
	
	/**
	 * 用户ID
	 */
	@ApiModelProperty("用户ID")
	protected Long userId;
	
	/**
	 * 点赞对象ID
	 */
	@ApiModelProperty("用户ID")
	protected Long objectId;
	
	/**
	 * 点赞类型（1帖子，2回复，3诊所评论，4医生评论）
	 */
	@ApiModelProperty("点赞类型（1帖子，2回复，3诊所评论，4医生评论）")
	protected Integer priseType;
	
	/**
	 * 点赞时间
	 */
	@ApiModelProperty("点赞时间")
	protected Date createAt;

	/**
	 * 设置：
	 */
	public void setPriseId(Long priseId) {
		this.priseId = priseId;
	}
	/**
	 * 获取：
	 */
	public Long getPriseId() {
		return priseId;
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
	 * 设置：点赞对象ID
	 */
	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}
	/**
	 * 获取：点赞对象ID
	 */
	public Long getObjectId() {
		return objectId;
	}
	/**
	 * 设置：点赞类型（1帖子，2回复，3诊所评论，4医生评论）
	 */
	public void setPriseType(Integer priseType) {
		this.priseType = priseType;
	}
	/**
	 * 获取：点赞类型（1帖子，2回复，3诊所评论，4医生评论）
	 */
	public Integer getPriseType() {
		return priseType;
	}
	/**
	 * 设置：点赞时间
	 */
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	/**
	 * 获取：点赞时间
	 */
	public Date getCreateAt() {
		return createAt;
	}
}
