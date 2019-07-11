package com.cqcej.web.modules.app.entity.home;

import com.baomidou.mybatisplus.annotations.TableName;
import com.cqcej.web.modules.common.entity.MechanismEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 机构，包含诊所，美容院
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-27 15:52:26
 */
@TableName("ct_mechanism")
@ApiModel("机构，包含诊所，美容院")
public class AppMechanismEntity extends MechanismEntity {
	
	/**
	 * 缩略图地址
	 */
	@ApiModelProperty("列表缩略图")
	protected String thumbImageUrl;
	
	/**
	 * 轮播图
	 */
	@ApiModelProperty("详情轮播图")
	protected List<AppMechanismImagesEntity> banners;
	
	/**
	 * 地区名
	 */
	@ApiModelProperty("地区名")
	protected String areaName;
	
	
	/**
	 * 是否收藏
	 */
	@ApiModelProperty("是否收藏(需登录)")
	protected boolean favorite;
	
	public String getThumbImageUrl() {
		return thumbImageUrl;
	}
	
	public void setThumbImageUrl(String thumbImageUrl) {
		this.thumbImageUrl = thumbImageUrl;
	}
	
	public String getAreaName() {
		return areaName;
	}
	
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	public List<AppMechanismImagesEntity> getBanners() {
		return banners;
	}
	
	public void setBanners(List<AppMechanismImagesEntity> banners) {
		this.banners = banners;
	}
	
	public boolean isFavorite() {
		return favorite;
	}
	
	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}
}
