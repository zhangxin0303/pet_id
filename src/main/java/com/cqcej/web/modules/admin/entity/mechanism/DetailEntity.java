package com.cqcej.web.modules.admin.entity.mechanism;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@ApiModel("详细信息")
@Data
public class DetailEntity  implements Serializable {
	/**
	 * 关键字
	 */
	@ApiModelProperty("关键字")
	protected String keywords;
	
	/**
	 * 营业时间
	 */
	@ApiModelProperty("营业时间")
	protected String businessTime;
	
	/**
	 * 描述(详情)
	 */
	@ApiModelProperty("描述(详情)")
	protected String description;
	
	/**
	 * 店铺LOGO
	 */
	@ApiModelProperty("店铺LOGO")
	protected String logoUrl;
	
	/**
	 * 商家状态(1.正常，2.待认证，3.已锁定)
	 */
	@ApiModelProperty("商家状态(1.正常，2.待认证，3.已锁定)")
	protected Integer mechanismStatus;
	
	/**
	 * 营业执照
	 */
	@ApiModelProperty("营业执照")
	protected String businessLicense;
	
	
	/**
	 * 轮播图
	 */
	@ApiModelProperty("营业执照")
	protected List<String> images;
	
	
//	/**
//	 * 服务类型(1美容服务，2寄养服务，4遛狗服务)
//	 */
//	@ApiModelProperty("服务类型(1美容服务，2寄养服务，4遛狗服务)")
//	protected List<Integer> serviceTypes;
	
	
}
