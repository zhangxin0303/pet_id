package com.cqcej.web.modules.app.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * DESCRIPTION
 *
 * @author Jia Min
 * @email 510573309@qq.com
 * @date 2018-11-13 10:16
 */
@Data
@ApiModel(value = "绑定银行卡表单")
public class UserCardForm  {
	
	@ApiModelProperty(value = "姓名")
	@NotBlank(message = "姓名不能为空")
	private String name;
	
	@ApiModelProperty(value = "卡号")
	@NotBlank(message = "卡号不能为空")
	private String bankNo;
	
	@ApiModelProperty(value = "银行名称")
	@NotBlank(message = "银行名称不能为空")
	private String bankName;
}
