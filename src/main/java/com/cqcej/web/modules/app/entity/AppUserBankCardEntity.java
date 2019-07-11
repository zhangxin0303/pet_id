package com.cqcej.web.modules.app.entity;

import com.cqcej.web.modules.common.entity.UserBankCardEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户绑定的银行卡
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-08-16 15:15
 */
@Data
@ApiModel("已绑定银行卡")
public class AppUserBankCardEntity extends UserBankCardEntity {
	/**
	 * 银行名
	 */
	@ApiModelProperty("银行名")
	private String bankName;
	
	/**
	 * 银行缩略图地址
	 */
	@ApiModelProperty("银行缩略图地址")
	private String iconUrl;
}
