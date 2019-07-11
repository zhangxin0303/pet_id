package com.cqcej.web.modules.admin.entity.mechanism;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


@ApiModel("服务")
@Data
public class ServiceEntity implements Serializable {
	
	/**
	 * ID
	 */
	@ApiModelProperty("mechanismServiceId")
	protected Long mechanismServiceId;
	
	/**
	 * 服务名称
	 */
	@ApiModelProperty("服务名称")
	protected String serviceName;
	
	/**
	 * 定价
	 */
	@ApiModelProperty("定价")
	protected BigDecimal servicePrice;

}
