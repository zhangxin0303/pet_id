package com.cqcej.web.modules.app.entity.home;

import com.cqcej.web.modules.common.entity.ArticleCommentEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 资讯评论
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-18 16:15:16
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel("资讯评论")
@Data
public class AppArticleCommentEntity extends ArticleCommentEntity {
	/**
	 * 用户昵称
	 */
	private String userNickname;
	
	/**
	 * 用户头像
	 */
	private String userAvatar;
}
