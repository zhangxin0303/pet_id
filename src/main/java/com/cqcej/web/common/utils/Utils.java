package com.cqcej.web.common.utils;

import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * DESCRIPTION
 *
 * @author Li HuanLing
 * @version 0.0.1
 * @date 2018-04-03 15:04
 */
public class Utils {
	/**
	 * 自动生成32位的UUid，对应数据库的主键id进行插入用。
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static void setObjectProperty(Object obj, String PropertyName, Object value) throws NoSuchFieldException, IllegalAccessException {
		// 获取obj类的字节文件对象
		Class c = obj.getClass();
		
		// 获取该类的成员变量
		Field f = c.getDeclaredField(PropertyName);
		
		// 取消语言访问检查
		f.setAccessible(true);
		
		// 给变量赋值
		f.set(obj, value);
	}
	
	public static String readResourceFile(String resourcePath) {
		return readResourceFile(resourcePath, false);
	}
	
	public static String readResourceFile(String resourcePath, boolean origin) {
		try {
			File file = ResourceUtils.getFile(resourcePath);
			StringBuilder sb = new StringBuilder();
			String s;
			
			BufferedReader br;
			br = new BufferedReader(new FileReader(file));
			while ((s = br.readLine()) != null) {
				if (origin) {
					sb.append(s).append('\n');
				} else {
					if (s.startsWith("-")) {
						continue;
					}
					sb.append(s);
				}
			}
			br.close();
			
			return sb.toString();
		} catch (IOException ignore) {
		}
		return "";
	}
	
	public static String getOrderIdByTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = sdf.format(new Date());
		Random random = new Random();
		int end = random.nextInt(9999);
		return String.format("%s%04d", date, end);
	}
}
