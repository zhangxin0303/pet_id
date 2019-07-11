package com.cqcej.web.modules.common.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-31 15:59:32
 */
@TableName("ct_user_footprint")
public class UserFootprintEntity implements Serializable {

	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty("ID")
	protected Long footprintId;

	/**
	 * 用户ID
	 */
	@ApiModelProperty("用户ID")
	protected Long userId;

	/**
	 * 机构ID
	 */
	@ApiModelProperty("机构ID")
	protected Long mechanismId;
	
	/**
	 * 时间
	 */
	@ApiModelProperty("时间")
	protected Date createAt;


	/**
	 * 设置：ID
	 */
	public void setFootprintId(Long footprintId) {
		this.footprintId = footprintId;
	}

	/**
	 * 获取：ID
	 */
	public Long getFootprintId() {
		return footprintId;
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
	 * 设置：机构ID
	 */
	public void setMechanismId(Long mechanismId) {
		this.mechanismId = mechanismId;
	}

	/**
	 * 获取：机构ID
	 */
	public Long getMechanismId() {
		return mechanismId;
	}
	
	/**
	 * 设置：时间
	 */
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	/**
	 * 获取：时间
	 */
	public Date getCreateAt() {
		return createAt;
	}
}
