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
 * 遛狗轨迹日志
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-09-10 10:57:42
 */
@TableName("ct_trajectory")
@ApiModel("遛狗轨迹日志")
@Data
public class TrajectoryEntity implements Serializable {

	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty("ID")
	protected Long trajectoryId;

	/**
	 * 订单ID
	 */
	@ApiModelProperty("订单ID")
	protected Long orderId;

	/**
	 * 遛狗距离
	 */
	@ApiModelProperty("遛狗距离")
	protected BigDecimal distance;

	/**
	 * 遛狗坐标集合
	 */
	@ApiModelProperty("遛狗坐标集合")
	protected String trajectory;

	/**
	 * 遛狗完成时间
	 */
	@ApiModelProperty("遛狗完成时间")
	protected Date createAt;
}
