package com.cqcej.web.modules.admin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 协议
 * 
 * @author Jia Min
 * @date 2018-09-06 16:37:03
 */
@TableName("ct_agreement")
@ApiModel("协议(邮箱)")
@Data
public class AdminAgreementEntity implements Serializable {

	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty("ID")
	protected Long agreeId;

	/**
	 * 协议代号
	 */
	@ApiModelProperty("协议代号")
	protected String agreeCode;

	/**
	 * 协议名称
	 */
	@ApiModelProperty("协议名称")
	protected String agreeName;

	/**
	 * 协议内容
	 */
	@ApiModelProperty("协议内容")
	protected String agreeContent;
	
	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	protected String createTime;
	
}
