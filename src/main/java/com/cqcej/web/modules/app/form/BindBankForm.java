package com.cqcej.web.modules.app.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 绑定银行卡
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-08-15 17:19
 */
@Data
@ApiModel(value = "绑定银行卡表单")
public class BindBankForm {
	/**
	 * 银行卡卡号
	 */
	@ApiModelProperty(value = "银行卡卡号")
	@NotBlank(message = "银行卡卡号不能为空")
	private String cardNo;
	
	/**
	 * 姓名
	 */
	@ApiModelProperty(value = "姓名")
	@NotBlank(message = "姓名不能为空")
	private String name;
	
	/**
	 * 手机号
	 */
	@ApiModelProperty(value = "手机号")
	@NotBlank(message = "手机号不能为空")
	private String phoneNo;
	
	/**
	 * 身份证号
	 */
	@ApiModelProperty(value = "身份证号")
	@NotBlank(message = "身份证号不能为空")
	private String idNo;
	
	/**
	 * 验证码
	 */
	@ApiModelProperty(value = "验证码")
	@NotBlank(message = "验证码不能为空")
	private String code;
}
