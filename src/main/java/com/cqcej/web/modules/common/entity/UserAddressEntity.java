package com.cqcej.web.modules.common.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用户收货地址
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-28 17:22:58
 */
@TableName("ct_user_address")
@ApiModel("用户收货地址")
@Data
public class UserAddressEntity implements Serializable {


	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty("ID")
	protected Long addressId;

	/**
	 * 用户ID
	 */
	@ApiModelProperty("用户ID")
	protected Long userId;
	
	/**
	 * 收货人姓名
	 */
	@ApiModelProperty("收货人姓名")
	protected String acceptName;

	/**
	 * 手机号
	 */
	@ApiModelProperty("手机号")
	protected String mobile;

	/**
	 * 省ID
	 */
	@ApiModelProperty("省ID")
	protected Integer provinceId;

	/**
	 * 市ID
	 */
	@ApiModelProperty("市ID")
	protected Integer cityId;

	/**
	 * 区ID
	 */
	@ApiModelProperty("区ID")
	protected Integer areaId;

	/**
	 * 详细地址
	 */
	@ApiModelProperty("详细地址")
	protected String detailAddress;
	
	/**
	 * 经度
	 */
	@ApiModelProperty("经度")
	protected BigDecimal longitude;
	
	/**
	 * 纬度
	 */
	@ApiModelProperty("纬度")
	protected BigDecimal latitude;

	/**
	 * 是否默认
	 */
	@ApiModelProperty("是否默认")
	protected Integer isDefault;

}
