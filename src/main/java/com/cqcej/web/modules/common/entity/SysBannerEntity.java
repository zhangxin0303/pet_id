package com.cqcej.web.modules.common.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 轮播图
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-02 17:12:30
 */
@TableName("ct_sys_banner")
@ApiModel("轮播图")
public class SysBannerEntity implements Serializable {
	
	/**
	 * 宠物分类
	 */
	public static final int BANNER_POSITION_CATEGORY = 1;
	
	/**
	 * 宠物健康
	 */
	public static final int BANNER_POSITION_HEALTH = 2;
	
	/**
	 * 线上预约
	 */
	public static final int BANNER_POSITION_ONLINE = 3;
	
	/**
	 * 宠物美容
	 */
	public static final int BANNER_POSITION_BEAUTY = 4;
	
	/**
	 * 接送服务
	 */
	public static final int BANNER_POSITION_PICKUP = 5;
	
	/**
	 * 宠物寄养
	 */
	public static final int BANNER_POSITION_FOSTER = 6;
	
	/**
	 * 商城
	 */
	public static final int BANNER_POSITION_SHOP = 7;

	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty("ID")
	protected  Integer bannerId;

	/**
	 * banner位置，1宠物健康，2宠物分类，3线上预约
	 */
	@ApiModelProperty("banner位置，1宠物健康，2宠物分类，3线上预约")
	protected  Integer position;
	
	/**
	 * 标题，可null
	 */
	@ApiModelProperty("标题，可null")
	protected String title;

	/**
	 * 地址
	 */
	@ApiModelProperty("地址")
	protected  String url;

	/**
	 * 图片地址
	 */
	@ApiModelProperty("图片地址")
	protected  String imageUrl;

	/**
	 * 排序，越大越靠前
	 */
	@ApiModelProperty("排序，越大越靠前")
	protected  Integer sort;

	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	protected  Date createAt;


	/**
	 * 设置：ID
	 */
	public void setBannerId(Integer bannerId) {
		this.bannerId = bannerId;
	}

	/**
	 * 获取：ID
	 */
	public Integer getBannerId() {
		return bannerId;
	}

	/**
	 * 设置：banner位置，1宠物健康，2宠物分类，3线上预约
	 */
	public void setPosition(Integer position) {
		this.position = position;
	}

	/**
	 * 获取：banner位置，1宠物健康，2宠物分类，3线上预约
	 */
	public Integer getPosition() {
		return position;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 设置：地址
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获取：地址
	 */
	public String getUrl() {
		return url;
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
	 * 设置：排序，越大越靠前
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * 获取：排序，越大越靠前
	 */
	public Integer getSort() {
		return sort;
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
