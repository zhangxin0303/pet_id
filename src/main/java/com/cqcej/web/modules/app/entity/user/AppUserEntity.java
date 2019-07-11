package com.cqcej.web.modules.app.entity.user;

import com.baomidou.mybatisplus.annotations.TableName;
import com.cqcej.web.modules.common.entity.UserEntity;
import com.cqcej.web.modules.common.entity.UserPetsEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 用户
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-13 15:46:48
 */
@TableName("ct_user")
@ApiModel("用户基本信息")
@Data
public class AppUserEntity extends UserEntity {
	/**
	 * 省名
	 */
	@ApiModelProperty("省名")
	private String provinceName;
	
	/**
	 * 市名
	 */
	@ApiModelProperty("市名")
	private String cityName;
	
	/**
	 * 区名
	 */
	@ApiModelProperty("区名")
	private String areaName;
	
	/**
	 * 用户的宠物列表
	 */
	@ApiModelProperty("用户的宠物列表")
	private List<UserPetsEntity> pets;
}
