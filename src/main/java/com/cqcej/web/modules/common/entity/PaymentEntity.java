package com.cqcej.web.modules.common.entity;

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
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-08 10:47:09
 */
@TableName("ct_payment")
@ApiModel("支付方式")
@Data
public class PaymentEntity implements Serializable {
	
	/**
	 * APP支付方式
	 */
	public static int CLIENT_TYPE_APP = 1;
	
	/**
	 * 手机端支付方式
	 */
	public static int CLIENT_TYPE_MOBILE = 2;
	
	/**
	 * PC支付方式
	 */
	public static int CLIENT_TYPE_WEB = 3;
	
	/**
	 * 通用支付方式
	 */
	public static int CLIENT_TYPE_COMMON = 4;
	
	/**
	 * 状态：开启
	 */
	public static int PAYMENT_STATUS_OPEN = 1;
	
	/**
	 * 状态：关闭
	 */
	public static int PAYMENT_STATUS_CLOSE = 0;
	
	/**
	 *
	 */
	@TableId
	@ApiModelProperty("ID")
	protected Integer paymentId;
	
	/**
	 * 支付名称
	 */
	@ApiModelProperty("支付名称")
	protected String paymentName;
	
	/**
	 * 支付类型
	 */
	@ApiModelProperty("支付类型")
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
