package com.cqcej.web.modules.common.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-27 16:49:23
 */
@TableName("ct_user")
@ApiModel("用户")
@Data
public class UserEntity implements Serializable {
	
	/**
	 * 普通用户
	 */
	public static final int USER_TYPE_NORMAL = 1;
	
	/**
	 * 医疗机构
	 */
	public static final int USER_TYPE_MECHANISM = 10;
	
	/**
	 * 美容机构
	 */
	public static final int USER_TYPE_BEAUTY = 11;
	
	/**
	 * 医师
	 */
	public static final int USER_TYPE_DOCTOR = 20;
	
	/**
	 * 接送者
	 */
	public static final int USER_TYPE_TRANSPORT = 21;
	
	/**
	 * 遛狗人员
	 */
	public static final int USER_TYPE_WORK_DOG = 22;

	/**
	 * 积分进度-完善资料
	 */
	public static final int SCORE_PROGRESS_PROFILE = 1;

	/**
	 * 积分进度-签到
	 */
	public static final int SCORE_PROGRESS_SIGN = 2;

	/**
	 * 积分进度-在线时长
	 */
	public static final int SCORE_PROGRESS_ONLINE = 4;

	/**
	 * 积分进度-发帖/平评论
	 */
	public static final int SCORE_PROGRESS_ARTICLECOMM = 8;

	/**
	 * 积分进度-评价
	 */
	public static final int SCORE_PROGRESS_EVALUATE = 16;
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
	 * 昵称
	 */
	@ApiModelProperty("真名")
	protected String realname;
	
	@ApiModelProperty("身份证")
	protected String idCard;
	
	/**
	 * 手机号（登录账号）
	 */
	@ApiModelProperty("手机号（登录账号）")
	protected String mobile;
	
	/**
	 * 用户邮箱
	 */
	@ApiModelProperty("用户邮箱")
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
	 * 积分获取进度(1完善资料，2签到，4在线时长，8发帖/评论，16进行评价)
	 */
	@ApiModelProperty("积分获取进度(1完善资料，2签到，4在线时长，8发帖/评论，16进行评价)")
	protected Integer scoreProgress;


	/**
	 * 余额
	 */
	@ApiModelProperty("余额")
	protected BigDecimal balance;
	
	/**
	 * 累计收入
	 */
	@ApiModelProperty("累计收入")
	protected BigDecimal totalIncome;
	
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
	 * 用户签名
	 */
	@ApiModelProperty("用户签名")
	protected String signature;
	
	/**
	 * 是否初始化IM通讯
	 */
	@ApiModelProperty("是否初始化IM通讯")
	protected Integer initIm;
	
	/**
	 * 用户客户端，Android或iOS或Web
	 */
	@ApiModelProperty("用户客户端，Android或iOS或Web")
	protected String client;
	
	/**
	 * 用户设备唯一id
	 */
	@ApiModelProperty("用户设备唯一id")
	protected String deviceToken;
	
	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	protected Date createAt;
}
