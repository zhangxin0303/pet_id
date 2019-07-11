package com.cqcej.web.modules.admin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 资讯
 * 
 * @author Jia Min
 * @email empty
 * @date 2018-07-17 10:41:21
 */
@TableName("ct_article")
@ApiModel("资讯")
public class AdminArticleEntity implements Serializable {

	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty("ID")
	protected Long articleId;

	/**
	 * 宠物分类ID
	 */
	@ApiModelProperty("宠物分类ID")
	protected Integer petClassId;

	/**
	 * 所属板块（1宠物医疗，2宠物美容，3宠物健康）
	 */
	@ApiModelProperty("所属板块（1宠物医疗，2宠物美容，3宠物健康）")
	protected Integer articleType;

	/**
	 * 图标地址
	 */
	@ApiModelProperty("图标地址")
	protected String thumbImageUrl;

	/**
	 * 资讯标题
	 */
	@ApiModelProperty("资讯标题")
	protected String articleTitle;

	/**
	 * 资讯内容
	 */
	@ApiModelProperty("资讯内容")
	protected String articleContent;

	/**
	 * 浏览次数
	 */
	@ApiModelProperty("浏览次数")
	protected Integer viewCount;

	/**
	 * 评论次数
	 */
	@ApiModelProperty("评论次数")
	protected Integer commentCount;

	/**
	 * 点赞次数
	 */
	@ApiModelProperty("点赞次数")
	protected Integer priseCount;

	/**
	 * 排序，越高越前
	 */
	@ApiModelProperty("排序，越高越前")
	protected Integer sort;

	/**
	 * 是否推荐
	 */
	@ApiModelProperty("是否推荐")
	protected Integer isRecommend;

	/**
	 * 发布时间
	 */
	@ApiModelProperty("发布时间")
	protected Date createAt;



	/**
	 * 设置：ID
	 */
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	/**
	 * 获取：ID
	 */
	public Long getArticleId() {
		return articleId;
	}

	/**
	 * 设置：宠物分类ID
	 */
	public void setPetClassId(Integer petClassId) {
		this.petClassId = petClassId;
	}

	/**
	 * 获取：宠物分类ID
	 */
	public Integer getPetClassId() {
		return petClassId;
	}

	/**
	 * 设置：所属板块（1宠物医疗，2宠物美容，3宠物健康）
	 */
	public void setArticleType(Integer articleType) {
		this.articleType = articleType;
	}

	/**
	 * 获取：所属板块（1宠物医疗，2宠物美容，3宠物健康）
	 */
	public Integer getArticleType() {
		return articleType;
	}

	/**
	 * 设置：图标地址
	 */
	public void setThumbImageUrl(String thumbImageUrl) {
		this.thumbImageUrl = thumbImageUrl;
	}

	/**
	 * 获取：图标地址
	 */
	public String getThumbImageUrl() {
		return thumbImageUrl;
	}

	/**
	 * 设置：资讯标题
	 */
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	/**
	 * 获取：资讯标题
	 */
	public String getArticleTitle() {
		return articleTitle;
	}

	/**
	 * 设置：资讯内容
	 */
	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	/**
	 * 获取：资讯内容
	 */
	public String getArticleContent() {
		return articleContent;
	}

	/**
	 * 设置：浏览次数
	 */
	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	/**
	 * 获取：浏览次数
	 */
	public Integer getViewCount() {
		return viewCount;
	}

	/**
	 * 设置：评论次数
	 */
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	/**
	 * 获取：评论次数
	 */
	public Integer getCommentCount() {
		return commentCount;
	}

	/**
	 * 设置：点赞次数
	 */
	public void setPriseCount(Integer priseCount) {
		this.priseCount = priseCount;
	}

	/**
	 * 获取：点赞次数
	 */
	public Integer getPriseCount() {
		return priseCount;
	}

	/**
	 * 设置：排序，越高越前
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * 获取：排序，越高越前
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * 设置：是否推荐
	 */
	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}

	/**
	 * 获取：是否推荐
	 */
	public Integer getIsRecommend() {
		return isRecommend;
	}

	/**
	 * 设置：发布时间
	 */
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	/**
	 * 获取：发布时间
	 */
	public Date getCreateAt() {
		return createAt;
	}
}
