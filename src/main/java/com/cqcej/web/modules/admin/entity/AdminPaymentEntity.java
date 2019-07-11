package com.cqcej.web.modules.admin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 支付方式
 * 
 * @author Jia Min
 * @date 2018-09-28 13:50:38
 */
@TableName("ct_payment")
@ApiModel("支付方式")
@Data
public class AdminPaymentEntity implements Serializable {

	/**
	 * 
	 */
	@TableId
	@ApiModelProperty("")
	protected Integer paymentId;

	/**
	 * 支付名称
	 */
	@ApiModelProperty("支付名称")
	protected String paymentName;

	/**
	 * 支付方式s
	 */
	@ApiModelProperty("支付方式s")
	protected String payType;

	/**
	 * logo
	 */
	@ApiModelProperty("logo")
	protected String paymentLogo;

	/**
	 * 是否开启0关闭1开启
	 */
	@ApiModelProperty("是否开启0关闭1开启")
	protected Integer paymentStatus;

	/**
	 * 手续费
	 */
	@ApiModelProperty("手续费")
	protected BigDecimal poundage;

	/**
	 * 手续费方式，1百分比2固定值
	 */
	@ApiModelProperty("手续费方式，1百分比2固定值")
	protected Integer poundageType;

	/**
	 * 参数配置
	 */
	@ApiModelProperty("参数配置")
	protected String configParam;

	/**
	 * 客户端（1手机APP，2手机端[微信，浏览器]，3电脑端， 4通用）
	 */
	@ApiModelProperty("客户端（1手机APP，2手机端[微信，浏览器]，3电脑端， 4通用）")
	protected Integer clientType;
}
