package com.cqcej.web.modules.admin.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 机构评价详情(查看)
 *
 * @author Jia Min
 * @email empty
 * @date 2018-07-16 11:08:31
 */
@ApiModel("机构评价详情")
public class AdminShopOrderCommentEntity {

    /**
     * 订单号
     */
    @ApiModelProperty("订单号")
    protected Long orderNo;

    /**
     * 评星等级，1-5
     */
    @ApiModelProperty("评星等级，1-5")
    protected Integer commentLevel;

    /**
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
    protected Date createAt;

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
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

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
