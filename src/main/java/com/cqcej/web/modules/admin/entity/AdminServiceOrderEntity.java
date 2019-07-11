package com.cqcej.web.modules.admin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 服务订单
 * 
 * @author Jia Min
 * @email empty
 * @date 2018-07-24 14:09:29
 */
@TableName("ct_service_order")
@ApiModel("服务订单")
public class AdminServiceOrderEntity implements Serializable {

	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty("ID")
	protected Long orderId;

	/**
	 * 用户ID
	 */
	@ApiModelProperty("用户ID")
	protected Long userId;

	/**
	 * 机构ID
	 */
	@ApiModelProperty("机构ID")
	protected Long mechanismId;

	/**
	 * 工作者ID
	 */
	@ApiModelProperty("工作者ID")
	protected Long workerId;

	/**
	 * 接送者ID
	 */
	@ApiModelProperty("接送者ID")
	protected Long pickupId;

	/**
	 * 服务ID
	 */
	@ApiModelProperty("服务ID")
	protected Long serviceId;

	/**
	 * 订单类型(1诊所，2美容，3健康)
	 */
	@ApiModelProperty("订单类型(1诊所，2美容，3健康)")
	protected Integer orderType;

	/**
	 * 订单子类型(11上门就诊，12线上预约，13医师咨询，31遛狗服务，32宠物寄养)
	 */
	@ApiModelProperty("订单子类型(11上门就诊，12线上预约，13聊天咨询，14电话咨询，20美容接送，31遛狗服务，32宠物寄养)")
	protected Integer orderSubtype;

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
	 * 优惠金额
	 */
	@ApiModelProperty("优惠金额")
	protected BigDecimal discountAmount;

	/**
	 * 接送金额
	 */
	@ApiModelProperty("接送金额")
	protected BigDecimal pickupAmount;

	/**
	 * 抵扣积分（使用了多少积分）
	 */
	@ApiModelProperty("抵扣积分（使用了多少积分）")
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
	 * 支付金额=订单金额-优惠金额
	 */
	@ApiModelProperty("支付金额=订单金额-优惠金额")
	protected BigDecimal payAmount;

	/**
	 * 预约时间
	 */
	@ApiModelProperty("预约时间")
	protected Date reserveAt;

	/**
	 * 订单状态(0默认，10已支付，20待评价，30已完成，41取消-不用退款，42取消-待退款，43取消-已退款)
	 */
	@ApiModelProperty("订单状态(0默认，10已支付(待上门)，11待遛狗，12遛狗中，13遛狗完成，14待到店，15待美容(已到店)，16美容中，" +
			"17美容完成，18已到店(送回)，20待评价，30已完成，41取消-不用退款，42取消-待退款，43取消-已退款)")
	protected Integer orderStatus;

	/**
	 * 是否支付
	 */
	@ApiModelProperty("是否支付")
	protected Integer isPay;

	/**
	 * 支付单号，由支付宝微信等返回
	 */
	@ApiModelProperty("支付单号，由支付宝微信等返回")
	protected String tradeNo;

	/**
	 * 支付方式
	 */
	@ApiModelProperty("支付方式")
	protected Integer paymentId;

	/**
	 * 支付时间
	 */
	@ApiModelProperty("支付时间")
	protected Date payAt;

	/**
	 * 描述，备注
	 */
	@ApiModelProperty("描述，备注")
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
	 * 是否接送（针对宠物寄养）
	 */
	@ApiModelProperty("是否接送（针对宠物寄养）")
	protected Integer isPickup;

	/**
	 * 寄养多久
	 */
	@ApiModelProperty("寄养多久")
	protected String fosterTime;

	/**
	 * 订单创建时间
	 */
	@ApiModelProperty("订单创建时间")
	protected Date createAt;

	/**
	 * 遛狗时间段
	 */
	@ApiModelProperty("遛狗时间段")
	protected String walkDogTime;

	/**
	 * 用户昵称
	 */
	@ApiModelProperty("用户昵称")
	protected String nickname;

	/**
	 * 商家名称
	 */
	@ApiModelProperty("商家名称")
	protected String mechanismName;

	/**
	 * 宠物ID
	 */
	@ApiModelProperty("宠物ID")
	protected Long petId;

	/**
	 * 开始时间
	 */
	@ApiModelProperty("开始时间")
	protected Date beginAt;

	/**
	 * 结束时间
	 */
	@ApiModelProperty("结束时间")
	protected Date endAt;

	/**
	 * 还宠者ID
	 */
	@ApiModelProperty("还宠者ID")
	protected Long givebackId;

	/**
	 * 订单来源
	 */
	@ApiModelProperty("订单来源")
	protected String source;


	/**
	 * 设置：ID
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/**
	 * 获取：ID
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
	 * 设置：机构ID
	 */
	public void setMechanismId(Long mechanismId) {
		this.mechanismId = mechanismId;
	}

	/**
	 * 获取：机构ID
	 */
	public Long getMechanismId() {
		return mechanismId;
	}

