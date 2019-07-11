package com.cqcej.web.modules.app.entity.home;

import com.baomidou.mybatisplus.annotations.TableName;
import com.cqcej.web.modules.common.entity.MechanismServiceEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 机构服务中间表
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-20 11:10:15
 */
@TableName("ct_mechanism_service")
@ApiModel("机构服务中间表")
public class AppMechanismServiceEntity extends MechanismServiceEntity {
	
	/**
	 * 宠物分类名
	 */
	@ApiModelProperty("宠物分类名")
	protected String petClassName;
	
	public String getPetClassName() {
		return petClassName;
	}
	
	public void setPetClassName(String petClassName) {
		this.petClassName = petClassName;
	}
}
