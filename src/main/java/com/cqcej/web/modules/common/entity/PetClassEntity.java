package com.cqcej.web.modules.common.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 宠物分类
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-07 10:07:20
 */
@TableName("ct_pet_class")
@ApiModel("宠物分类")
public class PetClassEntity implements Serializable {
	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty("ID")
	protected  Integer petClassId;

	/**
	 * 上级ID
	 */
	@ApiModelProperty("上级ID")
	protected  Integer parentId;

	/**
	 * 图标URL
	 */
	@ApiModelProperty("图标URL")
	protected  String iconUrl;
	
	/**
	 * 拼音首字母
	 */
	@ApiModelProperty("拼音首字母")
	protected String headerWord;

	/**
	 * 分类名
	 */
	@ApiModelProperty("分类名")
	protected  String className;

	/**
	 * 排序，从高到低排列
	 */
	@ApiModelProperty("排序，从高到低排列")
	protected  Integer sort;


	/**
	 * 设置：ID
	 */
	public void setPetClassId(Integer petClassId) {
		this.petClassId = petClassId;
	}

	/**
	 * 获取：ID
	 */
	public Integer getPetClassId() {
		return petClassId;
	}

	/**
	 * 设置：上级ID
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/**
	 * 获取：上级ID
	 */
	public Integer getParentId() {
		return parentId;
	}

	/**
	 * 设置：图标URL
	 */
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	/**
	 * 获取：图标URL
	 */
	public String getIconUrl() {
		return iconUrl;
	}
	
	public String getHeaderWord() {
		return headerWord;
	}
	
	public void setHeaderWord(String headerWord) {
		this.headerWord = headerWord;
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

	/**
	 * 设置：排序，从高到低排列
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * 获取：排序，从高到低排列
	 */
	public Integer getSort() {
		return sort;
	}
}
