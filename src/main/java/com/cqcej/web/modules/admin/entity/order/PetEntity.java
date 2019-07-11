package com.cqcej.web.modules.admin.entity.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


@ApiModel("宠物信息")
public class PetEntity implements Serializable {

    /**
     * 宠物名称
     */
    @ApiModelProperty("宠物名称")
    protected String petName;

    /**
     * 品种
     */
    @ApiModelProperty("品种")
    protected String className;

    /**
     * 性别(1公2母)
     */
    @ApiModelProperty("性别(1公2母)")
    protected Integer petSex;
    /**
     * 体重
     */
    @ApiModelProperty("体重")
    protected Double petWeight;

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getPetSex() {
        return petSex;
    }

    public void setPetSex(Integer petSex) {
        this.petSex = petSex;
    }

    public Double getPetWeight() {
        return petWeight;
    }

    public void setPetWeight(Double petWeight) {
        this.petWeight = petWeight;
    }
}
