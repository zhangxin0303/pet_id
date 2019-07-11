package com.cqcej.web.modules.admin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * APP升级实体
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-06 16:05:40
 */
@TableName("ct_app_upgrade")
@ApiModel("APP升级信息")
@SuppressWarnings("unused")
public class AppUpgradeEntity implements Serializable {
	
	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty(hidden = true)
	private Long upgradeId;
	/**
	 * 设备类型，1android，2ios
	 */
	@ApiModelProperty("设备类型，1android，2ios")
	private Integer deviceType;
	/**
	 * 版本号
	 */
	@ApiModelProperty("版本号")
	private Integer version;
	/**
	 * 版本大小
	 */
	@ApiModelProperty("版本大小")
	private BigDecimal size;
	/**
	 * 版本描述
	 */
	@ApiModelProperty("版本描述")
	private String description;
	/**
	 * 下载地址
	 */
	@ApiModelProperty("下载地址")
	private String downloadUrl;
	/**
	 * 是否强制升级(0不强制1强制)
	 */
	@ApiModelProperty("是否强制升级(0不强制1强制)")
	private Integer isForce;
	/**
	 * 发布时间
	 */
	@ApiModelProperty("发布时间")
	private Date createAt;

	/**
	 * 设置：ID
	 */
	public void setUpgradeId(Long upgradeId) {
		this.upgradeId = upgradeId;
	}
	
	public Integer getDeviceType() {
		return deviceType;
	}
	
	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}
	/**
	 * 获取：ID
	 */
	public Long getUpgradeId() {
		return upgradeId;
	}
	/**
	 * 设置：版本号
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}
	/**
	 * 获取：版本号
	 */
	public Integer getVersion() {
		return version;
	}
	/**
	 * 设置：版本大小
	 */
	public void setSize(BigDecimal size) {
		this.size = size;
	}
	/**
	 * 获取：版本大小
	 */
	public BigDecimal getSize() {
		return size;
	}
	/**
	 * 设置：版本描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：版本描述
	 */
	public String getDescription() {
		return description;
	}
	
	public String getDownloadUrl() {
		return downloadUrl;
	}
	
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	/**
	 * 设置：是否强制升级(0不强制1强制)
	 */
	public void setIsForce(Integer isForce) {
		this.isForce = isForce;
	}
	/**
	 * 获取：是否强制升级(0不强制1强制)
	 */
	public Integer getIsForce() {
		return isForce;
	}
	/**
	 * 设置：发布时间
	 */
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	/**
	 * 获取：发布时间
	 */
	public Date getCreateAt() {
		return createAt;
	}
}
