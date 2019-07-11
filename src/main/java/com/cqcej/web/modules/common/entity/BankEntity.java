package com.cqcej.web.modules.common.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 银行
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-08-16 14:53:37
 */
@TableName("ct_bank")
@ApiModel("银行")
@Data
public class BankEntity implements Serializable {

	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty("ID")
	protected Integer bankId;

	/**
	 * 银行名
	 */
	@ApiModelProperty("银行名")
	protected String bankName;

	/**
	 * 缩略图地址
	 */
	@ApiModelProperty("缩略图地址")
	protected String iconUrl;
}
