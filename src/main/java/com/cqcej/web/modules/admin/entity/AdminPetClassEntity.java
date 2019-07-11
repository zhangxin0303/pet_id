package com.cqcej.web.modules.admin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 宠物分类
 * 
 * @author Jia Min
 * @date 2018-09-05 09:45:00
 */
@TableName("ct_pet_class")
@ApiModel("宠物分类")
@Data
public class AdminPetClassEntity implements Serializable {

	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty("ID")
	protected Integer petClassId;

	/**
	 * 上级ID
	 */
	@ApiModelProperty("上级ID")
	protected Integer parentId;

	/**
	 * 图标URL
	 */
	@ApiModelProperty("图标URL")
	protected String iconUrl;

	/**
	 * 首字母
	 */
	@ApiModelProperty("首字母")
	protected String headerWord;

	/**
	 * 分类名
	 */
	@ApiModelProperty("分类名")
	protected String className;

	/**
	 * 排序，从高到低排列
	 */
	@ApiModelProperty("排序，从高到低排列")
	protected Integer sort;
	
	/**
	 * 类别下的所有宠物
	 */
	@ApiModelProperty("类别下的所有宠物")
	List<AdminPetClassEntity> pList;
	
}
