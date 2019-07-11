package com.cqcej.web.modules.admin.entity.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@ApiModel("订单统计")
@Data
public class OrderStatisticsEntity implements Serializable {



    /**
     * 日期
     */
    @ApiModelProperty("日期")
    protected Date createAt;

    /**
     * 订单数
     */
    @ApiModelProperty("订单数")
    protected Long orderCount;

    /**
     * 订单金额
     */
    @ApiModelProperty("订单总金额")
    protected BigDecimal orderSum;

    /**
     * 订单数
     */
    @ApiModelProperty("支付订单数")
    protected Long payCount;

    /**
     * 订单金额
     */
    @ApiModelProperty("实际成交金额")
    protected BigDecimal paySum;
    
    /**
     * 原价总金额
     */
    @ApiModelProperty("原价总金额")
    protected BigDecimal formerSum;
    
    /**
     * 平台分成
     */
    @ApiModelProperty("平台分成")
    protected BigDecimal platformMoney;
}
