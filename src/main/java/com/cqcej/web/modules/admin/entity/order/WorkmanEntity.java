package com.cqcej.web.modules.admin.entity.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@ApiModel("工作人员信息(医师,遛狗人员)")
@Data
public class WorkmanEntity implements Serializable {


    /**
     * 医师姓名
     */
    @ApiModelProperty("医师姓名")
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
     * 所属诊所
     */
    @ApiModelProperty("所属诊所")
    protected String mechanismName;


    /**
     * 服务内容(11上门就诊，12线上预约，13医师咨询，31遛狗服务，32宠物寄养)
     */
    @ApiModelProperty("服务内容(11上门就诊，12线上预约，13医师咨询，31遛狗服务，32宠物寄养)")
    protected Integer orderSubtype;

    /**
     * 到达时间
     */
    @ApiModelProperty("到达时间")
    protected Date endAt;

    /**
     * 订单类型(1诊所，2美容，3健康)
     */
    @ApiModelProperty("订单类型(1诊所，2美容，3健康)")
    protected Integer orderType;

//    /**
//     * 绑定账户
//     */
//    @ApiModelProperty("绑定账户")
//    protected String account;

}















