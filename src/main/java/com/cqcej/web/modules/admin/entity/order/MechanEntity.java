package com.cqcej.web.modules.admin.entity.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("商家信息")
public class MechanEntity implements Serializable {

    /**
     * 商家名称
     */
    @ApiModelProperty("商家名称")
    protected String mechanismName;

    /**
     * 手机号码
     */
    @ApiModelProperty("手机号码")
    protected String telephone;

    /**
     * 地址
     */
    @ApiModelProperty("地址")
    protected String address;

    /**
     * 负责人
     */
    @ApiModelProperty("负责人")
    protected String realname;

    public String getMechanismName() {
        return mechanismName;
    }

    public void setMechanismName(String mechanismName) {
        this.mechanismName = mechanismName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}
