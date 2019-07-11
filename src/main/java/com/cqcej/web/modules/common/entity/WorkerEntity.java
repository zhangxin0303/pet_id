package com.cqcej.web.modules.common.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 工作者，包含医生，接送者，遛狗人员
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-19 15:05:48
 */
@Data
@TableName("ct_worker")
public class WorkerEntity implements Serializable {
	
	/**
	 * 医生
	 */
	public static final int WORKER_TYPE_DOCTOR = 20;
	/**
	 * 接送
	 */
	public static final int WORKER_TYPE_TRANSPORT = 21;
	/**
	 * 遛狗
	 */
	public static final int WORKER_TYPE_WORK_DOG = 22;
	
	/**
	 * 美容师
	 */
	public static final int WORKER_TYPE_BEAUTICIAN = 23;

	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty("工作者ID")
	protected Long workerId;

	/**
	 * 对应的用户ID
	 */
	@ApiModelProperty("对应的用户ID")
	protected Long userId;

	/**
	 * 诊所ID，如果0，表示单独的医生
	 */
	@ApiModelProperty("诊所ID，如果0，表示单独的医生")
	protected Long mechanismId;
	
	/**
	 * 宠物分类ID
	 */
	@ApiModelProperty("宠物分类ID")
	protected Integer petClassId;

	/**
	 * 工作者类型，20医生21接送者2遛狗人员
	 */
	@ApiModelProperty("工作者类型，20医生21接送者22遛狗人员")
	protected Integer workerType;

	/**
	 * 医生介绍
	 */
	@ApiModelProperty("医生介绍")
	protected String workerDescription;
	
	/**
	 * 医生擅长的
	 */
	@ApiModelProperty("医生擅长")
	protected String goodAt;
	
	/**
	 * 咨询费，医生
	 */
	@ApiModelProperty("聊天咨询费，医生")
	protected Double chatFee;
	
	/**
	 * 咨询费，医生
	 */
	@ApiModelProperty("电话咨询费，医生")
	protected Double telephoneFee;
	
	/**
	 * 上门费，医生
	 */
	@ApiModelProperty("上门费，医生")
	protected Double ondoorFee;
	
	/**
	 * 接送费，接送人员
	 */
	@ApiModelProperty("接送费，接送人员")
	protected Double transportFee;
	
	/**
	 * 遛狗费
	 */
	@ApiModelProperty("遛狗费")
	protected Double walkDogFee;
	
	/**
	 * 订单次数
	 */
	@ApiModelProperty("订单次数")
	protected Integer orderCount;

	/**
	 * 咨询次数
	 */
	@ApiModelProperty("咨询次数")
	protected Integer consultCount;
	
	/**
	 * 接送次数
	 */
	@ApiModelProperty("接送次数")
	protected Integer transportCount;
	
	/**
	 * 遛狗次数
	 */
	@ApiModelProperty("遛狗次数")
	protected Integer walkDogCount;

	/**
	 * 评论次数
	 */
	@ApiModelProperty("评论次数")
	protected Integer commentCount;

	/**
	 * 好评次数
	 */
	@ApiModelProperty("好评次数")
	protected Integer goodCommentCount;
	
	/**
	 * 评星
	 */
	@ApiModelProperty("评星")
	protected Integer stars;

	/**
	 * 24小时内回复次数
	 */
	@ApiModelProperty("24小时内回复次数")
	@TableField("reply_in_24_hours_count")
	protected Integer replyIn24HoursCount;
	
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
	 * 是否推荐
	 */
	@ApiModelProperty("是否推荐")
	protected Integer isRecommend;

	/**
	 * 推荐时间
	 */
	@ApiModelProperty(hidden = true)
	protected Date recommendAt;
	
	/**
	 * 排序，越大越靠前
	 */
	@ApiModelProperty(hidden = true)
	protected Integer sort;
}
