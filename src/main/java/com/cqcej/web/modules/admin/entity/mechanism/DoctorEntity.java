package com.cqcej.web.modules.admin.entity.mechanism;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


@ApiModel("医师信息")
@Data
public class DoctorEntity implements Serializable {
	
	/**
	 * ID
	 */
	@ApiModelProperty("workerId")
	protected Long workerId;
	
	/**
	 * ID
	 */
	@ApiModelProperty("userId")
	protected Long userId;
	
	/**
	 * 医师姓名
	 */
	@ApiModelProperty("医师姓名")
	protected String realname;
	
	/**
	 * 聊天咨询费
	 */
	@ApiModelProperty("聊天咨询费")
	protected BigDecimal chatFee;
	
	/**
	 * 聊天咨询费
	 */
	@ApiModelProperty("电话咨询费")
	protected BigDecimal telephoneFee;
	
	/**
	 * 医师介绍
	 */
	@ApiModelProperty("医师介绍")
	protected String workerDescription;
	
	/**
	 * 头像地址
	 */
	@ApiModelProperty("头像地址")
	protected String avatar;
	
	/**
	 * 擅长
	 */
	@ApiModelProperty("擅长")
	protected String goodAt;
	
	/**
	 * 出诊费
	 */
	@ApiModelProperty("出诊费")
	protected BigDecimal ondoorFee;
	
	
}

























