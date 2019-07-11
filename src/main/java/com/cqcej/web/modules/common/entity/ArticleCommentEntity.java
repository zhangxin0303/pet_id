package com.cqcej.web.modules.common.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 资讯评论
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-27 17:13:37
 */
@TableName("ct_article_comment")
@Data
public class ArticleCommentEntity implements Serializable {
	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty("ID")
	protected  Long commentId;

	/**
	 * 评论的资讯ID
	 */
	@ApiModelProperty("评论的资讯ID")
	protected  Long articleId;

	/**
	 * 评论的用户
	 */
	@ApiModelProperty("评论的用户")
	protected  Long userId;

	/**
	 * 评论内容
	 */
	@ApiModelProperty("评论内容")
	protected  String commentContent;

	/**
	 * 评论时间
	 */
	@ApiModelProperty("评论时间")
	protected  Date createAt;
}
