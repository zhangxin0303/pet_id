package com.cqcej.web.modules.app.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 验证码
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-03 16:25:12
 */
@TableName("ct_verification_code")
@Data
public class VerificationCodeEntity implements Serializable {
	/**
	 * 用户ID
	 */
	@TableId
	private Long codeId;
	/**
	 * 验证码发送时间
	 */
	private Date sendTime;
	
	private Date expireTime;
	
	private String code;
	
	/**
	 * 收取验证码的账号（手机号或者邮箱）
	 */
	private String account;
}
