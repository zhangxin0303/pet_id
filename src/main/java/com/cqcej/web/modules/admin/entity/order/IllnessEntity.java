package com.cqcej.web.modules.admin.entity.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


@ApiModel("病情信息")
public class IllnessEntity implements Serializable {

    /**
     * 病情描述
     */
    @ApiModelProperty("病情描述")
    protected String notes;

    /**
     * 照片列表
     */
    @ApiModelProperty("病情图片")
    protected String image;

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
