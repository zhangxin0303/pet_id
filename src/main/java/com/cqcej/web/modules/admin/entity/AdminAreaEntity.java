package com.cqcej.web.modules.admin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 区域字典
 * 
 * @author Jia Min
 * @email empty
 * @date 2018-07-24 10:32:34
 */
@TableName("ct_area")
@ApiModel("区域字典")
@Data
public class AdminAreaEntity implements Serializable {

	/**
	 * 区域主键
	 */
	@TableId
	@ApiModelProperty("区域主键")
	protected Integer areaId;

	/**
	 * 上级主键
	 */
	@ApiModelProperty("上级主键")
	protected Integer parentId;

	/**
	 * 区域名称
	 */
	@ApiModelProperty("区域名称")
	protected String areaName;

	/**
	 * 区域代码
	 */
	@ApiModelProperty("区域代码")
	protected String areaCode;

	/**
	 * 排序
	 */
	@ApiModelProperty("排序")
	protected Integer areaSort;
}
