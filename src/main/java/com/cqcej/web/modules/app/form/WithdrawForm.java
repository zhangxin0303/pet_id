package com.cqcej.web.modules.app.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * 提现表单
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-08-20 14:45
 */
@Data
@ApiModel(value = "提现表单")
public class WithdrawForm {
	@ApiModelProperty("绑定银行卡id")
	@NotNull(message = "提现银行不能为空")
	private Long cardId;
	
	@ApiModelProperty("提现金额")
	@DecimalMin(value = "0.01", message = "金额必须大于0.01")
	private Double amount;
	
	@ApiModelProperty(value = "备注")
	private String note;
}
