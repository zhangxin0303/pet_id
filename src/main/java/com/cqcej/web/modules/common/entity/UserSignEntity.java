package com.cqcej.web.modules.common.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户签到记录
 * 
 * @author Jia Min
 * @date 2018-11-23 11:15:11
 */
@TableName("ct_user_sign")
@ApiModel("用户签到记录")
@Data
public class UserSignEntity implements Serializable {

	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty("ID")
	protected Long signId;

	/**
	 * user_id
	 */
	@ApiModelProperty("user_id")
	protected Long userId;

	/**
	 * 签到时间
	 */
	@ApiModelProperty("签到时间")
	protected Date createAt;
}
