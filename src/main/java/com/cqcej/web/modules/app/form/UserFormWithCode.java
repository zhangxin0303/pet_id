/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.cqcej.web.modules.app.form;

import com.cqcej.web.common.utils.AppConstants;
import com.cqcej.web.modules.common.entity.UserEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * 注册表单
 *
 * @author Mark 503580622@qq.com
 * @since 3.1.0 2018-01-25
 */
@ApiModel(value = "注册表单")
@Data
public class UserFormWithCode {
	@ApiModelProperty(value = "手机号")
	@NotBlank(message = "手机号不能为空")
	@Pattern(regexp = AppConstants.REGEX_MOBILE, message = "请输入正确的手机号")
	private String account;
	
	@ApiModelProperty(value = "密码")
	@NotBlank(message = "密码不能为空")
	@Length(min = AppConstants.PASSWORD_LENGTH_MIN, max = AppConstants.PASSWORD_LENGTH_MAX, message = AppConstants.PASSWORD_LENGTH_TIP)
	private String password;
	
	@ApiModelProperty(value = "手机验证码")
	@NotBlank(message = "手机验证码不能为空")
	private String code;
	
	@ApiModelProperty(value = "用户类型，默认普通用户")
	private Integer type = UserEntity.USER_TYPE_NORMAL;
	
	@ApiModelProperty(value = "客户端类型eg. Android|iOS")
	private String client;
	
	@ApiModelProperty(value = "客户端设备id")
	private String deviceToken;
}
