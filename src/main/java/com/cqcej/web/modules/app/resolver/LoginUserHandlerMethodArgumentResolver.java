package com.cqcej.web.modules.app.resolver;

import com.cqcej.web.modules.app.annotation.LoginUser;
import com.cqcej.web.modules.app.interceptor.AuthorizationInterceptor;
import com.cqcej.web.modules.common.entity.UserEntity;
import com.cqcej.web.modules.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 有@LoginUser注解的方法参数，注入当前登录用户
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-03-23 22:02
 */
@Component
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
	private final UserService userService;
	
	@Autowired
	public LoginUserHandlerMethodArgumentResolver(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterType().isAssignableFrom(UserEntity.class) && parameter.hasParameterAnnotation(LoginUser.class);
	}
	
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
	                              NativeWebRequest request, WebDataBinderFactory factory) throws Exception {
		//获取用户ID
		Object object = request.getAttribute(AuthorizationInterceptor.USER_KEY, RequestAttributes.SCOPE_REQUEST);
		if (object == null) {
			return null;
		}
		
		//获取用户信息
		return userService.selectById((Long) object);
	}
}
