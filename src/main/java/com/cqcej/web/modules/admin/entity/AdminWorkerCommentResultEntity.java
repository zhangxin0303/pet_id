package com.cqcej.web.modules.admin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("全部评价")
public class AdminWorkerCommentResultEntity {




//----------------属性
    @ApiModelProperty("人员昵称")
    protected String nickname;

    /**
     * 工作人员ID
     */
    @ApiModelProperty("工作人员ID")
    protected Long workerId;

    /**
     *
     * 好评率
     */
    @ApiModelProperty("好评率")
    protected Double levelRatio;

    /**
     * 好评
     */
    @ApiModelProperty("好评")
    protected String goodComment;

    /**
     * 中评
     */
    @ApiModelProperty("中评")
    protected String midComment;

    /**
     * 差评
     */
    @ApiModelProperty("差评")
    protected String badComment;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public Double getLevelRatio() {
        return levelRatio;
    }

    public void setLevelRatio(Double levelRatio) {
        this.levelRatio = levelRatio;
    }

    public String getGoodComment() {
        return goodComment;
    }

    public void setGoodComment(String goodComment) {
        this.goodComment = goodComment;
    }

    public String getMidComment() {
        return midComment;
    }

    public void setMidComment(String midComment) {
        this.midComment = midComment;
    }

    public String getBadComment() {
        return badComment;
    }

    public void setBadComment(String badComment) {
        this.badComment = badComment;
    }
}
