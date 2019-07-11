package com.cqcej.web.modules.admin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 社区
 *
 * @author Jia Min
 * @email empty
 * @date 2018-07-19 13:36:15
 */
@TableName("ct_community")
@ApiModel("社区信息")
@Data
public class AdminCommunityEntity implements Serializable {

    /**
     * ID
     */
    @TableId
    @ApiModelProperty("ID")
    protected Long communityId;

    /**
     * 发布帖子的用户ID
     */
    @ApiModelProperty("发布帖子的用户ID")
    protected Long userId;

    /**
     * 社区分类ID，10000(宠物回家), 10001(宠物领养), 10002(宠物交易)
     */
    @ApiModelProperty("社区分类ID，10000(宠物回家), 10001(宠物领养), 10002(宠物交易)")
    protected Integer classId;
	
	/**
	 * 宠物分类ID
	 */
	@ApiModelProperty("宠物分类ID")
	protected Integer petClassId;
	
    /**
     * 帖子标题
     */
    @ApiModelProperty("帖子标题")
    protected String title;

    /**
     * 地址，用于宠物回家等活动
     */
    @ApiModelProperty("地址，用于宠物回家等活动")
    protected String address;

    /**
     * 帖子内容
     */
    @ApiModelProperty("帖子内容")
    protected String context;

    /**
     * 收藏次数
     */
    @ApiModelProperty("收藏次数")
    protected Integer favoriteCount;

    /**
     * 浏览次数
     */
    @ApiModelProperty("浏览次数")
    protected Integer viewCount;

    /**
     * 评论次数
     */
    @ApiModelProperty("评论次数")
    protected Integer commentCount;

    /**
     * 点赞次数
     */
    @ApiModelProperty("点赞次数")
    protected Integer priseCount;

    /**
     * 是否置顶(1置顶，0正常)
     */
    @ApiModelProperty("是否置顶(1置顶，0正常)")
    protected Integer top;

    /**
     * 发布时间
     */
    @ApiModelProperty("发布时间")
    protected Date createAt;

    /**
     * 回复数
     */
    @ApiModelProperty("回复数")
    protected Integer commCount;

    /**
     * 所属板块
     */
    @ApiModelProperty("所属板块")
    protected String className;
    /**
     * 最新回复
     */
    @ApiModelProperty("最新回复")
    protected Date latestTime;

    /**
     * 发帖人
     */
    @ApiModelProperty("发帖人")
    protected String nickname;
}
