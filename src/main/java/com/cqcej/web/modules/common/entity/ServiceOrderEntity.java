package com.cqcej.web.modules.common.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 服务订单
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-08-10 15:42:51
 */
@TableName("ct_service_order")
@ApiModel("服务订单")
@Data
public class ServiceOrderEntity implements Serializable {
	
	public static final int ORDER_TYPE_CLINIC = 1;
	public static final int ORDER_TYPE_BEAUTY = 2;
	public static final int ORDER_TYPE_HEALTH = 3;
	
	/**
	 * 上门就诊
	 */
	public static final int ORDER_SUBTYPE_CLINIC_ONDOOR = 11;
	/**
	 * 线上预约
	 */
	public static final int ORDER_SUBTYPE_CLINIC_RESERVATION = 12;
	
	/**
	 * 医师咨询，在线
	 */
	public static final int ORDER_SUBTYPE_CLINIC_CONSULTATION_ONLINE = 13;
	
	/**
	 * 医师咨询，电话
	 */
	public static final int ORDER_SUBTYPE_CLINIC_CONSULTATION_TELEPHONE = 14;
	
	/**
	 * 美容接送
	 */
	public static final int ORDER_SUBTYPE_BEAUTY_TRANS = 20;
	
	/**
	 * 遛狗
	 */
	public static final int ORDER_SUBTYPE_HEALTH_WALK_DOG = 31;
	
	/**
	 * 宠物寄养
	 */
	public static final int ORDER_SUBTYPE_HEALTH_FOSTER = 32;
	
	/**
	 * 默认状态
	 */
	public static final int ORDER_STATUS_DEFAULT = 0;
	
	/**
	 * 待使用/已付款
	 */
	public static final int ORDER_STATUS_PAID = 10;
	
	/**
	 * 待遛狗
	 */
	public static final int ORDER_STATUS_PENDING_WALK_DOG = 11;
	
	/**
	 * 遛狗中
	 */
	public static final int ORDER_STATUS_WALKING_DOG = 12;
	
	/**
	 * 遛狗完成
	 */
	public static final int ORDER_STATUS_WALK_DOG_FINISH = 13;
	
	/**
	 * 待到店
	 */
	public static final int ORDER_STATUS_PENDING_ONMECHANISM = 14;
	
	/**
	 * 待美容
	 */
	public static final int ORDER_STATUS_PENDING_BEAUTY = 15;
	
	/**
	 * 美容中
	 */
	public static final int ORDER_STATUS_BEAUTYING = 16;
	
	/**
	 * 美容完成
	 */
	public static final int ORDER_STATUS_BEAUTY_FINISH = 17;
	
	/**
	 * 已到店(待送还这条支线)
	 */
	public static final int ORDER_STATUS_ARRIVE_MECHANISM = 18;
	
	/**
	 * 待评价
	 */
	public static final int ORDER_STATUS_COMMENT = 20;
	
	/**
	 * 预约美容店
	 */
	public static final int ORDER_SUBTYPE_BEAUTY_RESERVATION = 21;
	
	/**
	 * 已完成
	 */
	public static final int ORDER_STATUS_COMPLETE = 30;
	
	/**
	 * 已取消（不用退款）
	 */
	public static final int ORDER_STATUS_CANCEL_NO_REFUND = 41;
	
	/**
	 * 已取消（待退款）
	 */
	public static final int ORDER_STATUS_CANCEL_PENDING_REFUND = 42;
	
	/**
	 * 已取消（已退款）
	 */
	public static final int ORDER_STATUS_CANCEL_REFUND = 43;
	
	/**
	 * 退款失败
	 */
	public static final int ORDER_STATUS_FAIL_REFUND = 44;
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
	 * 工作者ID，(特殊情况，如果是接送订单，表示美容id，接宠还宠用pickup_id和giveback_id表示，如果是遛狗订单，则表示遛狗人员，接送都是一个人)
	 */
	@ApiModelProperty("工作者ID，(特殊情况，如果是接送订单，表示美容id，接宠还宠用pickup_id和giveback_id表示，如果是遛狗订单，则表示遛狗人员，接送都是一个人)")
	protected Long workerId;
	
	/**
	 * 接送者ID
	 */
	@ApiModelProperty("接送者ID")
	protected Long pickupId;
	
	/**
	 * 上门还宠者ID
	 */
	@ApiModelProperty("上门还宠者ID")
	protected Long givebackId;
	
