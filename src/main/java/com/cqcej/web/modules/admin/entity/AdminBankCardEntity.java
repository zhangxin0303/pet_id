package com.cqcej.web.modules.admin.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("银行卡信息")
@Data
public class AdminBankCardEntity implements Serializable {
	
	/**
	 * 收款银行
	 */
	@ApiModelProperty("收款银行")
	protected String bankName;
	
	/**
	 * 银行账号
	 */
	@ApiModelProperty("银行账号")
	protected String bankNo;
	
	/**
	 * 账户名称
	 */
	@ApiModelProperty("账户名称")
	protected String name;
}
