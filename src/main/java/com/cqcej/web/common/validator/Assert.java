package com.cqcej.web.common.validator;

import com.cqcej.web.common.exception.CTException;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-03-23 15:50
 */
public abstract class Assert {
	
	public static void isBlank(String str, String message) {
		if (StringUtils.isBlank(str)) {
			throw new CTException(message);
		}
	}
	
	/**
	 * 默认500错误码
	 * @param object
	 * @param message
	 */
	public static void isNull(Object object, String message) {
		isNull(object, message, 500);
	}
	
	public static void isNull(Object object, String message, int code) {
		if (object == null) {
			throw new CTException(message, code);
		}
	}
}
