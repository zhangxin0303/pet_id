package com.cqcej.web.modules.common.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 机构评价
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-20 11:10:15
 */
@TableName("ct_mechanism_comment")
@Data
public class MechanismCommentEntity implements Serializable {
	
	
	/**
	 *
	 */
	@TableId
	@ApiModelProperty("ID")
	protected Long commonId;
	
	/**
	 * 用户ID
	 */
	@ApiModelProperty("用户ID")
	protected Long userId;
	
	/**
	 * 诊所ID
	 */
	@ApiModelProperty("诊所ID")
	protected Long mechanismId;
	
	/**
	 * 评论的服务ID
	 */
	@ApiModelProperty("评论的服务ID")
	protected Long serviceId;
	
	/**
	 * 评星等级，1-5
	 */
	@ApiModelProperty("评星等级，1-5")
	protected Integer commentLevel;
	
	/**
	 * 评价内容
	 */
	@ApiModelProperty("评价内容")
	protected String commentContent;
	
	/**
	 * 点赞次数
	 */
	@ApiModelProperty("点赞次数")
	protected Integer priseCount;
	
	/**
	 * 评价时间
	 */
	@ApiModelProperty("评价时间")
	protected Date createAt;
	
}
