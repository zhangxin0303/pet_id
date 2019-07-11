package com.cqcej.web.modules.app.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 工作者收入日志
 * 
 * @author Jia Min
 * @date 2018-11-08 14:30:33
 */
@TableName("ct_worker_income_log")
@ApiModel("工作者收入日志")
@Data
public class AppWorkerIncomeLogEntity implements Serializable {

	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty("ID")
	protected Long logId;

	/**
	 * 用户id
	 */
	@ApiModelProperty("用户id")
	protected Long userId;

	/**
	 * 订单id
	 */
	@ApiModelProperty("订单id")
	protected Long orderId;

	/**
	 * 收入
	 */
	@ApiModelProperty("收入")
	protected BigDecimal amount;

	/**
	 * 日期
	 */
	@ApiModelProperty("日期")
	protected Date createAt;
}
