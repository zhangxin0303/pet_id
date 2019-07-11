package com.cqcej.web.modules.common.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 第三方登录
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-10-12 11:07:27
 */
@TableName("ct_user_oauth")
@ApiModel("第三方登录")
@Data
public class UserOauthEntity implements Serializable {

	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty("ID")
	protected Long oatuthId;

	/**
	 * 第三方平台唯一id
	 */
	@ApiModelProperty("第三方平台唯一id")
	protected String uniqueId;

	/**
	 * 对应的本地平台用户id
	 */
	@ApiModelProperty("对应的本地平台用户id")
	protected Long userId;

	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	protected Date createAt;
}
