package com.cqcej.web.modules.app.utils;

import com.github.wxpay.sdk.WXPayConstants;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * 微信APP支付MD5加密
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-10-16 10:27
 */
public class WxMD5Util {
	public String getSign(Map<String, String> data, String key) {
		Set<String> keySet = data.keySet();
		String[] keyArray = keySet.toArray(new String[keySet.size()]);
		Arrays.sort(keyArray);
		StringBuilder sb = new StringBuilder();
		for (String k : keyArray) {
			if (k.equals(WXPayConstants.FIELD_SIGN)) {
				continue;
			}
			if (data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
				sb.append(k).append("=").append(data.get(k).trim()).append("&");
		}
		sb.append("key=").append(key);
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array;
			array = md.digest(sb.toString().getBytes(StandardCharsets.UTF_8));
			StringBuilder sb2 = new StringBuilder();
			for (byte item : array) {
				sb2.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
			}
			return sb2.toString().toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return "";
	}
}
