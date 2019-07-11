package com.cqcej.web.modules.admin.entity.mechanism;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@ApiModel("基本信息")
@Data
public class MechanBasicEntity implements Serializable {
	
	
	/**
	 * ID
	 */
	@ApiModelProperty("ID")
	protected Long mechanismId;
	
	/**
	 * ID
	 */
	@ApiModelProperty("userId")
	protected Long userId;
	
	/**
	 * 店铺名称
	 */
	@ApiModelProperty("店铺名称")
	protected String mechanismName;
	
	/**
	 * 店主
	 */
	@ApiModelProperty("店主")
	protected String realname;
	
	/**
	 * 身份证号
	 */
	@ApiModelProperty("身份证号")
	protected String idCard;
	
	/**
	 * 主营分类
	 */
	@ApiModelProperty("主营分类")
	protected Integer mechanismType;
	
	/**
	 * 联系电话
	 */
	@ApiModelProperty("联系电话")
	protected String mobile;
	
	/**
	 * 省份
	 */
	@ApiModelProperty("省份")
	protected Integer provinceId;
	
	/**
	 * 市区
	 */
	@ApiModelProperty("市区")
	protected Integer cityId;
	
	/**
	 * 地区
	 */
	@ApiModelProperty("地区")
	protected Integer areaId;
	
	/**
	 * 详细地址
	 */
	@ApiModelProperty("详细地址")
	protected String address;
	
}
