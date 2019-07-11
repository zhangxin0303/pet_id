package com.cqcej.web.common.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2017年3月8日 上午10:19:56
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckCodeFrequency {
	String value() default "";
}
