package com.cqcej.web.modules.app.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.cqcej.web.modules.common.entity.ServiceOrderEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

/**
 * 服务订单
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-07-27 14:24
 */
@EqualsAndHashCode(callSuper = true)
@TableName("ct_service_order")
@ApiModel("工作者订单")
@Data
public class AppWorkerOrderEntity extends ServiceOrderEntity {
	
	@ApiModelProperty("用户昵称")
	private String nickname;
	
	@ApiModelProperty("用户头像地址")
	private String avatar;
	
	@ApiModelProperty("分类名")
	private String className;
	
	@ApiModelProperty("用户手机")
	private String userMobile;
	
	@ApiModelProperty("用户纬度")
	private BigDecimal userLatitude;
	
	@ApiModelProperty("用户经度")
	private BigDecimal userLongitude;
	
	@ApiModelProperty("附件列表")
	private List<String> attaches;
	
	@ApiModelProperty("机构名")
	private String mechanismName;
	
	@ApiModelProperty("机构经度")
	private BigDecimal mechanismLongitude;
	
	@ApiModelProperty("机构纬度")
	private BigDecimal mechanismLatitude;
	
	@ApiModelProperty("机构地址")
	private String mechanismAddress;
	
	@ApiModelProperty("机构省id")
	private Integer mechanismProvinceId;
	
	@ApiModelProperty("机构市id")
	private Integer mechanismCityId;
	
	@ApiModelProperty("机构区id")
	private Integer mechanismAreaId;
	
	@ApiModelProperty("机构电话")
	private String mechanismPhone;
}
