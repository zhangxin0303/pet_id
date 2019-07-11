package com.cqcej.web.modules.admin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 工作者，包含医师，接送者，遛狗人员
 * 
 * @author Jia Min
 * @email empty
 * @date 2018-07-27 11:23:32
 */
@TableName("ct_worker")
@ApiModel("工作者，包含医师，接送者，遛狗人员")
public class AdminWorkerEntity implements Serializable {

	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty("ID")
	protected Long workerId;

	/**
	 * 医师对应的用户ID
	 */
	@ApiModelProperty("医师对应的用户ID")
	protected Long userId;

	/**
	 * 诊所ID，如果0，表示单独的医师
	 */
	@ApiModelProperty("诊所ID，如果0，表示单独的医师")
	protected Long mechanismId;

	/**
	 * 宠物分类
	 */
	@ApiModelProperty("宠物分类")
	protected Integer petClassId;
	
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
	 * 医师介绍
	 */
	@ApiModelProperty("医师介绍")
	protected String workerDescription;

	/**
	 * 擅长
	 */
	@ApiModelProperty("擅长")
	protected String goodAt;

	/**
	 * 聊天咨询费
	 */
	@ApiModelProperty("聊天咨询费")
	protected BigDecimal chatFee;

	/**
	 * 电话咨询费
	 */
	@ApiModelProperty("电话咨询费")
	protected BigDecimal telephoneFee;

	/**
	 * 上门费
	 */
	@ApiModelProperty("上门费")
	protected BigDecimal ondoorFee;

	/**
	 * 接送费
	 */
	@ApiModelProperty("接送费")
	protected BigDecimal transportFee;

	/**
	 * 遛狗费
	 */
	@ApiModelProperty("遛狗费")
	protected BigDecimal walkDogFee;

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
	 * 24小时内回复次数
	 */
	@ApiModelProperty("24小时内回复次数")
	protected Integer replyIn24HoursCount;

	/**
	 * 是否推荐
	 */
	@ApiModelProperty("是否推荐")
	protected Integer isRecommend;

	/**
	 * 推荐时间
	 */
	@ApiModelProperty("推荐时间")
	protected Date recommendAt;

	/**
	 * 排序，越大越前
	 */
	@ApiModelProperty("排序，越大越前")
	protected Long sort;

	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	protected Date createAt;


	/**
	 * 设置：ID
	 */
	public void setWorkerId(Long workerId) {
		this.workerId = workerId;
	}

	/**
	 * 获取：ID
	 */
	public Long getWorkerId() {
		return workerId;
	}

	/**
	 * 设置：医师对应的用户ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 获取：医师对应的用户ID
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 设置：诊所ID，如果0，表示单独的医师
	 */
	public void setMechanismId(Long mechanismId) {
		this.mechanismId = mechanismId;
	}

	/**
	 * 获取：诊所ID，如果0，表示单独的医师
	 */
	public Long getMechanismId() {
		return mechanismId;
	}

	/**
	 * 设置：宠物分类
	 */
	public void setPetClassId(Integer petClassId) {
		this.petClassId = petClassId;
	}

	/**
	 * 获取：宠物分类
	 */
	public Integer getPetClassId() {
		return petClassId;
	}

	/**
	 * 设置：工作者类型，20医师21接送者22遛狗人员23美容师
	 */
	public void setWorkerType(Integer workerType) {
		this.workerType = workerType;
	}

	/**
	 * 获取：工作者类型，20医师21接送者22遛狗人员23美容师
	 */
	public Integer getWorkerType() {
		return workerType;
	}

	/**
	 * 设置：医师介绍
	 */
	public void setWorkerDescription(String workerDescription) {
		this.workerDescription = workerDescription;
	}

	/**
	 * 获取：医师介绍
	 */
	public String getWorkerDescription() {
		return workerDescription;
	}

	/**
	 * 设置：擅长
	 */
	public void setGoodAt(String goodAt) {
		this.goodAt = goodAt;
	}

	/**
	 * 获取：擅长
	 */
	public String getGoodAt() {
		return goodAt;
	}

