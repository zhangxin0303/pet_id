package com.cqcej.web.modules.common.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 机构预约
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-20 11:10:15
 */
@TableName("ct_mechanism_reservation")
@ApiModel("机构预约")
public class MechanismReservationEntity implements Serializable {
	
	
	/**
	 *
	 */
	@TableId
	@ApiModelProperty("ID")
	protected Long reservationId;
	
	/**
	 * 预约的用户
	 */
	@ApiModelProperty("预约的用户")
	protected Long userId;
	
	/**
	 * 预约的诊所ID
	 */
	@ApiModelProperty("预约的诊所ID")
	protected Long mechanismId;
	
	/**
	 * 服务类型
	 */
	@ApiModelProperty("服务类型")
	protected Integer serviceId;
	
	/**
	 * 预约时间
	 */
	@ApiModelProperty("预约时间")
	protected Date reserveAt;
	
	/**
	 * 联系方式
	 */
	@ApiModelProperty("联系方式")
	protected String contact;
	
	/**
	 * 创建时间
	 */
	@ApiModelProperty(hidden = true)
	protected Date createAt;
	
	
	/**
	 * 设置：
	 */
	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}
	
	/**
	 * 获取：
	 */
	public Long getReservationId() {
		return reservationId;
	}
	
	/**
	 * 设置：预约的用户
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取：预约的用户
	 */
	public Long getUserId() {
		return userId;
	}
	
	/**
	 * 设置：预约的诊所ID
	 */
	public void setMechanismId(Long mechanismId) {
		this.mechanismId = mechanismId;
	}
	
	/**
	 * 获取：预约的诊所ID
	 */
	public Long getMechanismId() {
		return mechanismId;
	}
	
	/**
	 * 设置：服务类型
	 */
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	
	/**
	 * 获取：服务类型
	 */
	public Integer getServiceId() {
		return serviceId;
	}
	
	/**
	 * 设置：预约时间
	 */
	public void setReserveAt(Date reserveAt) {
		this.reserveAt = reserveAt;
	}
	
	/**
	 * 获取：预约时间
	 */
	public Date getReserveAt() {
		return reserveAt;
	}
	
	/**
	 * 设置：联系方式
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	/**
	 * 获取：联系方式
	 */
	public String getContact() {
		return contact;
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
