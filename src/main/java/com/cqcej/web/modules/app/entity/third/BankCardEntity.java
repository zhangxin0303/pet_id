package com.cqcej.web.modules.app.entity.third;

import lombok.Data;

/**
 * 第三方查新银行卡
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-08-16 14:04
 */
@Data
public class BankCardEntity {
	private int error_code;
	private String reason;
	private ResultBean result;
	private String ordersign;
	
	@Data
	public static class ResultBean {
		
		private String bankname;
		private String banknum;
		private String cardprefixnum;
		private String cardname;
		private String cardtype;
		private String cardprefixlength;
		private String cardlength;
		private boolean isLuhn;
		private String bankurl;
		private String enbankname;
		private String abbreviation;
		private String bankimage;
		private Integer iscreditcard;
		private String servicephone;
		private String province;
		private String city;
	}
}
