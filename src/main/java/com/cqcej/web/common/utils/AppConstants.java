package com.cqcej.web.common.utils;

/**
 * App，Web等公共常量
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-05 上午12:30
 */
public class AppConstants {
	
	
	
	/**
	 * 支付宝支付
	 */
	public static final int PAYMENT_TYPE_ALIPAY = 1;
	
	/**
	 * 微信支付
	 */
	public static final int PAYMENT_TYPE_WECHAT = 2;
	
	/**
	 * 银联支付
	 */
	public static final int PAYMENT_TYPE_UNION = 3;
	
	
	
	/**
	 * 密码最短6位
	 */
	public static final short PASSWORD_LENGTH_MIN = 6;
	
	/**
	 * 密码最长24位
	 */
	public static final short PASSWORD_LENGTH_MAX = 24;
	
	/**
	 * 身份证号码尾数
	 */
	public static final short IDCARD_LENGTH = 18;
	
	/**
	 * 密码长度提示
	 */
	public static final String PASSWORD_LENGTH_TIP = "密码长度6-24位";
	
	/**
	 * 正则表达式：验证手机号
	 */
	public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0-9])|(19[0,5-9]))\\d{8}$";
	
	/**
	 * 正则表达式：验证邮箱
	 */
	public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	
	/**
	 * 验证码有效期5分钟
	 */
	public static final int CODE_VALIDATE_TIME_SECONDS = 300;
	
	/**
	 * 发送验证码类型，注册
	 */
	public static final int SEND_CODE_TYPE_REGISTER = 0;
	
	/**
	 * 发送验证码类型，忘记密码
	 */
	public static final int SEND_CODE_TYPE_FORGET_PASSWORD = 1;
	
	/**
	 * 发送验证码类型，修改密码
	 */
	public static final int SEND_CODE_TYPE_CHANGE_PASSWORD = 2;
	
	/**
	 * 发送验证码类型，修改手机号
	 */
	public static final int SEND_CODE_TYPE_CHANGE_MOBILE = 3;
	
	/**
	 * 发送验证码类型，绑定银行卡
	 */
	public static final int SEND_CODE_TYPE_BIND_BANK = 4;
	
	/**
	 * 发送验证码周期，60秒（60秒内不能再次发送验证码）
	 */
	public static final int SEND_CODE_PERIOD = 60;
	
	public static class Clients {
		public static final String Android = "Android";
		public static final String iOS = "iOS";
		public static final String Web = "Web";
	}
}
