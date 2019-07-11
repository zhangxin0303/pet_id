package com.cqcej.web.modules.app.interceptor;


import com.cqcej.web.common.exception.CTException;
import com.cqcej.web.modules.app.annotation.Login;
import com.cqcej.web.modules.app.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限(Token)验证
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-03-23 15:38
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
	private final JwtUtils jwtUtils;
	
	public static final String USER_KEY = "userId";
	
	@Autowired
	public AuthorizationInterceptor(JwtUtils jwtUtils) {
		this.jwtUtils = jwtUtils;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws CTException {
		Login methodAnnotation, typeAnnotation;
		if (handler instanceof HandlerMethod) {
			// 获取类注解
			typeAnnotation = ((HandlerMethod) handler).getBeanType().getAnnotation(Login.class);
			methodAnnotation = ((HandlerMethod) handler).getMethodAnnotation(Login.class);
		} else {
			return true;
		}
		
		// 方法注解优先
		if (methodAnnotation != null) {
			if (methodAnnotation.allowAnonymous()) {
				trySetUserId(request);
				return true;
			}
		} else {
			if (typeAnnotation != null) {
				if (typeAnnotation.allowAnonymous()) {
					trySetUserId(request);
					return true;
				}
			} else {
				return true;
			}
		}
		
		setUserId(request);
		return true;
	}
	
	private void trySetUserId(HttpServletRequest request) {
		try {
			setUserId(request);
		} catch (CTException ignored) {
			request.setAttribute(USER_KEY, (long) 0);
		}
	}
	
	private void setUserId(HttpServletRequest request) {
		//获取用户凭证
		String token = request.getHeader(jwtUtils.getHeader());
		if (StringUtils.isBlank(token)) {
			token = request.getParameter(jwtUtils.getHeader());
		}
		
		//凭证为空
		if (StringUtils.isBlank(token)) {
			throw new CTException(jwtUtils.getHeader() + "不能为空", HttpStatus.UNAUTHORIZED.value());
		}
		
		token = token.replace(jwtUtils.getStartWith(), "");
		
		Claims claims = jwtUtils.getClaimByToken(token);
		if (claims == null || jwtUtils.isTokenExpired(claims.getExpiration())) {
			throw new CTException(jwtUtils.getHeader() + "失效，请重新登录", HttpStatus.UNAUTHORIZED.value());
		}
		
		//设置userId到request里，后续根据userId，获取用户信息
		long userId = Long.parseLong(claims.getSubject());
		request.setAttribute(USER_KEY, userId);
	}
}
