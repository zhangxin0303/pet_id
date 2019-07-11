package com.cqcej.web.modules.admin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("服务评价")
public class AdminServiceOrderCommentEntity {


    @ApiModelProperty("订单号")
    protected String orderId;

    /**
     * 评价结果
     */
    @ApiModelProperty("评价结果")
    protected Integer commentLevel;

    /**
     *
     * 评价内容
     */
    @ApiModelProperty("评价内容")
    protected String commentContent;

    /**
     * 用户昵称
     */
    @ApiModelProperty("用户昵称")
    protected String nickname;

    /**
     * 评价时间
     */
    @ApiModelProperty("评价时间")
    protected String createAt;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getCommentLevel() {
        return commentLevel;
    }

    public void setCommentLevel(Integer commentLevel) {
        this.commentLevel = commentLevel;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }
}
