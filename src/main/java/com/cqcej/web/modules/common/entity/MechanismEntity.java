package com.cqcej.web.modules.common.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 机构，包含诊所，美容院
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-27 15:52:26
 */
@Data
@TableName("ct_mechanism")
public class MechanismEntity implements Serializable {
	
	/**
	 * 诊所
	 */
	public static final int MECHANISM_TYPE_CLINIC = 1;
	
	/**
	 * 美容院
	 */
	public static final int MECHANISM_TYPE_BEAUTY = 2;
	
	/**
	 * 健康
	 */
	public static final int MECHANISM_TYPE_HEALTHY = 4;
	
	/**
	 * 待认证
	 */
	public static final int MECHANISM_STATUS_DEFAULT = 2;
	
	/**
	 * 正常
	 */
	public static final int MECHANISM_STATUS_PASS = 1;
	
	/**
	 * 已锁定
	 */
	public static final int MECHANISM_STATUS_LOCK = 3;
	
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
	 * 机构类型（1诊所2美容院3健康）
	 */
	
	@ApiModelProperty("机构类型（1诊所2美容院3健康）")
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
	 * 总评星数
	 */
	private int totalStars;
	
	/**
	 * 评价总数
	 */
	private int commentCount;

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
	 * 收藏数
	 */
	@ApiModelProperty("收藏数")
	protected Integer favoriteCount;
	
	/**
	 * 状态1正常2待认证3锁定
	 */
	@ApiModelProperty("状态1正常2待认证3锁定")
	protected Integer mechanismStatus;
	
	/**
	 * logo地址
	 */
	@ApiModelProperty("logo地址")
	protected String logoUrl;

	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	protected Date createAt;
}
