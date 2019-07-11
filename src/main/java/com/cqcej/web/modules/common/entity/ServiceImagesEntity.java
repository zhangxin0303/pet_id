package com.cqcej.web.modules.common.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 预约上门图片，仅针对上门就诊类型
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-06-04 11:08:08
 */
@TableName("ct_service_images")
@ApiModel("预约上门图片，仅针对上门就诊类型")
public class ServiceImagesEntity implements Serializable {

	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty("ID")
	protected Long imageId;

	/**
	 * 预约上门ID
	 */
	@ApiModelProperty("预约上门ID")
	protected Long orderId;

	/**
	 * 图片地址
	 */
	@ApiModelProperty("图片地址")
	protected String imageUrl;


	/**
	 * 设置：ID
	 */
	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	/**
	 * 获取：ID
	 */
	public Long getImageId() {
		return imageId;
	}

	/**
	 * 设置：预约上门ID
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/**
	 * 获取：预约上门ID
	 */
	public Long getOrderId() {
		return orderId;
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
}
