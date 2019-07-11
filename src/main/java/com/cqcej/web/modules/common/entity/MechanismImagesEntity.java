package com.cqcej.web.modules.common.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 机构轮播
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-20 11:10:15
 */
@TableName("ct_mechanism_images")
public class MechanismImagesEntity implements Serializable {
	
	
	/**
	 *
	 */
	@TableId
	@ApiModelProperty("ID")
	protected Long imageId;
	
	/**
	 * 机构ID
	 */
	@ApiModelProperty("机构ID")
	protected Long mechanismId;
	
	/**
	 * 图片地址
	 */
	@ApiModelProperty("图片地址")
	protected String imageUrl;
	
	/**
	 * 创建时间
	 */
	@ApiModelProperty(hidden = true)
	protected Date createAt;
	
	
	/**
	 * 设置：
	 */
	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}
	
	/**
	 * 获取：
	 */
	public Long getImageId() {
		return imageId;
	}
	
	/**
	 * 设置：诊所ID
	 */
	public void setMechanismId(Long mechanismId) {
		this.mechanismId = mechanismId;
	}
	
	/**
	 * 获取：诊所ID
	 */
	public Long getMechanismId() {
		return mechanismId;
	}
	
	/**
	 * 设置：图片地址
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	/**
	 * 获取：图片地址
	 */
	public String getImageUrl() {
		return imageUrl;
	}
	
	/**
	 * 设置：创建时间
	 */
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	/**
	 * 获取：创建时间
	 */
	public Date getCreateAt() {
		return createAt;
	}
}
