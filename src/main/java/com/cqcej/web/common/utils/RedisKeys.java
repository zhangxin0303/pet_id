package com.cqcej.web.common.utils;

/**
 * Redis所有Keys
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-07-18 19:51
 */
public class RedisKeys {
	
	public static String getSysConfigKey(String key) {
		return "admin:config:" + key;
	}
}
