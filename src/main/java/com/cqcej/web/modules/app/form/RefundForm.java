package com.cqcej.web.modules.app.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 退款表单
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-11-01 11:39
 */
@Data
@ApiModel(value = "退款表单")
public class RefundForm {
	
	@ApiModelProperty(value = "订单Id", required = true)
	@NotEmpty(message = "订单号不能为空")
	private Long orderId;
	
	@ApiModelProperty(value = "退款原因", required = true)
	@NotEmpty(message = "退款原因不能为空")
	private Integer refundReason;
	
	@ApiModelProperty(value = "退款说明")
	private String refundExplain;
	
}
