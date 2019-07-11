package com.cqcej.web.modules.app.entity.dto;

import com.cqcej.web.modules.app.utils.AppPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * DESCRIPTION
 *
 * @author Jia Min
 * @email 510573309@qq.com
 * @date 2018-11-16 10:11
 */
@ApiModel("店铺评价")
@Data
public class MechCommentStarDTO implements Serializable {

	/**
	 * 店铺星级
	 */
	@ApiModelProperty("店铺评论星级")
	protected Integer mechStar;
	
	/**
	 * 店铺星级
	 */
	@ApiModelProperty("评价详情")
	protected AppPage<MechCommentDTO> page;
}
