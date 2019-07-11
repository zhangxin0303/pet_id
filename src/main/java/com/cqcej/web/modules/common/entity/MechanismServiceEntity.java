package com.cqcej.web.modules.common.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 机构服务中间表
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-28 15:51:31
 */
@TableName("ct_mechanism_service")
public class MechanismServiceEntity implements Serializable {
	
	/**
	 * 美容服务
	 */
	public static final int SERVICE_TYPE_BEAUTY = 1;
	
	/**
	 * 寄养服务
	 */
	public static final int SERVICE_TYPE_FOSTER = 2;
	
	/**
	 * 遛狗服务
	 */
	public static final int SERVICE_TYPE_WALK_DOG = 4;
	
	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty("ID")
	protected Long mechanismServiceId;
	
	/**
	 * 宠物分类ID
	 */
	@ApiModelProperty("宠物分类ID")
	protected Integer petClassId;
	
	/**
	 * 诊所ID
	 */
	@ApiModelProperty("诊所ID")
	protected Long mechanismId;
	
	/**
	 * 服务类型(1美容服务，2寄养服务，3遛狗服务)
	 */
	@ApiModelProperty("服务类型(1美容服务，2寄养服务，3遛狗服务)")
	protected Integer serviceType;
	
	/**
	 * 服务名
	 */
	@ApiModelProperty("服务名")
	protected String serviceName;
	
	/**
	 * 服务价格
	 */
	@ApiModelProperty("服务价格")
	protected BigDecimal servicePrice;
	
	/**
	 * 已售多少
	 */
	@ApiModelProperty("已售多少")
	protected Integer serviceSold;
	
	/**
	 * 排序，越大越前
	 */
	@ApiModelProperty("排序，越大越前")
	protected Integer sort;
	
	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	protected Date createAt;
	
	
	/**
	 * 设置：ID
	 */
	public void setMechanismServiceId(Long mechanismServiceId) {
		this.mechanismServiceId = mechanismServiceId;
	}
	
	/**
	 * 获取：ID
	 */
	public Long getMechanismServiceId() {
		return mechanismServiceId;
	}
	
	/**
	 * 设置：宠物分类ID
	 */
	public void setPetClassId(Integer petClassId) {
		this.petClassId = petClassId;
	}
	
	/**
	 * 获取：宠物分类ID
	 */
	public Integer getPetClassId() {
		return petClassId;
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
	 * 设置：服务类型
	 */
	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}
	
	/**
	 * 获取：服务类型
	 */
	public Integer getServiceType() {
		return serviceType;
	}
	
	/**
	 * 设置：服务名
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	/**
	 * 获取：服务名
	 */
	public String getServiceName() {
		return serviceName;
	}
	
	/**
	 * 设置：服务价格
	 */
	public void setServicePrice(BigDecimal servicePrice) {
		this.servicePrice = servicePrice;
	}
	
	/**
	 * 获取：服务价格
	 */
	public BigDecimal getServicePrice() {
		return servicePrice;
	}
	
	/**
	 * 设置：已售多少
	 */
	public void setServiceSold(Integer serviceSold) {
		this.serviceSold = serviceSold;
	}
	
	/**
	 * 获取：已售多少
	 */
	public Integer getServiceSold() {
		return serviceSold;
	}
	
	/**
	 * 设置：排序，越大越前
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	/**
	 * 获取：排序，越大越前
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
