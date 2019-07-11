package com.cqcej.web.modules.common.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户宠物
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-21 15:17:36
 */
@TableName("ct_user_pets")
@ApiModel("用户宠物")
@Data
public class UserPetsEntity implements Serializable {
	
	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty("ID")
	protected  Long petId;

	/**
	 * 用户ID
	 */
	@ApiModelProperty("用户ID")
	protected  Long userId;
	
	/**
	 * 宠物分类ID
	 */
	@ApiModelProperty("宠物分类ID")
	protected Integer petClassId;

	/**
	 * 宠物图片
	 */
	@ApiModelProperty("宠物图片")
	protected  String imageUrl;

	/**
	 * 宠物名
	 */
	@ApiModelProperty("宠物名")
	protected  String petName;

	/**
	 * 宠物性别
	 */
	@ApiModelProperty("宠物性别")
	protected  Integer petSex;

	/**
	 * 宠物生日
	 */
	@ApiModelProperty(value = "宠物生日", notes = "格式：XXXX-XX-XX")
	protected  Date birthday;
	
	@ApiModelProperty("宠物体重")
	protected BigDecimal petWeight;

	/**
	 * 宠物状态
	 */
	@ApiModelProperty("宠物状态")
	protected  Integer petStatus;

	/**
	 * 发布时间
	 */
	@ApiModelProperty("发布时间")
	protected  Date createAt;
}
