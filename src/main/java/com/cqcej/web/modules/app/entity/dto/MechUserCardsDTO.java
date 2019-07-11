package com.cqcej.web.modules.app.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * DESCRIPTION
 *
 * @author Jia Min
 * @email 510573309@qq.com
 * @date 2018-11-13 10:03
 */
@ApiModel("用户银行卡信息")
@Data
public class MechUserCardsDTO  implements Serializable {
	
	/**
	 * 银行名称
	 */
	@ApiModelProperty("银行名称")
	protected String bankName;
	
	/**
	 * 银行卡号
	 */
	@ApiModelProperty("银行卡号")
	protected String bankNo;
	
}
