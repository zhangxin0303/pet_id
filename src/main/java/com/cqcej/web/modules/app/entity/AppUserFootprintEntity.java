package com.cqcej.web.modules.app.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.cqcej.web.modules.common.entity.UserFootprintEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户足迹
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-31 16:22
 */
@TableName("ct_user_footprint")
public class AppUserFootprintEntity extends UserFootprintEntity {
	/**
	 * 机构图片
	 */
	@ApiModelProperty("机构图片")
	private String mechanismImage;
	
	/**
	 * 机构名
	 */
	@ApiModelProperty("机构名")
	private String mechanismName;
	
	/**
	 * 机构评分
	 */
	@ApiModelProperty("机构评分")
	private String mechanismStars;
	
	/**
	 * 机构关键词
	 */
	@ApiModelProperty("机构关键词")
	private String mechanismKeywords;
	
	/**
	 * 机构地区名
	 */
	@ApiModelProperty("机构地区名")
	private String areaName;
	
	/**
	 * 机构经度
	 */
	@ApiModelProperty("机构经度")
	private Double longitude;
	
	/**
	 * 机构纬度
	 */
	@ApiModelProperty("机构纬度")
	private Double latitude;
	
	public String getMechanismImage() {
		return mechanismImage;
	}
	
	public void setMechanismImage(String mechanismImage) {
		this.mechanismImage = mechanismImage;
	}
	
	public String getMechanismName() {
		return mechanismName;
	}
	
	public void setMechanismName(String mechanismName) {
		this.mechanismName = mechanismName;
	}
	
	public String getMechanismStars() {
		return mechanismStars;
	}
	
	public void setMechanismStars(String mechanismStars) {
		this.mechanismStars = mechanismStars;
	}
	
	public String getMechanismKeywords() {
		return mechanismKeywords;
	}
	
	public void setMechanismKeywords(String mechanismKeywords) {
		this.mechanismKeywords = mechanismKeywords;
	}
	
	public String getAreaName() {
		return areaName;
	}
	
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	public Double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	public Double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
}
