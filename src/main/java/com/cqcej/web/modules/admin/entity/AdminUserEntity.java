package com.cqcej.web.modules.admin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.cqcej.web.common.utils.AppConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户
 * 
 * @author Jia Min
 * @email empty
 * @date 2018-07-23 15:19:56
 */

@ApiModel("用户信息")
public class AdminUserEntity implements Serializable {

	/**
	 * 用户名
	 */
	@TableId
	@ApiModelProperty("用户名")
	protected Long userId;

	/**
	 * 昵称
	 */
	@ApiModelProperty("昵称")
	protected String nickname;

	/**
	 * 手机号（登录账号）
	 */
	@ApiModelProperty("手机号（登录账号）")
	@NotBlank(message = "手机号不能为空")
	@Pattern(regexp = AppConstants.REGEX_MOBILE, message = "请输入正确的手机号")
	protected String mobile;

	/**
	 * 邮箱（登录账号）
	 */
	@ApiModelProperty("邮箱（登录账号）")
	//@NotBlank(message = "邮箱不能为空")
	//@Pattern(regexp = AppConstants.REGEX_EMAIL, message = "请输入正确的邮箱")
	protected String email;

	/**
	 * 密码
	 */
	@ApiModelProperty("密码")
	protected String password;

	/**
	 * 用户类型，1普通用户，10医疗机构，11美容机构20医师21接送者22遛狗人员
	 */
	@ApiModelProperty("用户类型，1普通用户，10医疗机构，11美容机构20医师21接送者22遛狗人员")
	protected Integer userType;

	/**
	 * 用户积分
	 */
	@ApiModelProperty("用户积分")
	protected Integer score;

	/**
	 * 头像地址
	 */
	@ApiModelProperty("头像地址")
	protected String avatar;

	/**
	 * 性别1男2女
	 */
	@ApiModelProperty("性别1男2女")
	protected Integer sex;

	/**
	 * 省ID
	 */
	@ApiModelProperty("省ID")
	protected Integer provinceId;

	/**
	 * 市ID
	 */
	@ApiModelProperty("市ID")
	protected Integer cityId;

	/**
	 * 区ID
	 */
	@ApiModelProperty("区ID")
	protected Integer areaId;

	/**
	 * 签名
	 */
	@ApiModelProperty("签名")
	protected String signature;

	/**
	 * 状态(0正常，1禁言)
	 */
	@ApiModelProperty("状态(0正常，1禁言)")
	protected Integer forbiddenComment;

	/**
	 * 是否在线
	 */
	@ApiModelProperty("是否在线")
	protected Integer status;

	/**
	 * 用户地址
	 */
	@ApiModelProperty("用户地址")
	protected String address;

	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	protected Date createAt;
	
	
	/**
	 * 姓名
	 */
	@ApiModelProperty("姓名")
	protected String realname;
	/**
	 * 人员ID
	 */
	@ApiModelProperty("人员ID(接送人员-编辑人员页面)")
	protected Long workerId;
	
	/**
	 * 类型(接送人员-编辑人员页面)
	 */
	@ApiModelProperty("类型(接送人员-编辑人员页面)")
	protected String pickUpType;
	
	/**
	 * 遛狗费
	 */
	@ApiModelProperty("遛狗费(接送人员-编辑人员页面)")
	protected BigDecimal walkDogFee;
	
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
	
	
	public Integer getWorkerType() {
		return workerType;
	}
	
	public void setWorkerType(Integer workerType) {
		this.workerType = workerType;
	}
	
	public Integer getType() {
		return type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	/**
	 * 设置：用户名
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 获取：用户名
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 设置：昵称
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 获取：昵称
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * 设置：手机号（登录账号）
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 获取：手机号（登录账号）
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * 设置：邮箱（登录账号）
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获取：邮箱（登录账号）
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 设置：密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取：密码
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置：用户类型，1普通用户，10医疗机构，11美容机构20医师21接送者22遛狗人员
	 */
	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	/**
	 * 获取：用户类型，1普通用户，10医疗机构，11美容机构20医师21接送者22遛狗人员
	 */
	public Integer getUserType() {
		return userType;
	}

	/**
	 * 设置：用户积分
	 */
	public void setScore(Integer score) {
		this.score = score;
	}

	/**
	 * 获取：用户积分
	 */
	public Integer getScore() {
		return score;
	}

	/**
	 * 设置：头像地址
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * 获取：头像地址
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * 设置：性别1男2女
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/**
	 * 获取：性别1男2女
	 */
	public Integer getSex() {
		return sex;
	}

	/**
	 * 设置：省ID
	 */
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	/**
	 * 获取：省ID
	 */
	public Integer getProvinceId() {
		return provinceId;
	}

	/**
	 * 设置：市ID
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	/**
	 * 获取：市ID
	 */
	public Integer getCityId() {
		return cityId;
	}

	/**
	 * 设置：区ID
	 */
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	/**
	 * 获取：区ID
	 */
	public Integer getAreaId() {
		return areaId;
	}

	/**
	 * 设置：签名
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**
	 * 获取：签名
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * 设置：状态(0正常，1禁言)
	 */
	public void setForbiddenComment(Integer forbiddenComment) {
		this.forbiddenComment = forbiddenComment;
	}

	/**
	 * 获取：状态(0正常，1禁言)
	 */
	public Integer getForbiddenComment() {
		return forbiddenComment;
	}

	/**
	 * 设置：是否在线
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取：是否在线
	 */
	public Integer getStatus() {
		return status;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPickUpType() {
		return pickUpType;
	}
	
	public void setPickUpType(String pickUpType) {
		this.pickUpType = pickUpType;
	}
	
	public BigDecimal getWalkDogFee() {
		return walkDogFee;
	}
	
	public void setWalkDogFee(BigDecimal walkDogFee) {
		this.walkDogFee = walkDogFee;
	}
	
	public Long getWorkerId() {
		return workerId;
	}
	
	public void setWorkerId(Long workerId) {
		this.workerId = workerId;
	}
	
	public String getRealname() {
		return realname;
	}
	
	public void setRealname(String realname) {
		this.realname = realname;
	}
}
