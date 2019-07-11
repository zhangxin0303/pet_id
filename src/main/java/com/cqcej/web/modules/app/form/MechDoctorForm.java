package com.cqcej.web.modules.app.form;

import com.cqcej.web.common.utils.AppConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DESCRIPTION
 *
 * @author Jia Min
 * @email 510573309@qq.com
 * @date 2018-10-18 13:59
 */
@ApiModel("新增医师表单")
@Data
public class MechDoctorForm implements Serializable {
	
	/**
	 * 医师
	 */
	public static final int WORKER_TYPE_DOCTOR = 20;
	
	/**
	 * 接送者
	 */
	public static final int WORKER_TYPE_PICKUP = 21;
	
	/**
	 * 遛狗人员
	 */
	public static final int WORKER_TYPE_WALKDOG = 22;
	
	/**
	 * 美容师
	 */
	public static final int WORKER_TYPE_BEAUTICIAN = 23;
	
	
	
	/**
	 * ID
	 */
	@ApiModelProperty("用户ID")
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
	@ApiModelProperty("医师姓名")
	@NotBlank(message = "医师姓名不能为空")
	protected String realname;
	
	/**
	 * 手机号码
	 */
	@ApiModelProperty("手机号码")
	@NotBlank(message = "手机号码不能为空")
	@Pattern(regexp = AppConstants.REGEX_MOBILE, message = "请输入正确的手机号")
	protected String mobile;
	
	/**
	 * 密码
	 */
	@ApiModelProperty("密码")
	protected String password;
	
	
	/**
	 * 擅长
	 */
	@ApiModelProperty("擅长")
	protected String goodAt;
	
	/**
	 * 聊天咨询费
	 */
	@ApiModelProperty("聊天咨询费")
	@DecimalMin(value = "0.01",message = "金额必须大于0.01")
	protected BigDecimal chatFee;
	
	/**
	 * 电话咨询费
	 */
	@ApiModelProperty("电话咨询费")
	@DecimalMin(value = "0.01",message = "金额必须大于0.01")
	protected BigDecimal telephoneFee;
	
	/**
	 * 出诊费(上门费)
	 */
	@ApiModelProperty("出诊费(上门费)")
	@DecimalMin(value = "0.01",message = "金额必须大于0.01")
	protected BigDecimal ondoorFee;
	
	/**
	 * 医师介绍
	 */
	@ApiModelProperty("医师介绍")
	protected String workerDescription;
	
	/**
	 * 头像地址
	 */
	@ApiModelProperty("头像地址")
	protected String avatar;
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
		sb.append("\"userId\":")
				.append(userId);
		sb.append(",\"realname\":\"")
				.append(realname).append('\"');
		sb.append(",\"mobile\":\"")
				.append(mobile).append('\"');
		sb.append(",\"goodAt\":\"")
				.append(goodAt).append('\"');
		sb.append(",\"chatFee\":")
				.append(chatFee);
		sb.append(",\"telephoneFee\":")
				.append(telephoneFee);
		sb.append(",\"ondoorFee\":")
				.append(ondoorFee);
		sb.append(",\"workerDescription\":\"")
				.append(workerDescription).append('\"');
		sb.append(",\"avatar\":\"")
				.append(avatar).append('\"');
		sb.append('}');
		return sb.toString();
	}
}
