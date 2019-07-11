package com.cqcej.web.common.utils;

import java.util.Random;

/**
 * 验证码工具
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-04 14:08
 */
public class CodeUtils {
	public static String generateCode(int length) {
		
		int number = new Random().nextInt(getBoundWithLength(length));
		return String.format("%06d", number);
	}
	
	public static String generateCode() {
		return generateCode(6);
	}
	
	/**
	 * 生成指定位数的最大数字
	 * @param length
	 * @return
	 */
	private static int getBoundWithLength(int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append("9");
		}
		return Integer.parseInt(sb.toString());
	}
}
