package com.cqcej.web.modules.app.utils;

/**
 * App常量
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-03 18:36:56
 */
public class AppConstant {
	/**
	 * 错误码，只针对需要特殊处理的
	 */
	public static class ErrorCode {
		/**
		 * 没有注册
		 */
		public final static int ERROR_NOT_REGISTER = 1;
		
		/**
		 * 账号密码错误
		 */
		public final static int ERROR_ACCOUNT_OR_PASSWORD_WRONG = 2;
	}
	
	public static class TBApi {
		public static final String AppKey = "24937335";
		public static final String AppSecret = "4c2888bf2149a6ced91125c3e81ce207";
 	}
 	
 	static class Tencent {
		static class CloudIM {
			static final long APP_KEY = 1400114073;
		}
    }
    
    public static final String ClientMain = "main";
    public static final String ClientService = "service";
    public static final String ClientServiceExtend = "service_extend";
    
    public static class Aliyun {
    	public static class Sms {
    		public static final String ACCESS_KEY_ID = "LTAIXmUtugfUmWR6";
    		public static final String ACCESS_KEY_SECRET = "INJfnPXdN9h6cDQ85JsZvow6FUlORA";
		
		    /**
		     * 注册模板
		     */
		    public static final String TEMPLATE_REGISTER = "SMS_142370077";
		
		    /**
		     * 修改密码模板
		     */
		    public static final String TEMPLATE_CHANGE_PASSWORD = "SMS_142370076";
		
		    /**
		     * 修改手机号模板
		     */
		    public static final String TEMPLATE_CHANGE_MOBILE = "SMS_142382104";
		
		    /**
		     * 忘记密码模板
		     */
		    public static final String TEMPLATE_FORGET_PASSWORD = "SMS_142500041";
		
		    /**
		     * 绑定银行卡模板
		     */
		    public static final String TEMPLATE_BIND_BANK = "SMS_142382117";
	    }
    }
    
    public static class Alipay {
    	public static final String APP_ID = "2018091961453328";
    }
    
    public static final String UMAPPKey = "1hfipcqdicrjv5wfbbmnx3msdcfds3at";
}
