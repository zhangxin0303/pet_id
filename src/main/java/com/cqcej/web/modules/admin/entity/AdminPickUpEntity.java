package com.cqcej.web.modules.admin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;



@ApiModel("工作者(接送人员)")
@Data
public class AdminPickUpEntity  implements Serializable {

    /**
     * ID
     */
    @TableId
    @ApiModelProperty("ID")
    protected Long workerId;

    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    protected String realname;

    /**
     * 性别
     */
    @ApiModelProperty("性别")
    protected Integer sex;
    
    /**
     * 0店员，1官方，2其他
     */
    @ApiModelProperty("0店员，1官方，2其他")
    protected Integer type;
    
    /**
     * 工作者类型，20医师21接送者22遛狗人员23美容师
     */
    @ApiModelProperty("工作者类型，20医师21接送者22遛狗人员23美容师")
    protected Integer workerType;
    
    
    /**
     * 类型
     */
    @ApiModelProperty("诊所ID，如果0，表示单独的医师,-1表示官方接送人员")
    protected String mechanismName;

    /**
     * 医师介绍
     */
    @ApiModelProperty("联系方式")
    protected String mobile;

    /**
     * 工作人员状态(接送人员1.接送中2.等待中3.遛狗中)
     */
    @ApiModelProperty("工作人员状态(接送人员1.接送中2.等待中3.遛狗中)")
    protected Integer workerStatus;

    /**
     * 经度
     */
    @ApiModelProperty("经度")
    protected String longitude;

    /**
     * 纬度
     */
    @ApiModelProperty("纬度")
    protected String latitude;

    /**
     * 是否在线(0离线，1在线)人数
     */
    @ApiModelProperty("是否在线(0离线，1在线)人数")
    protected Integer statusCount;
}
