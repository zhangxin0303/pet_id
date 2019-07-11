package com.cqcej.web.modules.admin.entity.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@ApiModel("接送人员信息")
@Data
public class PickUpEntity implements Serializable {
    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    protected String realname;

    /**
     * 手机号码
     */
    @ApiModelProperty("手机号码")
    protected String mobile;

    /**
     * 起始时间
     */
    @ApiModelProperty("起始时间")
    protected Date beginAt;

    /**
     * 所属
     */
    @ApiModelProperty("所属（0店员，1官方，2其他）")
    protected String type;

//    /**
//     * 绑定账户
//     */
//    @ApiModelProperty("绑定账户")
//    protected String account;

    /**
     * 到达时间
     *
     */
    @ApiModelProperty("到达时间")
    protected Date endAt;

}
