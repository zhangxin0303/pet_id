package com.cqcej.web.modules.admin.entity.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("买家信息")
public class BuyerEntity implements Serializable {

    /**
     * 买家名称
     */
    @ApiModelProperty("买家名称")
    protected String nickname;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    protected String mobile;

    /**
     * 地址
     */
    @ApiModelProperty("地址")
    protected String address;

    /**
     * 所选服务
     */
    @ApiModelProperty("所选服务")//订单类型
    protected Integer type;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
