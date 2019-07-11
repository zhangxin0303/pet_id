package com.cqcej.web.modules.admin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 轮播图
 * 
 * @author Jia Min
 * @date 2018-08-10 16:27:30
 */
@TableName("ct_sys_banner")
@ApiModel("轮播图 ")
@Data
public class AdminSysBannerEntity implements Serializable {

	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty("ID")
	protected Integer bannerId;

	/**
	 * banner位置，1宠物分类，2宠物健康，3线上预约，4美容预约，5接送服务，6宠物寄养
	 */
	@ApiModelProperty("banner位置，1宠物分类，2宠物健康，3线上预约，4美容预约，5接送服务，6宠物寄养")
	protected Integer position;

	/**
	 * 标题
	 */
	@ApiModelProperty("标题")
	protected String title;

	/**
	 * 地址
	 */
	@ApiModelProperty("多个链接地址")
	protected String[] urls;
	
	/**
	 * 地址
	 */
	@ApiModelProperty("链接地址")
	protected String url;
	
	/**
	 * 地址
	 */
	@ApiModelProperty("多个图片地址")
	protected String[] imageUrls;
	
	/**
	 * 图片地址
	 */
	@ApiModelProperty("图片地址")
	protected String imageUrl;

	/**
	 * 排序，越大越靠前
	 */
	@ApiModelProperty("排序，越大越靠前")
	protected Integer sort;

	/**
	 * 状态
	 */
	@ApiModelProperty("状态")
	protected Integer status;

	/**
	 * 投放平台(1Android,2IPhone,3PC)
	 */
	@ApiModelProperty("投放平台(1Android,2IPhone,3PC)")
	protected Integer platform;
	
	
	/**
	 * 间隔时间
	 */
	@ApiModelProperty("间隔时间")
	protected Integer intervalTime;
	
	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	protected Date createAt;


}
