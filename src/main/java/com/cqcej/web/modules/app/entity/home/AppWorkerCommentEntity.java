package com.cqcej.web.modules.app.entity.home;

import com.baomidou.mybatisplus.annotations.TableName;
import com.cqcej.web.modules.common.entity.WorkerCommentEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 评论工作人员
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-19 15:05:48
 */
@ApiModel("评论工作人员")
@TableName("ct_worker_comment")
public class AppWorkerCommentEntity extends WorkerCommentEntity {
	
	/**
	 * 工作者用户头像
	 */
	@ApiModelProperty("工作者用户头像")
	protected String userAvatar;
	
	/**
	 * 工作者用户名称
	 */
	@ApiModelProperty("工作者用户名称")
	protected String userNickname;
	
	public String getUserAvatar() {
		return userAvatar;
	}
	
	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}
	
	public String getUserNickname() {
		return userNickname;
	}
	
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
}
