package com.cqcej.web.modules.admin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 机构，包含诊所，美容院
 * 
 * @author Jia Min
 * @email empty
 * @date 2018-07-23 10:36:31
 */
@TableName("ct_mechan")
@ApiModel("机构,包含诊所,美容院")
public class AdminMechanismEntity implements Serializable {

	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty("ID")
	protected Long mechanismId;

	/**
	 * 用户ID
	 */
	@ApiModelProperty("用户ID")
	protected Long userId;

	/**
	 * 宠物分类
	 */
	@ApiModelProperty("宠物分类")
	protected Integer petClassId;

	/**
	 * 机构类型（1诊所2美容院4健康）
	 */
	@ApiModelProperty("机构类型（1诊所2美容院4健康）")
	protected Integer mechanismType;

	/**
	 * 诊所名
	 */
	@ApiModelProperty("诊所名")
	protected String mechanismName;

	/**
	 * 关键词
	 */
	@ApiModelProperty("关键词")
	protected String keywords;

	/**
	 * 电话号码
	 */
	@ApiModelProperty("电话号码")
	protected String telephone;

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
	 * 详细地址
	 */
	@ApiModelProperty("详细地址")
	protected String address;

	/**
	 * 描述
	 */
	@ApiModelProperty("描述")
	protected String description;

	/**
	 * 营业时间
	 */
	@ApiModelProperty("营业时间")
	protected String businessTime;

	/**
	 * 平均评星级数，1-5
	 */
	@ApiModelProperty("平均评星级数，1-5")
	protected Integer stars;

	/**
	 * 总的评星数
	 */
	@ApiModelProperty("总的评星数")
	protected Integer totalStars;

	/**
	 * 评价次数
	 */
	@ApiModelProperty("评价次数")
	protected Integer commentCount;

	/**
	 * 经度
	 */
	@ApiModelProperty("经度")
	protected BigDecimal longitude;

	/**
	 * 纬度
	 */
	@ApiModelProperty("纬度")
	protected BigDecimal latitude;

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
	 * 售出多少
	 */
	@ApiModelProperty("售出多少")
	protected Integer sold;

	/**
	 * 收藏数量
	 */
	@ApiModelProperty("收藏数量")
	protected Integer favoriteCount;

	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	protected Date createAt;

	/**
	 * 商家状态(1.正常，2.待认证，3.已锁定)
	 */
	@ApiModelProperty("商家状态(1.正常，2.待认证，3.已锁定)")
	protected Integer mechanismStatus;

	/**
	 * 商家名称
	 */
	@ApiModelProperty("商家名称")
	protected String nickname;
	/**
	 * 设置：ID
	 */
	public void setMechanismId(Long mechanismId) {
		this.mechanismId = mechanismId;
	}

	/**
	 * 获取：ID
	 */
	public Long getMechanismId() {
		return mechanismId;
	}

	/**
	 * 设置：用户ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 获取：用户ID
	 */
	public Long getUserId() {
		return userId;
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
	 * 设置：机构类型（1诊所2美容院4健康）
	 */
	public void setMechanismType(Integer mechanismType) {
		this.mechanismType = mechanismType;
	}

	/**
	 * 获取：机构类型（1诊所2美容院4健康）
	 */
	public Integer getMechanismType() {
		return mechanismType;
	}

	/**
	 * 设置：诊所名
	 */
	public void setMechanismName(String mechanismName) {
		this.mechanismName = mechanismName;
	}

	/**
	 * 获取：诊所名
	 */
	public String getMechanismName() {
		return mechanismName;
	}

	/**
	 * 设置：关键词
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/**
	 * 获取：关键词
	 */
	public String getKeywords() {
		return keywords;
	}

	/**
	 * 设置：电话号码
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * 获取：电话号码
	 */
	public String getTelephone() {
		return telephone;
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
	 * 设置：详细地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获取：详细地址
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置：描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取：描述
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置：营业时间
	 */
	public void setBusinessTime(String businessTime) {
		this.businessTime = businessTime;
	}

	/**
	 * 获取：营业时间
	 */
	public String getBusinessTime() {
		return businessTime;
	}

	/**
	 * 设置：平均评星级数，1-5
	 */
	public void setStars(Integer stars) {
		this.stars = stars;
	}

	/**
	 * 获取：平均评星级数，1-5
	 */
	public Integer getStars() {
		return stars;
	}

	/**
	 * 设置：总的评星数
	 */
	public void setTotalStars(Integer totalStars) {
		this.totalStars = totalStars;
	}

	/**
	 * 获取：总的评星数
	 */
	public Integer getTotalStars() {
		return totalStars;
	}

	/**
	 * 设置：评价次数
	 */
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	/**
	 * 获取：评价次数
	 */
	public Integer getCommentCount() {
		return commentCount;
	}

	/**
	 * 设置：经度
	 */
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	/**
	 * 获取：经度
	 */
	public BigDecimal getLongitude() {
		return longitude;
	}

	/**
	 * 设置：纬度
	 */
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	/**
	 * 获取：纬度
	 */
	public BigDecimal getLatitude() {
		return latitude;
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
	 * 设置：售出多少
	 */
	public void setSold(Integer sold) {
		this.sold = sold;
	}

	/**
	 * 获取：售出多少
	 */
	public Integer getSold() {
		return sold;
	}

	/**
	 * 设置：收藏数量
	 */
	public void setFavoriteCount(Integer favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

	/**
	 * 获取：收藏数量
	 */
	public Integer getFavoriteCount() {
		return favoriteCount;
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

	/**
	 * 设置：商家状态
	 */
	public void setMechanismStatus(Integer status) {
		this.mechanismStatus = status;
	}

	/**
	 * 获取：商家状态
	 */
	public Integer getMechanismStatus() {
		return mechanismStatus;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}
