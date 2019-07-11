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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * 修改密码表单
 *
 * @author Li HuanLing
 * @author 503580622@qq.com
 * @date 2018-08-07 16:39
 */
@ApiModel(value = "修改密码表单")
public class ChangePasswordForm {
	@ApiModelProperty(value = "原密码")
	@NotBlank(message = "原密码不能为空")
	private String originPassword;
	
	@ApiModelProperty(value = "新密码")
	@NotBlank(message = "新密码不能为空")
	@Length(min = AppConstants.PASSWORD_LENGTH_MIN, max = AppConstants.PASSWORD_LENGTH_MAX, message = AppConstants.PASSWORD_LENGTH_TIP)
	@Pattern(regexp = "^[0-9a-zA-Z/.=$%,#@!^*`~+-_{}';:|\"\\\\\\[\\]]+$", message = "请输入正确的密码，数字，字母和特殊字符组成")
	private String newPassword;
	
	public String getOriginPassword() {
		return originPassword;
	}
	
	public void setOriginPassword(String originPassword) {
		this.originPassword = originPassword;
	}
	
	public String getNewPassword() {
		return newPassword;
	}
	
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
