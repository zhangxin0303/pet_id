package com.cqcej.web.modules.app.entity.home;

import com.cqcej.web.modules.common.entity.WorkerEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 工作者，包含医生，接送者，遛狗人员
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-01 17:39
 */
@Data
@ApiModel("工作者，包含医生，接送者，遛狗人员")
public class AppWorkerEntity extends WorkerEntity {
	/**
	 * 工作者所属机构logo，可为null
	 */
	@ApiModelProperty("工作者所属机构logo，可为null")
	protected String mechanismLogo;
	
	/**
	 * 工作者所属机构，可为null
	 */
	@ApiModelProperty("工作者所属机构名，可为null")
	protected String mechanismName;
	
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
	
	/**
	 * 手机号码
	 */
	@ApiModelProperty("手机号码")
	protected String userMobile;
	

	/**
	 * 用户评论
	 */
	@ApiModelProperty("用户评论")
	protected List<AppWorkerCommentEntity> comments;
	
	/**
	 * 工作者用户名称
	 */
	@ApiModelProperty("员工性别(性别1男2女)")
	protected Integer sex;
	
	
}
