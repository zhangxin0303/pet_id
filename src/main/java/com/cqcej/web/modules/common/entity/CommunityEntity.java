package com.cqcej.web.modules.common.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 社区
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-30 16:30:26
 */
@TableName("ct_community")
@Data
public class CommunityEntity implements Serializable {

	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty("ID")
	protected Long communityId;

	/**
	 * 发布帖子ID
	 */
	@ApiModelProperty("发布帖子ID")
	protected Long userId;

	/**
	 * 社区分类ID
	 */
	@ApiModelProperty("社区分类ID")
	protected Integer classId;
	
	/**
	 * 社区宠物分类
	 */
	@ApiModelProperty("社区宠物分类")
	protected Integer petClassId;

	/**
	 * 帖子标题
	 */
	@ApiModelProperty("帖子标题")
	protected String title;
	
	/**
	 * 地址，用户宠物回家等活动
	 */
	@ApiModelProperty("地址，用户宠物回家等活动")
	protected String address;

	/**
	 * 帖子内容
	 */
	@ApiModelProperty("帖子内容")
	protected String context;

	/**
	 * 收藏次数
	 */
	@ApiModelProperty("收藏次数")
	protected Integer favoriteCount;

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
	 * 发布时间
	 */
	@ApiModelProperty("发布时间")
	protected Date createAt;
}
