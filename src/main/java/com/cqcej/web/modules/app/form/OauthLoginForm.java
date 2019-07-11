package com.cqcej.web.modules.app.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 第三方登录
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-10-12 10:53
 */
@Data
@ApiModel(value = "第三方登录表单")
public class OauthLoginForm {
	@ApiModelProperty(value = "用户唯一码")
	@NotBlank(message = "用户唯一码不能为空")
	private String uid;
	
	@ApiModelProperty(value = "用户头像地址")
	private String avatar;
	
	@ApiModelProperty(value = "用户昵称")
	private String nickname;
	
	@ApiModelProperty(value = "用户性别")
	private String gender;
	
	@ApiModelProperty(value = "用户登录的客户端")
	@NotBlank(message = "客户端标识不能为空")
	private String client;
	
	@ApiModelProperty(value = "用户设备唯一编号")
	private String deviceToken;
}
