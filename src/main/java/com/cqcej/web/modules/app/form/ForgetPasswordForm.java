package com.cqcej.web.modules.app.form;

import com.cqcej.web.common.utils.AppConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * 忘记密码表单
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-09-29 11:36
 */
@ApiModel(value = "忘记密码")
@Data
public class ForgetPasswordForm {
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
}
