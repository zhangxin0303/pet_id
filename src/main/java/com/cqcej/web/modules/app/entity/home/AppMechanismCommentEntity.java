package com.cqcej.web.modules.app.entity.home;

import com.cqcej.web.modules.common.entity.MechanismCommentEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 机构评价
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-20 11:10:15
 */
@ApiModel("机构评价")
@Data
public class AppMechanismCommentEntity extends MechanismCommentEntity {
	
	/**
	 * 评论用户头像地址
	 */
	@ApiModelProperty("评论用户头像地址")
	private String userAvatar;
	
	/**
	 * 用户昵称
	 */
	@ApiModelProperty("用户昵称")
	private String nickname;
	
	/**
	 * 服务名
	 */
	@ApiModelProperty("服务名")
	private String serviceName;
	
	/**
	 * 是否点赞
	 */
	@ApiModelProperty("是否点赞")
	private boolean prised;
	
	
	/**
	 * 商家回复内容
	 */
	@ApiModelProperty("商家回复内容")
	private String replyContent;
	
	
}
