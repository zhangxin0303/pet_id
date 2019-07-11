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
 * 
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-08-15 15:16:39
 */
@TableName("ct_withdraw")
@ApiModel("提现申请")
@Data
public class WithdrawEntity implements Serializable {
	
	public static final int TYPE_TRANS = 1;
	
	public static final int TYPE_BUSINESS = 2;

	/**
	 * 
	 */
	@TableId
	@ApiModelProperty("ID")
	protected Long withdrawId;

	/**
	 * 用户id
	 */
	@ApiModelProperty("用户id")
	protected Long userId;

	/**
	 * 银行id
	 */
	@ApiModelProperty("绑定银行卡id")
	protected Long cardId;
	
	/**
	 * 类型，1配送，2商家
	 */
	@ApiModelProperty("类型，1配送，2商家")
	protected Integer type;

	/**
	 * 提现金额
	 */
	@ApiModelProperty("提现金额")
	protected BigDecimal amount;

	/**
	 * 当前余额
	 */
	@ApiModelProperty("当前余额")
	protected BigDecimal balance;

	/**
	 * 状态，0默认，1已提现
	 */
	@ApiModelProperty("状态，0默认，1已提现")
	protected Integer status;
	
	/**
	 * 备注
	 */
	@ApiModelProperty("备注")
	protected String note;

	/**
	 * 提现申请时间
	 */
	@ApiModelProperty("提现申请时间")
	protected Date createAt;

	/**
	 * 提现成功时间
	 */
	@ApiModelProperty("提现成功时间")
	protected Date withdrawAt;
}
