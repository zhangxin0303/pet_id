package com.cqcej.web.modules.common.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 社区分类
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-30 16:30:26
 */
@TableName("ct_community_class")
@ApiModel("社区分类")
public class CommunityClassEntity implements Serializable {

	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty("ID")
	protected Integer classId;

	/**
	 * 分类名
	 */
	@ApiModelProperty("分类名")
	protected String className;


	/**
	 * 设置：ID
	 */
	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	/**
	 * 获取：ID
	 */
	public Integer getClassId() {
		return classId;
	}

	/**
	 * 设置：分类名
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * 获取：分类名
	 */
	public String getClassName() {
		return className;
	}
}
