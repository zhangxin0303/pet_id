package com.cqcej.web.common.utils;

import com.cqcej.web.common.exception.CTException;
import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordCheck {

	public static void check(String password){
		if(StringUtils.isBlank(password)){
			throw new CTException("密码不能为空");
		}
		Matcher m = Pattern.compile("[\u4e00-\u9fa5]").matcher(password);
		if(m.find()){
			throw new CTException("密码不能有汉字");
		}
		if(password.length() < 6 || password.length() > 24){
			throw new CTException("密码长度不正确");
		}
	}
}
