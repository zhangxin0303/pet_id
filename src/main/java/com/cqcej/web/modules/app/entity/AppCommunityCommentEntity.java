package com.cqcej.web.modules.app.entity;

import com.cqcej.web.modules.common.entity.CommunityCommentEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 社区评论
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-30 16:30:26
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel("社区评论")
@Data
public class AppCommunityCommentEntity extends CommunityCommentEntity {
	/**
	 * 用户昵称
	 */
	private String userNickname;
	
	/**
	 * 用户头像
	 */
	private String userAvatar;
}