	/**
	 * 订单状态(0默认，10已支付(待上门)，11待遛狗，12遛狗中，13遛狗完成，14待到店，15待美容(已到店)，16美容中，17美容完成，18已到店(送回)，19上门就诊完成，20待评价，30已完成，41取消-不用退款，42取消-待退款，43取消-已退款)
	 */
	@ApiModelProperty("订单状态(0默认，10已支付(待上门)，11待遛狗，12遛狗中，13遛狗完成，14待到店，15待美容(已到店)，16美容中，17美容完成，18已到店(送回)，19上门就诊完成，20待评价，30已完成，41取消-不用退款，42取消-待退款，43取消-已退款)")
	protected Integer orderStatus;
	
	/**
	 * 服务ID
	 */
	@ApiModelProperty("服务ID")
	protected Long serviceId;
	
	/**
	 * 分类ID
	 */
	@ApiModelProperty("分类ID")
	protected Integer petClassId;
	
	/**
	 * 宠物ID
	 */
	@ApiModelProperty("宠物ID")
	protected Long petId;
	
	/**
	 * 支付subject
	 */
	@ApiModelProperty("支付subject")
	protected String subject;
	
	/**
	 * 订单类型(1诊所，2美容，3健康)
	 */
	@ApiModelProperty("订单类型(1诊所，2美容，3健康)")
	protected Integer orderType;
	
	/**
	 * 订单子类型(11上门就诊，12线上预约，13聊天咨询，14电话咨询，20美容接送，21预约美容店，31遛狗服务，32宠物寄养)
	 */
	@ApiModelProperty("订单子类型(11上门就诊，12线上预约，13聊天咨询，14电话咨询，20美容接送，21预约美容店，31遛狗服务，32宠物寄养)")
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
	 * 服务金额
	 */
	@ApiModelProperty("服务金额")
	protected BigDecimal serviceAmount;
	
	/**
	 * 接宠金额
	 */
	@ApiModelProperty("接宠金额")
	protected BigDecimal pickupAmount;
	
	/**
	 * 还宠金额
	 */
	@ApiModelProperty("还宠金额")
	protected BigDecimal givebackAmount;
	
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
	 * 是否接送中
	 */
	@ApiModelProperty("是否接送中")
	protected Integer isDistributing;
	
	/**
	 * 是否支付(0未支付，1已支付)
	 */
	@ApiModelProperty("是否支付(0未支付，1已支付)")
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
	protected Short paymentId;
	
	/**
	 * 用户微信支付，支付成功后查询订单
	 */
	@ApiModelProperty("微信支付，预支付id")
	protected String prepayId;
	
	/**
	 * 支付时间
	 */
	@ApiModelProperty("支付时间")
	protected Date payAt;
	
	/**
	 * 开始时间，比如遛狗等
	 */
	@ApiModelProperty("开始时间，比如遛狗等")
	protected Date beginAt;
	
	/**
	 * 结束时间
	 */
	@ApiModelProperty("结束时间")
	protected Date endAt;
	
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
	 * 联系方式
	 */
	@ApiModelProperty("联系方式")
	protected String linkType;
	
	/**
	 * 用户地址ID，在服务端查询用户坐标用
	 */
	@ApiModelProperty("用户地址ID，在服务端查询用户坐标用")
	protected Long addressId;
	
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
	 * 寄养多久(xxxx/xx/xx - xxxx/xx/xx 是一个时间段)
	 */
	@ApiModelProperty("寄养多久")
	protected String fosterTime;
	
	/**
	 * 遛狗时长，单位分钟
	 */
	@ApiModelProperty("遛狗时长，单位分钟")
	protected Integer walkDogTime;
	
	/**
	 * 咨询时间，针对医师电话咨询
	 */
	@ApiModelProperty("咨询时间，针对医师电话咨询")
	protected Integer during;
	
	/**
	 * 申请退款时间
	 */
	@ApiModelProperty("申请退款时间")
	protected Date applyRefundAt;
	
	/**
	 * 同意退款时间
	 */
	@ApiModelProperty("同意退款时间")
	protected Date agreeRefundAt;
	
	/**
	 * 退款原因(根据配置表ct_settings的refundReason读取)
	 */
	@ApiModelProperty("退款原因(根据配置表ct_settings的refundReason读取)")
	protected Integer refundReason;
	
	/**
	 * 退款说明
	 */
	@ApiModelProperty("退款说明")
	protected String refundExplain;
	
	/**
	 * 订单来源
	 */
	@ApiModelProperty("订单来源")
	protected String source;
	
	/**
	 * 订单创建时间
	 */
	@ApiModelProperty("订单创建时间")
	protected Date createAt;
}
