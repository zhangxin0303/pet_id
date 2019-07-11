package com.cqcej.web.modules.app.entity.dto;

import com.cqcej.web.common.utils.AppConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;

/**
 * DESCRIPTION
 *
 * @author Jia Min
 * @email 510573309@qq.com
 * @date 2018-10-20 14:48
 */
@ApiModel("店铺信息")
@Data
public class MechMechanismDTO implements Serializable {
	
	
	protected Long mechanismId;
	
	protected Long userId;
	
	/**
	 * 诊所名
	 */
	@ApiModelProperty("店铺名称")
	@NotBlank(message = "店铺名称不能为空")
	protected String mechanismName;
	
	
	/**
	 * 营业执照
	 */
	@ApiModelProperty("营业执照")
	protected String businessLicense;
	
	/**
	 * 经营者姓名
	 */
	@ApiModelProperty("经营者姓名")
	@NotBlank(message = "经营者姓名不能为空")
	protected String realname;
	
	/**
	 * 身份证号
	 */
	@ApiModelProperty("身份证号")
	@NotBlank(message = "身份证号不能为空")
	@Length(min = AppConstants.IDCARD_LENGTH, max = AppConstants.IDCARD_LENGTH, message = "身份证号码长度不正确")
	protected String idCard;
	
	
	/**
	 * 联系电话
	 */
	@ApiModelProperty("联系电话")
	@NotBlank(message = "联系电话不能为空")
	protected String telephone;
	
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
	@NotBlank(message = "店铺地址不能为空")
	protected String address;

//	/**
//	 * 收款银行
//	 */
//	@ApiModelProperty("收款银行")
//	protected String bankName;
//
//
//	/**
//	 * 银行账号
//	 */
//	@ApiModelProperty("银行账号")
//	protected String bankId;
//
//	/**
//	 * 账户姓名
//	 */
//	@ApiModelProperty("账户姓名")
//	protected String name;
	
	/**
	 * 店铺关键字
	 */
	@ApiModelProperty("店铺关键字")
	protected String keywords;
	
	/**
	 * 营业时间
	 */
	@ApiModelProperty("营业时间")
	@NotBlank(message = "营业时间不能为空")
	protected String businessTime;
	
	/**
	 * 店家详情
	 */
	@ApiModelProperty("店家详情")
	protected String description;
	
	/**
	 * 机构类型（1诊所2美容院4健康）
	 */
	@ApiModelProperty("服务范围（1诊所2美容院4健康）")
	protected Integer mechanismType = 0;
	
	/**
	 * 店铺LOGO
	 */
	@ApiModelProperty("店铺LOGO")
	protected String logoUrl;
	
	
	/**
	 * 店铺照片
	 */
	@ApiModelProperty("店铺照片")
	protected List<String> images;
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
		sb.append("\"mechanismId\":")
				.append(mechanismId);
		sb.append(",\"userId\":")
				.append(userId);
		sb.append(",\"mechanismName\":\"")
				.append(mechanismName).append('\"');
		sb.append(",\"businessLicense\":\"")
				.append(businessLicense).append('\"');
		sb.append(",\"realname\":\"")
				.append(realname).append('\"');
		sb.append(",\"idCard\":\"")
				.append(idCard).append('\"');
		sb.append(",\"telephone\":\"")
				.append(telephone).append('\"');
		sb.append(",\"provinceId\":")
				.append(provinceId);
		sb.append(",\"cityId\":")
				.append(cityId);
		sb.append(",\"areaId\":")
				.append(areaId);
		sb.append(",\"address\":\"")
				.append(address).append('\"');
		sb.append(",\"keywords\":\"")
				.append(keywords).append('\"');
		sb.append(",\"businessTime\":\"")
				.append(businessTime).append('\"');
		sb.append(",\"description\":\"")
				.append(description).append('\"');
		sb.append(",\"mechanismType\":")
				.append(mechanismType);
		sb.append(",\"logoUrl\":\"")
				.append(logoUrl).append('\"');
		sb.append(",\"images\":")
				.append(images);
		sb.append('}');
		return sb.toString();
	}
}
