package com.cqcej.web.modules.admin.entity.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@ApiModel("基本信息")
@Data
public class BasicEntity implements Serializable {


    /**
     * 订单ID
     */
    @ApiModelProperty("订单ID")
    protected Long orderId;

    /**
     * 订单号
     */
    @ApiModelProperty("订单号")
    protected String orderNo;

    /**
     * 购买用户
     */
    @ApiModelProperty("购买用户")
    protected String nickname;

    /**
     * 支付方式
     */
    @ApiModelProperty("支付方式")
    protected String paymentName;

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
     * 预约时间
     */
    @ApiModelProperty("预约时间")
    protected Date reserveAt;
    
    /**
     * 下单时间
     */
    @ApiModelProperty("下单时间")
    protected Date payAt;

    /**
     * 订单状态(0默认，10已支付，20待评价，30已完成，41取消-不用退款，42取消-待退款，43取消-已退款)
     */
    @ApiModelProperty("订单状态(0默认，10已支付，20待评价，30已完成，41取消-不用退款，42取消-待退款，43取消-已退款)")
    protected Integer orderStatus;

    /**
     * 下单时间
     */
    @ApiModelProperty("下单时间")
    protected Date createAt;

    /**
     * 付款时间
     */
    @ApiModelProperty("付款时间")
    protected Date pay_at;

    /**
     * 订单金额
     */
    @ApiModelProperty("订单金额")
    protected BigDecimal orderAmount;

    /**
     * 订单来源
     */
    @ApiModelProperty("订单来源")
    protected String source;

    /**
     * 有效时间(医师资讯)
     */
    @ApiModelProperty("有效时间(医师咨询(聊天))")
    protected Date validTime;

    /**
     * 通话时间(医师咨询(电话))
     */
    @ApiModelProperty("通话时间(医师咨询(电话))")
    protected Integer during;

    /**
     * 遛狗时间段(遛狗服务)
     */
    @ApiModelProperty("遛狗时间段(遛狗服务)")
    protected String walkDogTime;

}
