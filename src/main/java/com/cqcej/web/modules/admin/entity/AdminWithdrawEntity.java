package com.cqcej.web.modules.admin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 提现申请
 *
 * @author Jia Min
 * @date 2018-08-17 13:58:16
 */
@TableName("ct_withdraw")
@ApiModel("提现申请")
@Data
public class AdminWithdrawEntity implements Serializable {
	
	//同意提现
	public static final int AGREE_STATUS = 1;
	//拒绝提现
	public static final int REFUSE_STATUS = 2;
	
	/**
	 *
	 */
	@TableId
	@ApiModelProperty("")
	protected Long withdrawId;
	
	/**
	 * 用户id
	 */
	@ApiModelProperty("用户id")
	protected Long userId;
	
	/**
	 * 名称
	 */
	@ApiModelProperty("名称")
	protected String name;
	
	
	/**
	 * 银行卡id
	 */
	@ApiModelProperty("银行卡id")
	protected Long cardId;
	
	/**
	 * 收款账号
	 */
	@ApiModelProperty("收款账号")
	protected String bankNo;
	
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
	 * 状态，0默认，1已提现，2失败
	 */
	@ApiModelProperty("状态，0默认，1已提现，2失败")
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
