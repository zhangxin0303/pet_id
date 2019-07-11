package com.cqcej.web.modules.common.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 已绑定银行卡
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-08-16 14:53:37
 */
@TableName("ct_user_bank_card")
@ApiModel("已绑定银行卡")
@Data
public class UserBankCardEntity implements Serializable {

	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty("ID")
	protected Long cardId;

	/**
	 * 用户id
	 */
	@ApiModelProperty("用户id")
	protected Long userId;
	
	/**
	 * 银行id
	 */
	@ApiModelProperty("银行id")
	protected Integer bankId;

	/**
	 * 持卡人姓名
	 */
	@ApiModelProperty("持卡人姓名")
	protected String name;

	/**
	 * 银行卡号
	 */
	@ApiModelProperty("银行卡号")
	protected String bankNo;

	/**
	 * 手机号
	 */
	@ApiModelProperty("手机号")
	protected String mobile;

	/**
	 * 是否默认
	 */
	@ApiModelProperty("是否默认")
	protected Integer isDefault;

	/**
	 * 添加时间
	 */
	@ApiModelProperty("添加时间")
	protected Date createAt;
}
