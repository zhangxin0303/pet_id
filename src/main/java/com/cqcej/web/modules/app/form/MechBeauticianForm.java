package com.cqcej.web.modules.app.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * DESCRIPTION
 *
 * @author Jia Min
 * @email 510573309@qq.com
 * @date 2018-11-14 09:38
 */
@ApiModel("新增美容师")
@Data
public class MechBeauticianForm implements Serializable {
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
	@ApiModelProperty("美容师姓名")
	@NotBlank(message = "姓名不能为空")
	protected String realname;
	
	/**
	 * 擅长
	 */
	@ApiModelProperty("擅长")
	protected String goodAt;
	
	/**
	 * 医师介绍
	 */
	@ApiModelProperty("介绍")
	protected String workerDescription;
	
	/**
	 * 头像地址
	 */
	@ApiModelProperty("头像地址")
	protected String avatar;
	
}
