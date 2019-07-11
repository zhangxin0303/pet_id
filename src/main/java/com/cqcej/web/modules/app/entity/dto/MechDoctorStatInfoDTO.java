package com.cqcej.web.modules.app.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * DESCRIPTION
 *
 * @author Jia Min
 * @email 510573309@qq.com
 * @date 2018-11-13 14:32
 */
@ApiModel("医师订单统计")
@Data
public class MechDoctorStatInfoDTO implements Serializable {
	
	/**
	 * 金额
	 */
	@ApiModelProperty("历史总金额")
	protected BigDecimal totalSumAmount;
	
	/**
	 * 订单
	 */
	@ApiModelProperty("当月总订单")
	protected Integer orderCount;
	
	/**
	 * 金额
	 */
	@ApiModelProperty("当月总金额")
	protected BigDecimal sumAmount;
	
	/**
	 * 明细
	 */
	@ApiModelProperty("明细")
	protected List<MechDoctorStatisticsDTO> list;
}
