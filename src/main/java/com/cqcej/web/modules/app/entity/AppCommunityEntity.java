package com.cqcej.web.modules.app.entity;

import com.cqcej.web.modules.common.entity.CommunityEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 社区扩展
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-30 17:05
 */
@ApiModel("社区")
@Data
@EqualsAndHashCode(callSuper = true)
public class AppCommunityEntity extends CommunityEntity {
	
	private String userNickname;
	
	private String userAvatar;
	
	private String thumbImageUrl;
	
	private Integer isPrise = 0;
	
	private Integer isWatch = 0;
	
//	private boolean collected;
}
