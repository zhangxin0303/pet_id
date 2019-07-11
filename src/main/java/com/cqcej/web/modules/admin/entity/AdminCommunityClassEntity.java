package com.cqcej.web.modules.admin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 社区分类
 * 
 * @author Jia Min
 * @email empty
 * @date 2018-07-19 13:36:25
 */
@TableName("ct_community_class")
@ApiModel("社区分类")
public class AdminCommunityClassEntity implements Serializable {

	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty("ID")
	protected Integer classId;

	/**
	 * 分类名
	 */
	@ApiModelProperty("版块名")
	protected String className;

	/**
	 * 帖子内容
	 */
	@ApiModelProperty("版块简介")
	protected String classIntro;

	/**
	 * 头像地址
	 */
	@ApiModelProperty("头像地址")
	protected String classAvatar;

	/**
	 * 帖子数
	 */
	@ApiModelProperty("帖子数")
	protected Integer communityCount;

	/**
	 * 最新回复
	 */
	@ApiModelProperty("最新回复")
	protected Date latestTime;

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

	public String getClassIntro() {
		return classIntro;
	}

	public void setClassIntro(String classIntro) {
		this.classIntro = classIntro;
	}

	public String getClassAvatar() {
		return classAvatar;
	}

	public void setClassAvatar(String classAvatar) {
		this.classAvatar = classAvatar;
	}

	public Integer getCommunityCount() {
		return communityCount;
	}

	public void setCommunityCount(Integer communityCount) {
		this.communityCount = communityCount;
	}

	public Date getLatestTime() {
		return latestTime;
	}

	public void setLatestTime(Date latestTime) {
		this.latestTime = latestTime;
	}
}
