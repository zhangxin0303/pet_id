package com.cqcej.web.modules.app.annotation;

import java.lang.annotation.*;

/**
 * app登录效验
 *
 * 可在类和方法上，默认需要验证，设置allowAnonymous为true可跳过验证
 * 优先判断方法上的注解
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2017/9/23 14:30
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {
	/**
	 * 是否允许不进行登录验证
	 * @return boolean
	 */
	boolean allowAnonymous() default false;
}
