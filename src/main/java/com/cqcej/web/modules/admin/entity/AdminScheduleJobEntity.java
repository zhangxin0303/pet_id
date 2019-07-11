package com.cqcej.web.modules.admin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务
 * 
 * @author Jia Min
 * @date 2018-09-05 14:17:00
 */
@TableName("schedule_job")
@ApiModel("定时任务")
@Data
public class AdminScheduleJobEntity implements Serializable {

	/**
	 * 任务id
	 */
	@TableId
	@ApiModelProperty("任务id")
	protected Long jobId;

	/**
	 * spring bean名称
	 */
	@ApiModelProperty("spring bean名称")
	protected String beanName;
	
	/**
	 * spring bean名称
	 */
	@ApiModelProperty("任务名称")
	protected String jobName;
	
	/**
	 * 方法名
	 */
	@ApiModelProperty("方法名")
	protected String methodName;

	/**
	 * 参数
	 */
	@ApiModelProperty("参数")
	protected String params;

	/**
	 * cron表达式
	 */
	@ApiModelProperty("cron表达式")
	protected String cronExpression;

	/**
	 * 任务状态  0：正常  1：暂停
	 */
	@ApiModelProperty("任务状态  0：正常  1：暂停")
	protected Integer status;

	/**
	 * 备注
	 */
	@ApiModelProperty("备注")
	protected String remark;

	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	protected Date createTime;
}