	/**
	 * 设置：聊天咨询费
	 */
	public void setChatFee(BigDecimal chatFee) {
		this.chatFee = chatFee;
	}

	/**
	 * 获取：聊天咨询费
	 */
	public BigDecimal getChatFee() {
		return chatFee;
	}

	/**
	 * 设置：电话咨询费
	 */
	public void setTelephoneFee(BigDecimal telephoneFee) {
		this.telephoneFee = telephoneFee;
	}

	/**
	 * 获取：电话咨询费
	 */
	public BigDecimal getTelephoneFee() {
		return telephoneFee;
	}

	/**
	 * 设置：上门费
	 */
	public void setOndoorFee(BigDecimal ondoorFee) {
		this.ondoorFee = ondoorFee;
	}

	/**
	 * 获取：上门费
	 */
	public BigDecimal getOndoorFee() {
		return ondoorFee;
	}

	/**
	 * 设置：接送费
	 */
	public void setTransportFee(BigDecimal transportFee) {
		this.transportFee = transportFee;
	}

	/**
	 * 获取：接送费
	 */
	public BigDecimal getTransportFee() {
		return transportFee;
	}

	/**
	 * 设置：遛狗费
	 */
	public void setWalkDogFee(BigDecimal walkDogFee) {
		this.walkDogFee = walkDogFee;
	}

	/**
	 * 获取：遛狗费
	 */
	public BigDecimal getWalkDogFee() {
		return walkDogFee;
	}

	/**
	 * 设置：咨询次数
	 */
	public void setConsultCount(Integer consultCount) {
		this.consultCount = consultCount;
	}

	/**
	 * 获取：咨询次数
	 */
	public Integer getConsultCount() {
		return consultCount;
	}

	/**
	 * 设置：接送次数
	 */
	public void setTransportCount(Integer transportCount) {
		this.transportCount = transportCount;
	}

	/**
	 * 获取：接送次数
	 */
	public Integer getTransportCount() {
		return transportCount;
	}

	/**
	 * 设置：遛狗次数
	 */
	public void setWalkDogCount(Integer walkDogCount) {
		this.walkDogCount = walkDogCount;
	}

	/**
	 * 获取：遛狗次数
	 */
	public Integer getWalkDogCount() {
		return walkDogCount;
	}

	/**
	 * 设置：评论次数
	 */
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	/**
	 * 获取：评论次数
	 */
	public Integer getCommentCount() {
		return commentCount;
	}

	/**
	 * 设置：好评次数
	 */
	public void setGoodCommentCount(Integer goodCommentCount) {
		this.goodCommentCount = goodCommentCount;
	}

	/**
	 * 获取：好评次数
	 */
	public Integer getGoodCommentCount() {
		return goodCommentCount;
	}

	/**
	 * 设置：24小时内回复次数
	 */
	public void setReplyIn24HoursCount(Integer replyIn24HoursCount) {
		this.replyIn24HoursCount = replyIn24HoursCount;
	}

	/**
	 * 获取：24小时内回复次数
	 */
	public Integer getReplyIn24HoursCount() {
		return replyIn24HoursCount;
	}

	/**
	 * 设置：是否推荐
	 */
	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}

	/**
	 * 获取：是否推荐
	 */
	public Integer getIsRecommend() {
		return isRecommend;
	}

	/**
	 * 设置：推荐时间
	 */
	public void setRecommendAt(Date recommendAt) {
		this.recommendAt = recommendAt;
	}

	/**
	 * 获取：推荐时间
	 */
	public Date getRecommendAt() {
		return recommendAt;
	}

	/**
	 * 设置：排序，越大越前
	 */
	public void setSort(Long sort) {
		this.sort = sort;
	}

	/**
	 * 获取：排序，越大越前
	 */
	public Long getSort() {
		return sort;
	}

	/**
	 * 设置：创建时间
	 */
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	/**
	 * 获取：创建时间
	 */
	public Date getCreateAt() {
		return createAt;
	}
}
