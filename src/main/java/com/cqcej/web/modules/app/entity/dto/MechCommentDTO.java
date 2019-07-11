package com.cqcej.web.modules.app.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * DESCRIPTION
 *
 * @author Jia Min
 * @email 510573309@qq.com
 * @date 2018-11-16 09:57
 */
@ApiModel("店铺评价详情")
@Data
public class MechCommentDTO implements Serializable {

	
	/**
	 * 用户头像
	 */
	@ApiModelProperty("用户头像")
	protected String avatar;
	
	/**
	 * 用户名称
	 */
	@ApiModelProperty("用户名称")
	protected String nickname;
	
	/**
	 * 评论星级
	 */
	@ApiModelProperty("评论星级")
	protected Integer commentLevel;
	
	/**
	 * 评论内容
	 */
	@ApiModelProperty("评论内容")
	protected String commentContent;
	
	/**
	 * 评论时间
	 */
	@ApiModelProperty("评论时间")
	protected String createAt;
	
}
