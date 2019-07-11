package com.cqcej.web.modules.admin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 机构评价
 *
 * @author Jia Min
 * @email empty
 * @date 2018-07-16 11:08:31
 */
@ApiModel("商户评价")
public class AdminMechanismCommentResultEntity  implements Serializable {

    /**
     * 诊所ID
     */
    @TableId
    @ApiModelProperty("诊所ID")
    protected Long mechanismId;

    /**
     * 店铺名称
     */
    @ApiModelProperty("店铺名称")
    protected String mechanismName;

    /**
     * 综合评分
     */
    @ApiModelProperty("综合评分")
    protected Double syntheticalMark;

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

    public Long getMechanismId() {
        return mechanismId;
    }

    public void setMechanismId(Long mechanismId) {
        this.mechanismId = mechanismId;
    }

    public String getMechanismName() {
        return mechanismName;
    }

    public void setMechanismName(String mechanismName) {
        this.mechanismName = mechanismName;
    }

    public Double getSyntheticalMark() {
        return syntheticalMark;
    }

    public void setSyntheticalMark(Double syntheticalMark) {
        this.syntheticalMark = syntheticalMark;
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