	/**
	 * 设置：工作者ID
	 */
	public void setWorkerId(Long workerId) {
		this.workerId = workerId;
	}

	/**
	 * 获取：工作者ID
	 */
	public Long getWorkerId() {
		return workerId;
	}

	/**
	 * 设置：接送者ID
	 */
	public void setPickupId(Long pickupId) {
		this.pickupId = pickupId;
	}

	/**
	 * 获取：接送者ID
	 */
	public Long getPickupId() {
		return pickupId;
	}

	/**
	 * 设置：服务ID
	 */
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	/**
	 * 获取：服务ID
	 */
	public Long getServiceId() {
		return serviceId;
	}

	/**
	 * 设置：订单类型(1诊所，2美容，3健康)
	 */
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	/**
	 * 获取：订单类型(1诊所，2美容，3健康)
	 */
	public Integer getOrderType() {
		return orderType;
	}

	/**
	 * 设置：订单子类型(11上门就诊，12线上预约，13医师咨询，31遛狗服务，32宠物寄养)
	 */
	public void setOrderSubtype(Integer orderSubtype) {
		this.orderSubtype = orderSubtype;
	}

	/**
	 * 获取：订单子类型(11上门就诊，12线上预约，13医师咨询，31遛狗服务，32宠物寄养)
	 */
	public Integer getOrderSubtype() {
		return orderSubtype;
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
	 * 设置：优惠金额
	 */
	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	/**
	 * 获取：优惠金额
	 */
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	/**
	 * 设置：接送金额
	 */
	public void setPickupAmount(BigDecimal pickupAmount) {
		this.pickupAmount = pickupAmount;
	}

	/**
	 * 获取：接送金额
	 */
	public BigDecimal getPickupAmount() {
		return pickupAmount;
	}

	/**
	 * 设置：抵扣积分（使用了多少积分）
	 */
	public void setScoreDiscount(Integer scoreDiscount) {
		this.scoreDiscount = scoreDiscount;
	}

	/**
	 * 获取：抵扣积分（使用了多少积分）
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
	 * 设置：支付金额=订单金额-优惠金额
	 */
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	/**
	 * 获取：支付金额=订单金额-优惠金额
	 */
	public BigDecimal getPayAmount() {
		return payAmount;
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
	 * 设置：订单状态(0默认，10已支付，20待评价，30已完成，41取消-不用退款，42取消-待退款，43取消-已退款)
	 */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * 获取：订单状态(0默认，10已支付，20待评价，30已完成，41取消-不用退款，42取消-待退款，43取消-已退款)
	 */
	public Integer getOrderStatus() {
		return orderStatus;
	}

	/**
	 * 设置：是否支付
	 */
	public void setIsPay(Integer isPay) {
		this.isPay = isPay;
	}

	/**
	 * 获取：是否支付
	 */
	public Integer getIsPay() {
		return isPay;
	}

	/**
	 * 设置：支付单号，由支付宝微信等返回
	 */
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	/**
	 * 获取：支付单号，由支付宝微信等返回
	 */
	public String getTradeNo() {
		return tradeNo;
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
	 * 设置：支付时间
	 */
	public void setPayAt(Date payAt) {
		this.payAt = payAt;
	}

	/**
	 * 获取：支付时间
	 */
	public Date getPayAt() {
		return payAt;
	}

	/**
	 * 设置：描述，备注
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * 获取：描述，备注
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
	 * 设置：是否接送（针对宠物寄养）
	 */
	public void setIsPickup(Integer isPickup) {
		this.isPickup = isPickup;
	}

	/**
	 * 获取：是否接送（针对宠物寄养）
	 */
	public Integer getIsPickup() {
		return isPickup;
	}

	/**
	 * 设置：寄养多久
	 */
	public void setFosterTime(String fosterTime) {
		this.fosterTime = fosterTime;
	}

	/**
	 * 获取：寄养多久
	 */
	public String getFosterTime() {
		return fosterTime;
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

	/**
	 * 设置：遛狗时间段
	 */
	public void setWalkDogTime(String walkDogTime) {
		this.walkDogTime = walkDogTime;
	}

	/**
	 * 获取：遛狗时间段
	 */
	public String getWalkDogTime() {
		return walkDogTime;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMechanismName() {
		return mechanismName;
	}

	public void setMechanismName(String mechanismName) {

		this.mechanismName = mechanismName;
	}

	public Long getPetId() {
		return petId;
	}

	public void setPetId(Long petId) {
		this.petId = petId;
	}

	public Date getBeginAt() {
		return beginAt;
	}

	public void setBeginAt(Date beginAt) {
		this.beginAt = beginAt;
	}

	public Date getEndAt() {
		return endAt;
	}

	public void setEndAt(Date endAt) {
		this.endAt = endAt;
	}

	public Long getGivebackId() {
		return givebackId;
	}

	public void setGivebackId(Long givebackId) {
		this.givebackId = givebackId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}
