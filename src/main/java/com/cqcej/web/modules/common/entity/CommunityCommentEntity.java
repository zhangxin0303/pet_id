package com.cqcej.web.modules.common.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 社区评论
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-30 16:30:26
 */
@TableName("ct_community_comment")
@Data
public class CommunityCommentEntity implements Serializable {
	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty("ID")
	protected Long commentId;

	/**
	 * 评论者
	 */
	@ApiModelProperty("评论者")
	protected Long userId;

	/**
	 * 帖子ID
	 */
	@ApiModelProperty("帖子ID")
	protected Long communityId;
	
	/**
	 * 评论内容
	 */
	@ApiModelProperty("评论内容")
	protected String context;

	/**
	 * 点赞数
	 */
	@ApiModelProperty("点赞数")
	protected Integer priseCount;

	/**
	 * 发布评论时间
	 */
	@ApiModelProperty("发布评论时间")
	protected Date createAt;
}
