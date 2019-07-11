package com.cqcej.web.modules.app.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * DESCRIPTION
 *
 * @author Jia Min
 * @email 510573309@qq.com
 * @date 2018-11-09 11:01
 */
@Data
@ApiModel(value = "新增服务表单")
public class MechanismServiceForm {
	
	@ApiModelProperty(value = "商户Id")
	@NotNull(message = "服务Id不能为空")
	private Long mechId;
	
	@ApiModelProperty(value = "服务Id")
	private Long mechServiceId;
	
	@ApiModelProperty(value = "服务类型(1美容服务，2寄养服务，4遛狗服务)")
	@NotNull(message = "服务类型不能为空")
	private Integer serviceType;
	
	@ApiModelProperty(value = "宠物类别")
	@NotNull(message = "宠物类型不能为空")
	private Integer petClassId;
	
	@ApiModelProperty(value = "服务名称")
	@NotBlank(message = "服务名称不能为空")
	private String serviceName;
	
	@ApiModelProperty(value = "服务价格")
	@DecimalMin(value = "0.01",message = "金额必须大于0.01")
	private BigDecimal servicePrice;
	
	
}
