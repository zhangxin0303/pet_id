package com.cqcej.web.modules.app.form;

import com.cqcej.web.common.utils.AppConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * DESCRIPTION
 *
 * @author Jia Min
 * @email 510573309@qq.com
 * @date 2018-11-14 10:02
 */
@ApiModel("接送人员")
@Data
public class MechPickupForm {
	
	/**
	 * ID
	 */
	@ApiModelProperty("ID")
	protected Long userId;
	
	/**
	 * ID
	 */
	@ApiModelProperty("工作者ID")
	protected Long workerId;
	
	/**
	 * ID
	 */
	@ApiModelProperty("mechanismId")
	protected Long mechanismId;
	
	/**
	 * 姓名
	 */
	@ApiModelProperty("姓名")
	@NotBlank(message = "姓名不能为空")
	protected String realname;
	
	/**
	 * 性别
	 */
	@ApiModelProperty("性别1男2女")
	@NotNull(message = "性别不能为空")
	protected Integer sex;
	
	/**
	 * 头像地址
	 */
	@ApiModelProperty("头像地址")
	protected String avatar;
	
	/**
	 * 联系方式
	 */
	@ApiModelProperty("联系方式")
	@NotBlank(message = "手机号不能为空")
	@Pattern(regexp = AppConstants.REGEX_MOBILE, message = "请输入正确的手机号")
	protected String mobile;
	
	/**
	 * 密码
	 */
	@ApiModelProperty("密码")
	protected String password;
	
	/**
	 * 是否接受遛狗
	 */
	@ApiModelProperty("是否接受遛狗(否：21接送者,是：22遛狗人员)")
	protected Integer workerType;
}
