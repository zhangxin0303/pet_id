package com.cqcej.web.modules.admin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商城订单
 * 
 * @author Jia Min
 * @email empty
 * @date 2018-07-23 14:32:40
 */
@TableName("ct_shop_order")
@ApiModel("商城订单")
public class AdminShopOrderEntity implements Serializable {

	/**
	 * 
	 */
	@TableId
	@ApiModelProperty("")
	protected Long orderId;

	/**
	 * 用户ID
	 */
	@ApiModelProperty("用户ID")
	protected Long userId;

	/**
	 * 订单号
	 */
	@ApiModelProperty("订单号")
	protected String orderNo;

	/**
	 * 订单总金额
	 */
	@ApiModelProperty("订单总金额")
	protected BigDecimal orderAmount;

	/**
	 * 总运费金额
	 */
	@ApiModelProperty("总运费金额")
	protected BigDecimal freightAmount;

	/**
	 * 折扣金额
	 */
	@ApiModelProperty("折扣金额")
	protected BigDecimal discountAmount;

	/**
	 * 积分抵扣数量
	 */
	@ApiModelProperty("积分抵扣数量")
	protected Integer scoreDiscount;

	/**
	 * 积分抵扣金额
	 */
	@ApiModelProperty("积分抵扣金额")
	protected BigDecimal scoreDiscountAmount;

	/**
	 * 优惠券ID
	 */
	@ApiModelProperty("优惠券ID")
	protected Long couponId;

	/**
	 * 优惠券抵扣金额
	 */
	@ApiModelProperty("优惠券抵扣金额")
	protected BigDecimal couponDiscountAmount;

	/**
	 * 付款总金额
	 */
	@ApiModelProperty("付款总金额")
	protected BigDecimal payAmount;

	/**
	 * 订单状态（1生成订单,2支付订单,3取消订单(客户触发),4作废订单(管理员触发),5完成订单,6退款(订单完成后),7部分退款(订单完成后)）
	 */
	@ApiModelProperty("订单状态（1生成订单,2支付订单,3取消订单(客户触发),4作废订单(管理员触发),5完成订单,6退款(订单完成后),7部分退款(订单完成后)）")
	protected Integer orderStatus;

	/**
	 * 支付方式
	 */
	@ApiModelProperty("支付方式")
	protected Integer paymentId;

	/**
	 * 是否支付0未支付，1已支付
	 */
	@ApiModelProperty("是否支付0未支付，1已支付")
	protected Integer isPay;

	/**
	 * 支付单号（由第三方支付平台返回）
	 */
	@ApiModelProperty("支付单号（由第三方支付平台返回）")
	protected String tradeNo;

	/**
	 * 付款时间
	 */
	@ApiModelProperty("付款时间")
	protected Date payAt;

	/**
	 * 用户备注
	 */
	@ApiModelProperty("用户备注")
	protected String notes;

	/**
	 * 省ID
	 */
	@ApiModelProperty("省ID")
	protected Integer provinceId;

	/**
	 * 市ID
	 */
	@ApiModelProperty("市ID")
	protected Integer cityId;

	/**
	 * 区ID
	 */
	@ApiModelProperty("区ID")
	protected Integer areaId;

	/**
	 * 详细地址
	 */
	@ApiModelProperty("详细地址")
	protected String address;

	/**
	 * 订单创建时间
	 */
	@ApiModelProperty("订单创建时间")
	protected Date createAt;


	/**
	 * 设置：
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/**
	 * 获取：
	 */
	public Long getOrderId() {
		return orderId;
	}

	/**
	 * 设置：用户ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 获取：用户ID
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 设置：订单号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * 获取：订单号
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * 设置：订单总金额
	 */
	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	/**
	 * 获取：订单总金额
	 */
	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	/**
	 * 设置：总运费金额
	 */
	public void setFreightAmount(BigDecimal freightAmount) {
		this.freightAmount = freightAmount;
	}

	/**
	 * 获取：总运费金额
	 */
	public BigDecimal getFreightAmount() {
		return freightAmount;
	}

	/**
	 * 设置：折扣金额
	 */
	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	/**
	 * 获取：折扣金额
	 */
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	/**
	 * 设置：积分抵扣数量
	 */
	public void setScoreDiscount(Integer scoreDiscount) {
		this.scoreDiscount = scoreDiscount;
	}

	/**
	 * 获取：积分抵扣数量
	 */
	public Integer getScoreDiscount() {
		return scoreDiscount;
	}

	/**
	 * 设置：积分抵扣金额
	 */
	public void setScoreDiscountAmount(BigDecimal scoreDiscountAmount) {
		this.scoreDiscountAmount = scoreDiscountAmount;
	}

	/**
	 * 获取：积分抵扣金额
	 */
	public BigDecimal getScoreDiscountAmount() {
		return scoreDiscountAmount;
	}

	/**
	 * 设置：优惠券ID
	 */
	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}

	/**
	 * 获取：优惠券ID
	 */
	public Long getCouponId() {
		return couponId;
	}

	/**
	 * 设置：优惠券抵扣金额
	 */
	public void setCouponDiscountAmount(BigDecimal couponDiscountAmount) {
		this.couponDiscountAmount = couponDiscountAmount;
	}

	/**
	 * 获取：优惠券抵扣金额
	 */
	public BigDecimal getCouponDiscountAmount() {
		return couponDiscountAmount;
	}

	/**
	 * 设置：付款总金额
	 */
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	/**
	 * 获取：付款总金额
	 */
	public BigDecimal getPayAmount() {
		return payAmount;
	}

	/**
	 * 设置：订单状态（1生成订单,2支付订单,3取消订单(客户触发),4作废订单(管理员触发),5完成订单,6退款(订单完成后),7部分退款(订单完成后)）
	 */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * 获取：订单状态（1生成订单,2支付订单,3取消订单(客户触发),4作废订单(管理员触发),5完成订单,6退款(订单完成后),7部分退款(订单完成后)）
	 */
	public Integer getOrderStatus() {
		return orderStatus;
	}

	/**
	 * 设置：支付方式
	 */
	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	/**
	 * 获取：支付方式
	 */
	public Integer getPaymentId() {
		return paymentId;
	}

	/**
	 * 设置：是否支付0未支付，1已支付
	 */
	public void setIsPay(Integer isPay) {
		this.isPay = isPay;
	}

	/**
	 * 获取：是否支付0未支付，1已支付
	 */
	public Integer getIsPay() {
		return isPay;
	}

	/**
	 * 设置：支付单号（由第三方支付平台返回）
	 */
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	/**
	 * 获取：支付单号（由第三方支付平台返回）
	 */
	public String getTradeNo() {
		return tradeNo;
	}

	/**
	 * 设置：付款时间
	 */
	public void setPayAt(Date payAt) {
		this.payAt = payAt;
	}

	/**
	 * 获取：付款时间
	 */
	public Date getPayAt() {
		return payAt;
	}

	/**
	 * 设置：用户备注
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * 获取：用户备注
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * 设置：省ID
	 */
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	/**
	 * 获取：省ID
	 */
	public Integer getProvinceId() {
		return provinceId;
	}

	/**
	 * 设置：市ID
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	/**
	 * 获取：市ID
	 */
	public Integer getCityId() {
		return cityId;
	}

	/**
	 * 设置：区ID
	 */
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	/**
	 * 获取：区ID
	 */
	public Integer getAreaId() {
		return areaId;
	}

	/**
	 * 设置：详细地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获取：详细地址
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置：订单创建时间
	 */
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	/**
	 * 获取：订单创建时间
	 */
	public Date getCreateAt() {
		return createAt;
	}
}
