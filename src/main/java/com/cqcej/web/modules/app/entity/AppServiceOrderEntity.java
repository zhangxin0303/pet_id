package com.cqcej.web.modules.app.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.cqcej.web.modules.common.entity.ServiceOrderEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 服务订单
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-06-04 11:08:08
 */
@TableName("ct_service_order")
@ApiModel("服务订单")
@Data
public class AppServiceOrderEntity extends ServiceOrderEntity {
	
	/**
	 * 机构名称
	 */
	@ApiModelProperty("机构名称")
	private String mechanismName;
	
	/**
	 * 机构关键词
	 */
	@ApiModelProperty("机构关键词")
	private String keywords;
	
	/**
	 * 机构评星
	 */
	@ApiModelProperty("机构评星")
	private Integer stars;
	
	/**
	 * 机构图片
	 */
	@ApiModelProperty("机构图片")
	private String mechanismImage;
	
	/**
	 * 机构所在地区
	 */
	@ApiModelProperty("机构所在地区")
	private String mechanismAreaName;
	
	/**
	 * 服务名称
	 */
	@ApiModelProperty("服务名称")
	private String serviceName;
	
	/**
	 * 服务价格
	 */
	@ApiModelProperty("服务价格")
	private BigDecimal servicePrice;
	
	/**
	 * 工作者名称
	 */
	@ApiModelProperty("工作者名称")
	private String workerName;
	
	/**
	 * 经度
	 */
	@ApiModelProperty("机构经度")
	private BigDecimal mechanismLongitude;
	
	/**
	 * 纬度
	 */
	@ApiModelProperty("机构纬度")
	private BigDecimal mechanismLatitude;
	
	/**
	 * 接送者名称
	 */
	@ApiModelProperty("接送者名称")
	private String pickupName;
	
	/**
	 * 宠物名称
	 */
	@ApiModelProperty("宠物名称")
	private String petName;
	
	/**
	 * 宠物品种
	 */
	@ApiModelProperty("宠物品种")
	private String className;
	
	
	/**
	 * 工作者经度
	 */
	@ApiModelProperty("工作者经度")
	private BigDecimal workerLongitude;
	
	/**
	 * 工作者纬度
	 */
	@ApiModelProperty("工作者纬度")
	private BigDecimal workerLatitude;
	
	/**
	 * 用户名称
	 */
	@ApiModelProperty("用户名称")
	private String nickname;
	
	/**
	 * 宠物品种
	 */
	@ApiModelProperty("手机号码")
	private String userMobile;
	
}
